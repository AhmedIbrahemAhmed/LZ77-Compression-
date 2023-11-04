package LZW;

import java.util.Vector;

public class Compression {
    public Vector<Integer> compress(String text){
        Vector<String> dictionary = new Vector<>() ;
        for(int i=0;i<256;i++){
            String temp = "" ;
            temp += (char)i ;
            dictionary.add(temp) ;
        }
        Vector<Integer> compressed = new Vector<>();

            for(int i=0;i<text.length();i++){
                String temp = "";
                for(int j=i;j<text.length();j++) {
                    temp += text.charAt(j);
                    if (!dictionary.contains(temp)) {
                        dictionary.add(temp) ;
                        i= j-1 ;
                        compressed.add(dictionary.indexOf(temp.substring(0,temp.length()-1)));
                        break ;
                    }
                    if(j==text.length()-1){
                        compressed.add(dictionary.indexOf(temp.substring(0,temp.length())));
                        return compressed ;
                        }

                }
            }
        return compressed;
    }
}