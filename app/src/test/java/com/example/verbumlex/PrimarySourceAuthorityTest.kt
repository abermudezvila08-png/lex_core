package com.example.verbumlex

import com.example.verbumlex.engine.lex.ExtractionMethod
import com.example.verbumlex.engine.lex.PrimarySourceDocument
import com.example.verbumlex.engine.lex.PrimarySourceIngestionEngine
import com.example.verbumlex.engine.lex.VerificationStatus
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class PrimarySourceAuthorityTest {

    @Test
    fun testPrimaryPdfIsAuthoritativeSource() {
        val primaryPdfText = "Artículo 400. Quien tenga relación sexual con otra persona..."
        val conflictingKotlinText = "Artículo 400. Evasión fiscal cometida por contribuyentes..."

        // Regla de autoridad: el PDF oficial manda sobre la representación estructurada
        val resolution = PrimarySourceIngestionEngine.resolveDiscrepancy(primaryPdfText, conflictingKotlinText)

        assertTrue(resolution.isConflict)
        assertEquals(primaryPdfText, resolution.authoritativeText)
        assertEquals(VerificationStatus.CONFLICT, resolution.status)
    }

    @Test
    fun testSourceHashMismatchCreatesConflict() {
        val tempFile = File.createTempFile("fake_norm", ".pdf")
        tempFile.writeText("Corrupted or modified bytes")

        val result = PrimarySourceIngestionEngine.verifyPhysicalIntegrity("SRC-LEY-151-2022", tempFile)
        assertFalse(result.first)
        assertTrue(result.second.startsWith("FAIL_HASH"))

        tempFile.delete()
    }

    @Test
    fun testEvidencePointsToPrimaryDocument() {
        val queryResult = PrimarySourceIngestionEngine.queryWithPrimaryTrace("LEY-151", "400")

        assertTrue(queryResult.matched)
        assertNotNull(queryResult.sourceDocument)
        assertEquals("SRC-LEY-151-2022", queryResult.sourceDocument?.sourceDocumentId)
        assertEquals("eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20", queryResult.sourceDocument?.sha256)
        assertEquals(136, queryResult.page)
        assertEquals("400", queryResult.article)
        assertNotNull(queryResult.evidenceFragment)
        assertEquals(ExtractionMethod.STREAM_DECOMPRESSION, queryResult.evidenceFragment?.extractionMethod)
    }

    @Test
    fun testLexToLexUsesPrimaryEvidence() {
        val relation = PrimarySourceIngestionEngine.traceLexToLex(
            oldSourceId = "SRC-LEY-62-1987",
            oldArticle = "305",
            newSourceId = "SRC-LEY-151-2022",
            newArticle = "400",
            relationType = "SUSTITUCION_INTERTEMPORAL",
            justification = "Descarte de homonimia y trazabilidad a fuentes primarias"
        )

        assertEquals("SRC-LEY-62-1987", relation.oldSourceDocumentId)
        assertEquals("SRC-LEY-151-2022", relation.newSourceDocumentId)
        assertEquals("eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20", relation.newSourceSha256)
        assertNotNull(relation.newEvidenceFragment)
        assertEquals(136, relation.newEvidenceFragment?.page)
    }

    @Test
    fun testRebuildFromPrimarySources() {
        val rebuild = PrimarySourceIngestionEngine.rebuildCorpusFromPrimarySources()

        assertTrue(rebuild.success)
        assertTrue(rebuild.totalSourcesReconstructed >= 8)
        assertTrue(rebuild.totalFragmentsPreserved >= 4)
        assertEquals("PRIMARY_CORPUS_RECONSTRUCTED_OK", rebuild.status)
    }

    @Test
    fun testInsufficientPrimaryEvidenceReturnsStrictFailure() {
        val unknownQuery = PrimarySourceIngestionEngine.queryWithPrimaryTrace("LEY-INVENTADA", "9999")

        assertFalse(unknownQuery.matched)
        assertNull(unknownQuery.sourceDocument)
        assertEquals("INSUFFICIENT_PRIMARY_EVIDENCE", unknownQuery.epistemicCode)
    }
}
