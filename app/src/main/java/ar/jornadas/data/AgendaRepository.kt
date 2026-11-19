package ar.jornadas.data

import android.database.sqlite.SQLiteException
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

enum class ResultadoSync { Exito, Falla }

class AgendaRepository(private val api: ProgramaApi, private val dao: CarteleraDao) {
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
