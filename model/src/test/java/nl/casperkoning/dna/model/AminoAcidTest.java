package nl.casperkoning.dna.model;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AminoAcidTest {

    @Test
    public void testRegexps(){
        assertThat("TTT",matchesToRegex(AminoAcid.PHE.getBasePairsRegex()));
        assertThat("TTC",matchesToRegex(AminoAcid.PHE.getBasePairsRegex()));

        assertThat("CTT",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));
        assertThat("CTC",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));
        assertThat("CTA",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));
        assertThat("CTG",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));
        assertThat("TTA",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));
        assertThat("TTG",matchesToRegex(AminoAcid.LEU.getBasePairsRegex()));

        assertThat("TCT",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));
        assertThat("TCC",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));
        assertThat("TCA",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));
        assertThat("TCG",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));
        assertThat("AGC",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));
        assertThat("AGT",matchesToRegex(AminoAcid.SER.getBasePairsRegex()));

        assertThat("ATT",matchesToRegex(AminoAcid.ILE.getBasePairsRegex()));
        assertThat("ATC",matchesToRegex(AminoAcid.ILE.getBasePairsRegex()));
        assertThat("ATA",matchesToRegex(AminoAcid.ILE.getBasePairsRegex()));

        assertThat("ATG",matchesToRegex(AminoAcid.MET.getBasePairsRegex()));

        assertThat("GTT",matchesToRegex(AminoAcid.VAL.getBasePairsRegex()));
        assertThat("GTC",matchesToRegex(AminoAcid.VAL.getBasePairsRegex()));
        assertThat("GTA",matchesToRegex(AminoAcid.VAL.getBasePairsRegex()));
        assertThat("GTG",matchesToRegex(AminoAcid.VAL.getBasePairsRegex()));

        assertThat("CCT",matchesToRegex(AminoAcid.PRO.getBasePairsRegex()));
        assertThat("CCC",matchesToRegex(AminoAcid.PRO.getBasePairsRegex()));
        assertThat("CCA",matchesToRegex(AminoAcid.PRO.getBasePairsRegex()));
        assertThat("CCG",matchesToRegex(AminoAcid.PRO.getBasePairsRegex()));

        assertThat("ACT",matchesToRegex(AminoAcid.THR.getBasePairsRegex()));
        assertThat("ACC",matchesToRegex(AminoAcid.THR.getBasePairsRegex()));
        assertThat("ACA",matchesToRegex(AminoAcid.THR.getBasePairsRegex()));
        assertThat("ACG",matchesToRegex(AminoAcid.THR.getBasePairsRegex()));

        assertThat("GCT",matchesToRegex(AminoAcid.ALA.getBasePairsRegex()));
        assertThat("GCC",matchesToRegex(AminoAcid.ALA.getBasePairsRegex()));
        assertThat("GCA",matchesToRegex(AminoAcid.ALA.getBasePairsRegex()));
        assertThat("GCG",matchesToRegex(AminoAcid.ALA.getBasePairsRegex()));

        assertThat("TAT",matchesToRegex(AminoAcid.TYR.getBasePairsRegex()));
        assertThat("TAC",matchesToRegex(AminoAcid.TYR.getBasePairsRegex()));

        assertThat("TAA",matchesToRegex(AminoAcid.STOP.getBasePairsRegex()));
        assertThat("TAG",matchesToRegex(AminoAcid.STOP.getBasePairsRegex()));
        assertThat("TGA",matchesToRegex(AminoAcid.STOP.getBasePairsRegex()));

        assertThat("CAT",matchesToRegex(AminoAcid.HIS.getBasePairsRegex()));
        assertThat("CAC",matchesToRegex(AminoAcid.HIS.getBasePairsRegex()));

        assertThat("CAA",matchesToRegex(AminoAcid.GLN.getBasePairsRegex()));
        assertThat("CAG",matchesToRegex(AminoAcid.GLN.getBasePairsRegex()));

        assertThat("AAT",matchesToRegex(AminoAcid.ASN.getBasePairsRegex()));
        assertThat("AAC",matchesToRegex(AminoAcid.ASN.getBasePairsRegex()));

        assertThat("AAA",matchesToRegex(AminoAcid.LYS.getBasePairsRegex()));
        assertThat("AAG",matchesToRegex(AminoAcid.LYS.getBasePairsRegex()));

        assertThat("GAT",matchesToRegex(AminoAcid.ASP.getBasePairsRegex()));
        assertThat("GAC",matchesToRegex(AminoAcid.ASP.getBasePairsRegex()));

        assertThat("GAA",matchesToRegex(AminoAcid.GLU.getBasePairsRegex()));
        assertThat("GAG",matchesToRegex(AminoAcid.GLU.getBasePairsRegex()));

        assertThat("TGT",matchesToRegex(AminoAcid.CYS.getBasePairsRegex()));
        assertThat("TGC",matchesToRegex(AminoAcid.CYS.getBasePairsRegex()));

        assertThat("TGG",matchesToRegex(AminoAcid.TRP.getBasePairsRegex()));

        assertThat("CGT",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));
        assertThat("CGC",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));
        assertThat("CGA",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));
        assertThat("CGG",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));
        assertThat("AGA",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));
        assertThat("AGG",matchesToRegex(AminoAcid.ARG.getBasePairsRegex()));

        assertThat("GGT",matchesToRegex(AminoAcid.GLY.getBasePairsRegex()));
        assertThat("GGC",matchesToRegex(AminoAcid.GLY.getBasePairsRegex()));
        assertThat("GGA",matchesToRegex(AminoAcid.GLY.getBasePairsRegex()));
        assertThat("GGG",matchesToRegex(AminoAcid.GLY.getBasePairsRegex()));

    }

    @Test
    public void testStartCodon(){
        assertTrue(AminoAcid.MET.isStart());
    }

    @Test
    public void testStopCodon(){
        assertTrue(AminoAcid.STOP.isStop());
    }


    private Matcher<? super String> matchesToRegex(final String basePairsRegex) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return o instanceof String && ((String)o).matches(basePairsRegex);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("String that matches the regular expression " + basePairsRegex);
            }
        };
    }

}
