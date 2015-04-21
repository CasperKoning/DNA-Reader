import nl.casperkoning.dna.io.AminoAcidReader;
import nl.casperkoning.dna.io.DNASequenceReader;
import nl.casperkoning.dna.model.AminoAcid;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AminoAcidReaderTest {

    @Test
    public void testReadAminoAcid() throws IOException{
        String aaa = "AAA";
        AminoAcidReader reader = new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(aaa))));
        AminoAcid aminoAcid = reader.read();
        assertNotNull(aminoAcid);
    }

    @Test
    public void testMarkResetAndShiftCursorForwardWork() throws IOException{
        String actga = "ACTGA";
        AminoAcidReader reader = new AminoAcidReader(new DNASequenceReader(new BufferedReader(new StringReader(actga))));
        reader.mark(1);
        AminoAcid aminoAcid1 = reader.read();
        assertThat("ACT",matchesToRegex(aminoAcid1.getBasePairsRegex()));
        reader.reset();
        reader.shiftCursorFoward(2);
        AminoAcid aminoAcid2 = reader.read();
        assertThat("TGA",matchesToRegex(aminoAcid2.getBasePairsRegex()));
    }

    private Matcher<? super String> matchesToRegex(final String basePairsRegex) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return o instanceof String && ((String)o).matches(basePairsRegex);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("String that matches the regular expression " + basePairsRegex);
            }
        };
    }

}
