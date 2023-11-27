package standardHuffman;

import java.util.HashMap;

public class CompressionParsed {
    protected HashMap<Character,Integer> frequencies ;
    protected String compressedStream ;

    public CompressionParsed(HashMap<Character, Integer> frequencies, String compressedStream) {
        this.frequencies = frequencies;
        this.compressedStream = compressedStream;
    }

    public HashMap<Character, Integer> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(HashMap<Character, Integer> frequencies) {
        this.frequencies = frequencies;
    }

    public String getCompressedStream() {
        return compressedStream;
    }

    public void setCompressedStream(String compressedStream) {
        this.compressedStream = compressedStream;
    }
}
