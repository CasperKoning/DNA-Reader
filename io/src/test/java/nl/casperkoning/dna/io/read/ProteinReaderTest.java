package nl.casperkoning.dna.io.read;

import nl.casperkoning.dna.model.AminoAcid;
import nl.casperkoning.dna.model.Protein;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProteinReaderTest {
    private static final String START = "ATG";
    private static final String SOME_OTHER_AMINO_ACID = "CAT";
    private static final String STOP = "TAA";

    @Test
    public void testReadProtein() throws IOException {
        String dna = START + SOME_OTHER_AMINO_ACID + STOP;
        ProteinReader reader = new ProteinReader(new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(dna)))));
        Protein protein = reader.read();

        List<AminoAcid> acidsExpected = Arrays.asList(AminoAcid.MET,AminoAcid.HIS,AminoAcid.STOP);
        List<AminoAcid> acidsRead = protein.getAminoAcids();
        for(int i=0; i<3;i++){
            assertEquals(acidsExpected.get(i),acidsRead.get(i));
        }
    }

    @Test
    public void testReadProteinWithAmountOfBasesInFrontNotDivisibleByThree() throws IOException {
        String dna = "AA" + START + SOME_OTHER_AMINO_ACID + STOP;
        ProteinReader reader = new ProteinReader(new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(dna)))));
        Protein protein = reader.read();

        List<AminoAcid> acidsExpected = Arrays.asList(AminoAcid.MET,AminoAcid.HIS,AminoAcid.STOP);
        List<AminoAcid> acidsRead = protein.getAminoAcids();
        for(int i=0; i<3;i++){
            assertEquals(acidsExpected.get(i),acidsRead.get(i));
        }
    }

    @Test
    public void testReadProteinWithoutStop() throws IOException {
        String dna = START + SOME_OTHER_AMINO_ACID + SOME_OTHER_AMINO_ACID;
        ProteinReader reader = new ProteinReader(new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(dna)))));
        Protein protein = reader.read();
        assertNull(protein);
    }

    @Test
    public void testReadDnaWithoutProtein() throws IOException {
        String dna = SOME_OTHER_AMINO_ACID + SOME_OTHER_AMINO_ACID + SOME_OTHER_AMINO_ACID;
        ProteinReader reader = new ProteinReader(new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(dna)))));
        Protein protein = reader.read();
        assertNull(protein);
    }
}
