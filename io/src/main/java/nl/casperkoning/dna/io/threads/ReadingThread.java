package nl.casperkoning.dna.io.threads;

import nl.casperkoning.dna.io.read.ProteinReader;
import nl.casperkoning.dna.model.Protein;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadingThread extends Thread {
    private final ProteinReader proteinReader;
    private final BlockingQueue<Protein> proteins;
    private final NotifyingObject notifyingObject;

    public ReadingThread(BlockingQueue<Protein> proteins, BufferedReader reader, NotifyingObject notifyingObject) {
        this.proteins = proteins;
        this.proteinReader = new ProteinReader(reader);
        this.notifyingObject = notifyingObject;
        this.setName("ReadingThread");
    }

    @Override
    public void run() {
        try {
            readDna();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void readDna() throws IOException, InterruptedException {
        Protein protein = proteinReader.read();
        Protein nextProtein = protein;
        if (nextProtein == null) {
            notifyingObject.stopReading();
        }
        while (notifyingObject.isReading() && nextProtein != null) {
            nextProtein = proteinReader.read();
            if (nextProtein == null) {
                notifyingObject.stopReading();
            }
            proteins.put(protein);
            protein = nextProtein;
        }
    }
}
