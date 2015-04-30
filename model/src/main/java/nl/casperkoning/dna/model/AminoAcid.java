package nl.casperkoning.dna.model;

public enum AminoAcid {
    PHE("TT[TC]"),
    LEU("(CT[TCAG])|(TT[AG])"),
    SER("(TC[TCAG])|(AG[CT])"),
    ILE("AT[TCA]"),
    MET("ATG"),
    VAL("GT[TCAG]"),
    PRO("CC[TCAG]"),
    THR("AC[TCAG]"),
    ALA("GC[TCAG]"),
    TYR("TA[TC]"),
    STOP("(TA[AG])|(TGA)"),
    HIS("CA[TC]"),
    GLN("CA[AG]"),
    ASN("AA[TC]"),
    LYS("AA[AG]"),
    ASP("GA[TC]"),
    GLU("GA[AG]"),
    CYS("TG[TC]"),
    TRP("TGG"),
    ARG("(CG[TCAG])|(AG[AG])"),
    GLY("GG[TCAG]");

    public static final int NUMBER_OF_BASES_IN_AN_AMINO_ACID = 3;

    private final String basePairsRegex;

    private AminoAcid(String basePairsRegex) {
        this.basePairsRegex = basePairsRegex;
    }

    public String getBasePairsRegex() {
        return basePairsRegex;
    }

    public boolean isStart() {
        return this.name().equals("MET");
    }

    public boolean isStop() {
        return this.name().equals("STOP");
    }

}
