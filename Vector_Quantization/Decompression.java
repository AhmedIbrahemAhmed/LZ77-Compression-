package Vector_Quantization;

import java.util.Vector;

public class Decompression {
   public int[][] decompress(CompressionParsed compressionParsed){
       Vector<int[][]> decompressed3d = new Vector<>() ;
       int rows = compressionParsed.getRows() ;
       int temp = compressionParsed.getCols() ;
       rows*= compressionParsed.getCodeBook().elementAt(0).length ;
       int cols = compressionParsed.getCols() ;
       cols*= compressionParsed.getCodeBook().elementAt(0)[0].length ;
       int[][] decompressed = new int[rows][cols] ;
       for(int i=0;i<compressionParsed.getCompressedStream().length;i++){
           decompressed3d.add(compressionParsed.getCodeBook().elementAt(compressionParsed.getCompressedStream()[i])) ;
       }


       int step = 0 ;
        for(int i=0;i<decompressed3d.size();i++){
            if(i%temp== 0 && i!= 0)
                step+= decompressed3d.elementAt(0).length ;
            for(int j=0;j<decompressed3d.elementAt(0).length;j++){
                for(int k=0;k<decompressed3d.elementAt(0)[0].length;k++){

                    decompressed[step + j][((i*decompressed3d.elementAt(0)[0].length) % decompressed[0].length) +k] = decompressed3d.elementAt(i)[j][k] ;
                }
            }
        }
        return decompressed ;
   }
   
}
