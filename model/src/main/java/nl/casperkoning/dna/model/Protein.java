package nl.casperkoning.dna.model;

import java.util.List;

public class Protein {
    private final List<AminoAcid> aminoAcids;

    public Protein(List<AminoAcid> aminoAcids){
        this.aminoAcids = aminoAcids;
    }

    public List<AminoAcid> getAminoAcids() {
        return aminoAcids;
    }

    @Override
    public String toString() {
        return "Protein{" +
                "aminoAcids=" + aminoAcids +
                '}';
    }

}
