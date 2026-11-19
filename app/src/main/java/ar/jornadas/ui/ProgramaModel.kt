package ar.jornadas.ui

import android.app.Application
import androidx.lifecycle.*
import ar.jornadas.Servicios
import ar.jornadas.data.Charla
import ar.jornadas.data.ResultadoSync
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/** Estado único de la pantalla del programa. */
sealed interface ProgramaUi {
    /** Todavía no se sabe nada (primera lectura de Room o primera descarga sin edición previa). */
    data object Cargando : ProgramaUi
    /** Edición vigente; con lista vacía significa «sin charlas programadas». */
    data class Datos(val charlas: List<Charla>, val actualizando: Boolean) : ProgramaUi
    /** La última descarga falló: se muestran los datos de la última edición guardada. */
    data class Guardados(val charlas: List<Charla>, val actualizando: Boolean) : ProgramaUi
    /** Falla sin ninguna edición previa: se ofrece reintentar. */
    data object SinDatos : ProgramaUi
}

val ProgramaUi.charlas: List<Charla>
    get() = when (this) {
        is ProgramaUi.Datos -> charlas
        is ProgramaUi.Guardados -> charlas
        else -> emptyList()
    }

class ProgramaModel(app: Application, private val guardado: SavedStateHandle) : AndroidViewModel(app) {
    private val servicios = Servicios.obtener(app)
    val mesa = servicios.mesa
    private val repo = servicios.agenda

    // Filtros en estado de instancia guardado: sobreviven a rotación y a muerte del proceso,
    // pero una tarea nueva arranca sin filtros (no se persisten en Room ni DataStore).
    val dia: StateFlow<Int> = guardado.getStateFlow("dia", 0)
    val tema: StateFlow<String> = guardado.getStateFlow("tema", "Todos")
    fun elegirDia(valor: Int) { guardado["dia"] = valor }
    fun elegirTema(valor: String) { guardado["tema"] = valor }

    val ui: StateFlow<ProgramaUi> = combine(repo.programa, repo.hayEdicion, repo.cargando, repo.falla) {
            charlas, hayEdicion, cargando, falla ->
        when {
            hayEdicion && falla -> ProgramaUi.Guardados(charlas, cargando)
            hayEdicion -> ProgramaUi.Datos(charlas, cargando)
            falla && !cargando -> ProgramaUi.SinDatos
            else -> ProgramaUi.Cargando
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ProgramaUi.Cargando)

    /** Carga automática: una sola vez por proceso (la bandera vive en el repositorio). */
    fun cargaAutomatica() = repo.cargaAutomatica()

    /** Actualización explícita (Actualizar / Reintentar). */
    fun recargar() = repo.actualizar()

    fun marcar(id: String) { viewModelScope.launch { repo.cambiarFavorita(id) } }
}
