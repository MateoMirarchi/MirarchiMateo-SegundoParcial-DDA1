package ar.jornadas.data

import android.database.sqlite.SQLiteException
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

enum class ResultadoSync { Exito, Falla }

class AgendaRepository(
    private val api: ProgramaApi,
    private val dao: CarteleraDao,
    /** Scope de proceso: la petición no se cancela al rotar ni al cerrar la pantalla. */
    private val scope: CoroutineScope,
) {
    private val enCurso = AtomicBoolean(false)
    private val autoHecha = AtomicBoolean(false)
    private val _cargando = MutableStateFlow(false)
    private val _falla = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando
    val falla: StateFlow<Boolean> = _falla

    /** Una sola carga automática por proceso; si falla, no se reintenta sola. */
    fun cargaAutomatica() {
        if (autoHecha.compareAndSet(false, true)) actualizar()
    }

    /** Actualización explícita: ignora el pedido si ya hay una petición en vuelo. */
    fun actualizar() {
        if (!enCurso.compareAndSet(false, true)) return
        _cargando.value = true
        scope.launch {
            try {
                _falla.value = sincronizar() == ResultadoSync.Falla
            } finally {
                _cargando.value = false
                enCurso.set(false)
            }
        }
    }

    val programa = dao.observar()
    /** true si alguna vez se descargó una edición (aunque no tuviera charlas). */
    val hayEdicion = dao.observarEdicion().map { it != null }

    suspend fun sincronizar(): ResultadoSync = withContext(Dispatchers.IO) {
        try {
            val respuesta = api.consultar()
            val cuerpo = respuesta.body()
            if (!respuesta.isSuccessful || cuerpo == null) return@withContext ResultadoSync.Falla
            dao.guardarSnapshot(cuerpo.map {
                Charla(it.id, it.title, it.track, it.day, it.time, it.room, it.speaker, it.summary)
            })
            ResultadoSync.Exito
        } catch (e: IOException) {
            currentCoroutineContext().ensureActive() // una cancelación no es una falla de red
            ResultadoSync.Falla
        } catch (e: JsonParseException) {
            ResultadoSync.Falla
        } catch (e: SQLiteException) {
            ResultadoSync.Falla
        }
    }

    suspend fun cambiarFavorita(id: String) = withContext(Dispatchers.IO) { dao.alternar(id) }
}
