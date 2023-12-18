package twoDimensionsPredictiveCoding;

import java.util.Vector;

public class Compression {
    private int subarraySize; // Variable to replace the hard-coded size

    public int[][] compress(int[][] stream) {
       int[][] predicted = new int[stream.length][stream[0].length]   ;
       int[][] difference = new int[stream.length][stream[0].length]  ;
       int[][] quantized = new int[stream.length][stream[0].length]  ;
       int[][] dequantized = new int[stream.length][stream[0].length] ;
       int[][] decoded = new int[stream.length][stream[0].length]  ;
       for(int i=0;i<stream.length;i++){
          decoded[i][0] = stream[i][0] ;
          quantized[i][0] = stream[i][0] ;
       }
       for(int i=0;i< stream[0].length;i++){
           decoded[0][i] = stream[0][i] ;
           quantized[0][i] = stream[0][i] ;
       }
       for(int i=1;i<stream.length;i++){
           for(int j=1;j<stream[0].length;j++){
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
               difference[i][j] = stream[i][j] - predicted[i][j] ;
               quantized[i][j] = (int)Math.floor((double)difference[i][j]/ 10 + 0.5) ;
               dequantized[i][j] = quantized[i][j] * 10 ;
               decoded[i][j] = predicted[i][j] + dequantized[i][j] ;
           }
       }
//       for(int i=0;i<decoded.length;i++){
//           for(int j=0;j<decoded[0].length;j++){
//               System.out.print(decoded[i][j] + " ");
//           }
//           System.out.println();
//       }
//       System.out.println("compression ended");
       return quantized ;
    }




}
