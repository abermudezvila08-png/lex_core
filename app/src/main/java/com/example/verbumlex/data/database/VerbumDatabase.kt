package com.example.verbumlex.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        NormaEntity::class,
        ArticuloEntity::class,
        ObligacionEntity::class,
        RelacionNormativaEntity::class,
        EvidenciaEntity::class,
        TramiteEntity::class,
        ChronosEventEntity::class,
        ExpedienteEntity::class,
        AuditTrailEntity::class,
        DocumentoEntity::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VerbumDatabase : RoomDatabase() {
    abstract fun normaDao(): NormaDao
    abstract fun articuloDao(): ArticuloDao
    abstract fun obligacionDao(): ObligacionDao
    abstract fun relacionNormativaDao(): RelacionNormativaDao
    abstract fun evidenciaDao(): EvidenciaDao
    abstract fun tramiteDao(): TramiteDao
    abstract fun chronosDao(): ChronosDao
    abstract fun expedienteDao(): ExpedienteDao
    abstract fun auditTrailDao(): AuditTrailDao
    abstract fun documentoDao(): DocumentoDao

    companion object {
        @Volatile
        private var INSTANCE: VerbumDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): VerbumDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VerbumDatabase::class.java,
                    "verbum_lex_database.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .addCallback(VerbumDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class VerbumDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database)
                    }
                }
            }

            suspend fun populateDatabase(database: VerbumDatabase) {
                database.normaDao().insertNormas(InitialCorpus.getNormas())
                database.articuloDao().insertArticulos(InitialCorpus.getArticulos())
                database.obligacionDao().insertObligaciones(InitialCorpus.getObligaciones())
                database.relacionNormativaDao().insertRelaciones(InitialCorpus.getRelaciones())
                database.evidenciaDao().insertEvidencias(InitialCorpus.getEvidencias())
                database.tramiteDao().insertTramites(InitialCorpus.getTramites())
                database.chronosDao().insertChronosEvents(InitialCorpus.getChronosEvents())
                InitialCorpus.getExpedientes().forEach { database.expedienteDao().insertExpediente(it) }
                database.auditTrailDao().insertAuditEvents(InitialCorpus.getAuditTrail())
            }
        }
    }
}
