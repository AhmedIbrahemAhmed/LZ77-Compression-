package standardHuffman;

import java.util.HashMap;
import java.util.Map;

public class Compression {
    public String compress(String data){
        Map<Character,Integer> frequencies =  new HashMap<>() ;
        Map<Character,String> table;
        StringBuilder compressed = new StringBuilder();
        StringBuilder compressedStream = new StringBuilder();
        for(int i=0;i<data.length();i++){
            frequencies.merge(data.charAt(i),1,Integer::sum) ;
        }
        int numberOfCharacters = frequencies.size();
        String temp = Integer.toBinaryString(numberOfCharacters) ;
        temp = String.format("%7s",temp).replaceAll(" ", "0") ;
        compressed.append(temp) ;
        for (Map.Entry<Character,Integer> entry : frequencies.entrySet()) {
            String character = Integer.toBinaryString(entry.getKey()) ;
            character = String.format("%7s",character).replaceAll(" ", "0") ;
            compressed.append(character) ;
            String freq = Integer.toBinaryString(entry.getValue()-1) ;
            freq = String.format("%4s",freq).replaceAll(" ", "0") ;
            compressed.append(freq) ;
        }
        CodeFinder finder = new CodeFinder();
        table = finder.find(frequencies);
        for(int i=0;i<data.length();i++){
            compressedStream.append(table.get(data.charAt(i)));
        }
        int numberOfAdditionalZeros = 8-( (3 + 7 + ( 11 * frequencies.size() ) + compressedStream.length() ) % 8 ) ;
        String binaryNumberOfZeros = Integer.toBinaryString(numberOfAdditionalZeros) ;
        binaryNumberOfZeros = String.format("%3s",binaryNumberOfZeros).replaceAll(" ", "0") ;
        compressed.append(binaryNumberOfZeros);
        compressed.append(compressedStream) ;

        for(int i=0;i<numberOfAdditionalZeros;i++){
            compressed.append('0') ;
        }
        return compressed.toString();
    }
}
