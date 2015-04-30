package nl.casperkoning.dna.io.threads;

import nl.casperkoning.dna.io.read.ProteinReader;
import nl.casperkoning.dna.model.Protein;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadingThread extends Thread{
    private ProteinReader proteinReader;
    private BlockingQueue<Protein> proteins;
    private NotifyingObject notifyingObject;

    public ReadingThread(BlockingQueue<Protein> proteins, BufferedReader reader, NotifyingObject notifyingObject) throws IOException {
        this.proteins = proteins;
        this.proteinReader = new ProteinReader(reader);
        this.notifyingObject = notifyingObject;
        this.setName("ReadingThread");
    }

    @Override
    public void run() {
        try {
            Protein protein = proteinReader.read();
            Protein nextProtein = protein;
            if(nextProtein == null){
                notifyingObject.stopReading();
            }
            while(notifyingObject.isReading() && nextProtein!= null){
                nextProtein = proteinReader.read();
                if(nextProtein == null){
                    notifyingObject.stopReading();
                }
                proteins.put(protein);
                protein = nextProtein;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
