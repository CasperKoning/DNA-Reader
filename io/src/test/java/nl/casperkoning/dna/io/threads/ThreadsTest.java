package nl.casperkoning.dna.io.threads;

import nl.casperkoning.dna.io.write.DNAWriter;
import nl.casperkoning.dna.io.write.DNAWriterJSON;
import nl.casperkoning.dna.model.Protein;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadsTest {
    @Test
    public void test() throws IOException, InterruptedException {
        BlockingQueue<Protein> proteins = new LinkedBlockingQueue<>();
        NotifyingObject notifyingObject = new NotifyingObject();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ThreadsTest.class.getResourceAsStream("/dna.txt")));

        StringWriter stringWriter = new StringWriter();
        DNAWriter writer = new DNAWriterJSON(stringWriter);

        Thread readingThread = new ReadingThread(proteins, reader, notifyingObject);
        Thread writingThread = new WritingThread(proteins, writer, notifyingObject);

        readingThread.start();
        readingThread.join();
        writingThread.start();
        writingThread.join();

        String result = stringWriter.toString();
        assertThat(result,is("{\"DNA\":[{\"Protein\":[\"MET\",\"ASN\",\"STOP\"]}]}"));
    }
}
