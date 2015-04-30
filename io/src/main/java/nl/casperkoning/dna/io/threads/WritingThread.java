package nl.casperkoning.dna.io.threads;

import nl.casperkoning.dna.io.write.DNAWriter;
import nl.casperkoning.dna.model.Protein;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class WritingThread extends Thread{
    private BlockingQueue<Protein> proteins;
    private DNAWriter dnaWriter;
    private NotifyingObject notifyingObject;

    public WritingThread(BlockingQueue<Protein> proteins, DNAWriter writer, NotifyingObject notifyingObject) throws IOException {
        this.proteins = proteins;
        this.dnaWriter = writer;
        this.notifyingObject = notifyingObject;
        this.setName("WritingThread");
    }

    @Override
    public void run() {
        dnaWriter.startWriting();
        while (notifyingObject.isReading()) {
            try {
                this.dnaWriter.write(proteins.take());
            }catch (InterruptedException e) {
                throw new RuntimeException(" WritingThread was interrupted");
            }
        }
        while(!proteins.isEmpty()){
            try {
                this.dnaWriter.write(proteins.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(" WritingThread was interrupted");
            }
        }
        dnaWriter.stopWriting();
    }
}
