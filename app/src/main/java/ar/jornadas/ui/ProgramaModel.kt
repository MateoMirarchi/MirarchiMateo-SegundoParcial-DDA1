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

class ProgramaModel(app: Application) : AndroidViewModel(app) {
    private val servicios = Servicios(app)
    val mesa = servicios.mesa
    private val repo = servicios.agenda
    val dia = MutableStateFlow(0)
    private val cargando = MutableStateFlow(false)
    private val falla = MutableStateFlow(false)

    val ui: StateFlow<ProgramaUi> = combine(repo.programa, repo.hayEdicion, cargando, falla) {
            charlas, hayEdicion, cargando, falla ->
        when {
            hayEdicion && falla -> ProgramaUi.Guardados(charlas, cargando)
            hayEdicion -> ProgramaUi.Datos(charlas, cargando)
            falla && !cargando -> ProgramaUi.SinDatos
            else -> ProgramaUi.Cargando
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ProgramaUi.Cargando)

    fun recargar() {
        viewModelScope.launch {
            cargando.value = true
            try {
                falla.value = repo.sincronizar() == ResultadoSync.Falla
            } finally {
                cargando.value = false
            }
        }
    }

    fun marcar(id: String) { viewModelScope.launch { repo.cambiarFavorita(id) } }
}
