package nl.casperkoning.dna.io.read;

import nl.casperkoning.dna.model.AminoAcid;
import nl.casperkoning.dna.model.Protein;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProteinReader {
    private AminoAcidReader aminoAcidReader;

    public ProteinReader(AminoAcidReader aminoAcidReader) {
        this.aminoAcidReader = aminoAcidReader;
    }

    public Protein read() throws IOException {
        List<AminoAcid> aminoAcids = new ArrayList<>();
        AminoAcid start;
        if ((start = getNextStart()) != null) {
            aminoAcids.add(start);
        }
        AminoAcid aminoAcid;
        while ((aminoAcid = aminoAcidReader.read()) != null) {
            aminoAcids.add(aminoAcid);
            if (aminoAcid.isStop()) {
                break;
            }
        }
        if (!aminoAcids.isEmpty() && lastAminoAcidIsStop(aminoAcids)) {
            return new Protein(aminoAcids);
        } else {
            return null;
        }
    }

    private boolean lastAminoAcidIsStop(List<AminoAcid> aminoAcids) {
        return aminoAcids.get(aminoAcids.size()-1).isStop();
    }

    private AminoAcid getNextStart() throws IOException{
        aminoAcidReader.mark(1);
        AminoAcid aminoAcid;
        while((aminoAcid = aminoAcidReader.read())!=null){
            if(aminoAcid.isStart()){
                return aminoAcid;
            } else {
                aminoAcidReader.reset();
                aminoAcidReader.shiftCursorFoward(1);
                aminoAcidReader.mark(1);
            }
        }
        return null;
    }
}
