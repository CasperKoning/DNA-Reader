package nl.casperkoning.dna.io.threads;

import java.util.concurrent.atomic.AtomicBoolean;

public class NotifyingObject {
    private final AtomicBoolean reading = new AtomicBoolean(true);

    public synchronized boolean isReading(){
        return reading.get();
    }

    public synchronized void stopReading(){
        this.reading.set(false);
    }

}
