package LZW;

import java.util.Vector;

public class Decompression {
    public String decompress(Vector<Integer> compressed){
        Vector<String> dictionary = new Vector<>() ;
        String result="";
        String temp ="";
        for(int i :compressed){
            if(i<=255){
                result += (char)i;
                if(temp.length()>0)
                dictionary.add(temp+(char)i);
                temp=""+(char)i;
            }
            else {
                if((i-256)==dictionary.size()){
                    dictionary.add(temp+temp.charAt(0));
                }
                result +=dictionary.get(i-256);
                if(!dictionary.contains(temp+dictionary.get(i-256).charAt(0)))
                dictionary.add(temp+dictionary.get(i-256).charAt(0));
                temp= dictionary.get(i-256);
            }
        }
        return result;
    }
}

        