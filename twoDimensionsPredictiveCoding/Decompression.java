package twoDimensionsPredictiveCoding;

import java.util.Vector;

public class Decompression {
   public int[][] decompress(int[][] quantizedDiff){
       int[][] dequantized = new int[quantizedDiff.length][quantizedDiff[0].length] ;
       int[][] decoded = new int[quantizedDiff.length][quantizedDiff[0].length]  ;
       int[][] predicted = new int[quantizedDiff.length][quantizedDiff[0].length]   ;
       for(int i=0;i<quantizedDiff.length;i++){
           decoded[i][0] = quantizedDiff[i][0] ;
       }
       for(int i=0;i< quantizedDiff[0].length;i++){
           decoded[0][i] = quantizedDiff[0][i] ;
       }
       for(int i=1;i<quantizedDiff.length;i++){
           for(int j=1;j<quantizedDiff[0].length;j++){
               int a = decoded[i][j-1] ;
               int b = decoded[i-1][j-1] ;
               int c = decoded[i-1][j] ;
               int temp  ;
               if(b<= Math.min(a,c))
                   temp = Math.max(a,c) ;
               else if(b>= Math.max(a,c))
                   temp = Math.min(a,c) ;
               else
                   temp = a+c-b ;
               predicted[i][j] = temp ;
               dequantized[i][j] = quantizedDiff[i][j] * 10 ;
               decoded[i][j] = predicted[i][j] + dequantized[i][j] ;
           }
       }
//       for(int i=0;i<decoded.length;i++){
//           for(int j=0;j<decoded[0].length;j++){
//               System.out.print(decoded[i][j] + " ");
//           }
//           System.out.println();
//       }
       return decoded ;
   }

}
