package standardHuffman;

import java.util.HashMap;
import java.util.Map;

public class Decompression {
   public String decompress(CompressionParsed parsed){
    String result="";
    Map<Character,String> table = new HashMap<>() ;
    table= new CodeFinder().find(parsed.getFrequencies());
    String temp="";
   
    for(int i =0;i<parsed.getCompressedStream().length();i++){
        temp=temp+parsed.getCompressedStream().charAt(i);
        boolean f= false;
        for (Map.Entry<Character, String> entry : table.entrySet()) {
            if (temp.equals(entry.getValue())) {
                result=result+ entry.getKey();
                f=true;
                break;
            }
        }
        if(f){
            temp="";
        }
    }
    return result;
   }
}
