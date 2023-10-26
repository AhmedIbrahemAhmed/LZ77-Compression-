package LZ77;

import java.util.Vector;

public class Decompression {
    public String decompress(Vector<Tag> tags){
        String result = "";
        result += tags.elementAt(0).getNext() ;
        int iterate = 1 ;
        for(int i=1;i<tags.size();i++){
            if(tags.elementAt(i).getPosition()>0){
                for(int j=0;j<tags.elementAt(i).getLength();j++){
                    result += result.charAt(iterate-tags.elementAt(i).getPosition() + j) ;

                }
                iterate+= tags.elementAt(i).getLength() ;
            }
            result += tags.elementAt(i).getNext() ;
            iterate++ ;
        }
        return result ;
    }
}
