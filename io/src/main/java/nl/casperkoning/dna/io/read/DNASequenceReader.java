package nl.casperkoning.dna.io.read;

import java.io.BufferedReader;
import java.io.IOException;

class DNASequenceReader {
    private final BufferedReader reader;
    DNASequenceReader(BufferedReader reader) {
        this.reader = reader;
    }

    String read() throws IOException{
        int chr;
        if((chr = reader.read())!=-1){
            String base = String.valueOf((char)chr);
            if(!(base.equals("C") || base.equals("T") || base.equals("A") || base.equals("G"))){
                throw new IOException("This character does not represent a base: " + base);
            }
            return base;
        }else{
            return null;
        }
    }

    void mark(int readAheadLimit) throws IOException{
        reader.mark(readAheadLimit);
    }

    void reset() throws IOException {
        reader.reset();
    }
}
