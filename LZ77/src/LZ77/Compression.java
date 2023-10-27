package LZ77;

import java.util.Vector;

public class Compression {
    public Vector<Tag> compress(String text , int dictionarySize,int WindowSize ){
        Vector<Tag> compressed = new Vector<>();
        if(dictionarySize==0)dictionarySize=text.length();
        if(WindowSize==0)WindowSize=text.length();
        compressed.add(new Tag(0, 0, text.charAt(0)));
        for(int i=1;i<=text.length()-1;i++){
            Tag temp= new Tag(0, 0, text.charAt(i));
            if(i==text.length()-1){
                compressed.add(temp);
                break;
            }    
            for (int j=Math.max(i-1,0);j>=Math.max(0,i-dictionarySize);j--){
                if(text.charAt(i)==text.charAt(j)){
                     Tag temp2= new Tag(1, i-j,text.charAt(i+1 ));
                    for(int k=1;k<=Math.min(text.length()-i-1,WindowSize)-1;k++){
                        
                        if(text.charAt(j+k)==text.charAt(i+k)){
                            temp2.setLength(temp2.getLength()+1);
                            temp2.setNext(text.charAt(i+k+1));
                        }
                        else break;
                    }
                    
                    
                    if(temp.getLength()<temp2.getLength()){
                       
                        temp.setLength(temp2.getLength());
                        
                        temp.setNext(temp2.getNext());
                        temp.setPosition(temp2.getPosition());
                    }
                    
                }
            }
            compressed.add(temp);
            i=i+temp.getLength();
        }
        return compressed;
    }
}
