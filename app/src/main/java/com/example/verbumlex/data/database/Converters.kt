package com.example.verbumlex.data.database

import androidx.room.TypeConverter
import com.example.verbumlex.core.ChronosEstado
import com.example.verbumlex.core.EvidenceEstado
import com.example.verbumlex.core.NormaEstado
import com.example.verbumlex.core.NormaTipo
import com.example.verbumlex.core.PrioridadConsulta
import com.example.verbumlex.core.RelacionTipo

class Converters {
    @TypeConverter
    fun fromNormaTipo(value: NormaTipo?): String? = value?.name

    @TypeConverter
    fun toNormaTipo(value: String?): NormaTipo? = value?.let { enumValueOf<NormaTipo>(it) }

    @TypeConverter
    fun fromNormaEstado(value: NormaEstado?): String? = value?.name

    @TypeConverter
    fun toNormaEstado(value: String?): NormaEstado? = value?.let { enumValueOf<NormaEstado>(it) }

    @TypeConverter
    fun fromEvidenceEstado(value: EvidenceEstado?): String? = value?.name

    @TypeConverter
    fun toEvidenceEstado(value: String?): EvidenceEstado? = value?.let { enumValueOf<EvidenceEstado>(it) }

    @TypeConverter
    fun fromRelacionTipo(value: RelacionTipo?): String? = value?.name

    @TypeConverter
    fun toRelacionTipo(value: String?): RelacionTipo? = value?.let { enumValueOf<RelacionTipo>(it) }

    @TypeConverter
    fun fromPrioridadConsulta(value: PrioridadConsulta?): String? = value?.name

    @TypeConverter
    fun toPrioridadConsulta(value: String?): PrioridadConsulta? = value?.let { enumValueOf<PrioridadConsulta>(it) }

    @TypeConverter
    fun fromChronosEstado(value: ChronosEstado?): String? = value?.name

    @TypeConverter
    fun toChronosEstado(value: String?): ChronosEstado? = value?.let { enumValueOf<ChronosEstado>(it) }
}
