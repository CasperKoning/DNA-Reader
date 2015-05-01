package nl.casperkoning.dna.io.threads;

import nl.casperkoning.dna.io.write.DNAWriter;
import nl.casperkoning.dna.model.Protein;

import java.util.concurrent.BlockingQueue;

public class WritingThread extends Thread {
    private final BlockingQueue<Protein> proteins;
    private final DNAWriter dnaWriter;
    private final NotifyingObject notifyingObject;

    public WritingThread(BlockingQueue<Protein> proteins, DNAWriter writer, NotifyingObject notifyingObject) {
        this.proteins = proteins;
        this.dnaWriter = writer;
        this.notifyingObject = notifyingObject;
        this.setName("WritingThread");
    }

    @Override
    public void run() {
        dnaWriter.startWriting();
        try {
            writeDna();
        } catch (InterruptedException ie) {
            throw new RuntimeException("WritingThread was interrupted.");
        }
        dnaWriter.stopWriting();
    }

    private void writeDna() throws InterruptedException {
        while (notifyingObject.isReading()) {
            this.dnaWriter.write(proteins.take());
        }
        while (!proteins.isEmpty()) {
            this.dnaWriter.write(proteins.take());
        }
    }
}
