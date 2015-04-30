package nl.casperkoning.dna.demo;

import nl.casperkoning.dna.io.threads.NotifyingObject;
import nl.casperkoning.dna.io.threads.ReadingThread;
import nl.casperkoning.dna.io.threads.WritingThread;
import nl.casperkoning.dna.io.write.DNAWriter;
import nl.casperkoning.dna.io.write.DNAWriterJSON;
import nl.casperkoning.dna.model.Protein;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Demo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Protein> proteins = new LinkedBlockingQueue<>();
        NotifyingObject notifyingObject = new NotifyingObject();
        BufferedReader reader = new BufferedReader(new InputStreamReader(Demo.class.getResourceAsStream("/dnaSequence.txt")));

        DNAWriter writer = new DNAWriterJSON(new PrintWriter(System.out));

        Thread readingThread = new ReadingThread(proteins, reader, notifyingObject);
        Thread writingThread = new WritingThread(proteins, writer, notifyingObject);

        readingThread.start();
        readingThread.join();
        writingThread.start();
        writingThread.join();
    }
}
