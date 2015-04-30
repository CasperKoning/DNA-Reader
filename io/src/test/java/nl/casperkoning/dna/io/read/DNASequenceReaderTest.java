package nl.casperkoning.dna.io.read;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class DNASequenceReaderTest {

    @Test(expected = IOException.class)
    public void testNotABase() throws IOException {
        String notABase = "Z";
        DNASequenceReader reader = new DNASequenceReader(new BufferedReader(new StringReader(notABase)));
        reader.read();
    }

    @Test
    public void testReadBases() throws IOException {
        String bases = "ACTG";
        DNASequenceReader reader = new DNASequenceReader(new BufferedReader(new StringReader(bases)));
        assertThat(reader.read(), is("A"));
        assertThat(reader.read(), is("C"));
        assertThat(reader.read(), is("T"));
        assertThat(reader.read(), is("G"));
    }

    @Test
    public void testEndReturnsNull() throws IOException {
        String base = "A";
        DNASequenceReader reader = new DNASequenceReader(new BufferedReader(new StringReader(base)));
        assertThat(reader.read(), is("A"));
        assertNull(reader.read());
    }

    @Test
    public void testMarkAndResetWork() throws IOException {
        String bases = "ATTTTT";
        DNASequenceReader reader = new DNASequenceReader(new BufferedReader(new StringReader(bases)));
        reader.mark(1);
        assertThat(reader.read(), is("A"));
        reader.reset();
        assertThat(reader.read(), is("A"));
    }

}
