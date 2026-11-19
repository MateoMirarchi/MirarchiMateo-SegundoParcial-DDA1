package ar.jornadas.data

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import retrofit2.Response
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

data class FichaRemota(val id: String, val title: String, val track: String, val day: Int,
    val time: String, val room: String, val speaker: String, val summary: String)

interface ProgramaApi {
    @GET("programa") suspend fun consultar(): Response<List<FichaRemota>>
}

@Entity(tableName = "charlas")
data class Charla(@PrimaryKey val id: String, val titulo: String, val tema: String, val dia: Int,
    val hora: String, val sala: String, val ponente: String, val resumen: String,
    val favorita: Boolean = false)

/** Metadatos: una fila (id = 1) existe si hubo al menos una descarga exitosa, aunque haya traído cero charlas. */
@Entity(tableName = "edicion")
data class Edicion(@PrimaryKey val id: Int = 1,
    @ColumnInfo(defaultValue = "0") val descargadaEn: Long = 0)

@Dao
abstract class CarteleraDao {
    @Query("SELECT * FROM charlas ORDER BY dia, hora, sala")
    abstract fun observar(): Flow<List<Charla>>

    @Query("SELECT * FROM edicion WHERE id = 1")
    abstract fun observarEdicion(): Flow<Edicion?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun guardar(fichas: List<Charla>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun guardarEdicion(edicion: Edicion)

    /** Guarda el snapshot y registra la edición en una sola transacción. (La fusión por id llega en el pedido 42.) */
    @Transaction
    open suspend fun guardarSnapshot(fichas: List<Charla>) {
        guardar(fichas)
        guardarEdicion(Edicion(1, System.currentTimeMillis()))
    }

    @Query("UPDATE charlas SET favorita = NOT favorita WHERE id = :id")
    abstract suspend fun alternar(id: String)
}

@Database(entities = [Charla::class, Edicion::class], version = 2, exportSchema = true)
abstract class Archivo : RoomDatabase() {
    abstract fun cartelera(): CarteleraDao
}

/** Conserva charlas y favoritas. Un usuario con filas ya tuvo una descarga válida; sin filas queda "sin descarga previa". */
val MIGRACION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `edicion` (`id` INTEGER NOT NULL, " +
            "`descargadaEn` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))")
        db.execSQL("INSERT INTO `edicion` (`id`, `descargadaEn`) SELECT 1, 0 " +
            "WHERE EXISTS (SELECT 1 FROM `charlas`)")
    }
}
