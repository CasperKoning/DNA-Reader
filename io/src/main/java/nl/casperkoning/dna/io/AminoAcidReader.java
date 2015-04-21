package nl.casperkoning.dna.io;

import nl.casperkoning.dna.model.AminoAcid;

import java.io.IOException;

public class AminoAcidReader {
    private DNASequenceReader dnaSequenceReader;

    public AminoAcidReader(DNASequenceReader dnaSequenceReader){
        this.dnaSequenceReader = dnaSequenceReader;
    }

    public AminoAcid read() throws IOException{
        String aminoAcidString = "";
        for(int i=0; i<AminoAcid.NUMBER_OF_BASES_IN_AN_AMINO_ACID;i++){
            String base;
            if((base = dnaSequenceReader.read())!=null){
                aminoAcidString += base;
            } else{
                return null;
            }
        }
        return matchToAminoAcid(aminoAcidString);
    }

    private AminoAcid matchToAminoAcid(String aminoAcidString) {
        for(AminoAcid aminoAcid : AminoAcid.values()){
            if(aminoAcidString.matches(aminoAcid.getBasePairsRegex())){
                return aminoAcid;
            }
        }
        return null;
    }

    public void shiftCursorFoward(int i) throws IOException {
        for(int j=0; j<i;j++){
            dnaSequenceReader.mark(1);
            if(dnaSequenceReader.read()==null){
                reset();//Can't move any further
            }
        }
    }

    public void reset() throws IOException {
        dnaSequenceReader.reset();
    }

    public void mark(int readAheadLimit) throws IOException {
        dnaSequenceReader.mark(AminoAcid.NUMBER_OF_BASES_IN_AN_AMINO_ACID*readAheadLimit);
    }
}
