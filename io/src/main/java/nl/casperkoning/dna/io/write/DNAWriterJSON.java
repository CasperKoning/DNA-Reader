package nl.casperkoning.dna.io.write;

import nl.casperkoning.dna.model.AminoAcid;
import nl.casperkoning.dna.model.Protein;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.Writer;

public class DNAWriterJSON extends DNAWriter {
    private JsonGenerator jsonGenerator;

    public DNAWriterJSON(Writer writer) {
        super(writer);
    }

    @Override
    public void startWriting() {
        this.jsonGenerator = Json.createGenerator(super.getWriter());
        this.jsonGenerator.writeStartObject().writeStartArray("DNA");
    }

    @Override
    public void write(Protein protein) {
        this.jsonGenerator.writeStartObject().writeStartArray("Protein");
        for(AminoAcid aminoAcid : protein.getAminoAcids()){
            jsonGenerator.write(aminoAcid.name());
        }
        jsonGenerator.writeEnd().writeEnd();
    }

    @Override
    public void stopWriting() {
        if(this.jsonGenerator!=null) {
            this.jsonGenerator.writeEnd().writeEnd().flush();
            this.jsonGenerator.close();
        }
    }
}
