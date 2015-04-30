package nl.casperkoning.dna.io.write;

import nl.casperkoning.dna.model.Protein;

import java.io.Writer;

public abstract class DNAWriter {
    private final Writer writer;

    protected DNAWriter(Writer writer){
        this.writer = writer;
    }

    protected final Writer getWriter(){
        return this.writer;
    }

    public abstract void startWriting();

    public abstract void write(Protein protein);

    public abstract void stopWriting();
}