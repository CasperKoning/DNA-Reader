package nl.casperkoning.dna.io.write;

import nl.casperkoning.dna.model.AminoAcid;
import nl.casperkoning.dna.model.Protein;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DNAWriterJSONTest {

    @Test
    public void testReadDNA() throws IOException {
        List<AminoAcid> aminoAcids = Arrays.asList(AminoAcid.MET, AminoAcid.ARG, AminoAcid.STOP);
        Protein protein = new Protein(aminoAcids);
        StringWriter stringWriter = new StringWriter();
        DNAWriter dnaWriter = new DNAWriterJSON(stringWriter);
        dnaWriter.startWriting();
        dnaWriter.write(protein);
        dnaWriter.stopWriting();
        assertThat(stringWriter.toString(), is("{\"DNA\":[{\"Protein\":[\"MET\",\"ARG\",\"STOP\"]}]}"));
    }

}
