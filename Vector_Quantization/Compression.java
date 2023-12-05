package Vector_Quantization;

import java.util.Vector;

public class Compression {
    private int subarraySize; // Variable to replace the hard-coded size

    public CompressionParsed compress(int[][] stream) {
        this.subarraySize = Math.max(Math.min(stream.length, stream[0].length)/100, 1);
        int rows = stream.length/subarraySize ;
        int cols = stream[0].length/subarraySize ;
        int[][][] image = devide(stream);
        Vector<Vector<int[][]>> temp = new Vector<>();
        Vector<int[][]> temp2 = new Vector<>();
        for (int i = 0; i < image.length; i++) temp2.add(image[i]);
        temp.add(temp2);
        Vector<int[][]> codeBook = new Vector<>();
        int[] compressed = new int[image.length];
        Vector<double[][]> averages = new Vector<>();
        double MSE = 60;
        int n = 0;
        while (MSE > 1&& n < 8) {
            n++;
            for (int i = 0; i < temp.size(); i++) {
                averages.add(average(temp.elementAt(i)));
            }
            codeBook.clear();
            for (int i = 0; i < averages.size(); i++) {
                int[][] lower = new int[subarraySize][subarraySize];
                int[][] upper = new int[subarraySize][subarraySize];
                for (int j = 0; j < subarraySize; j++) {
                    for (int k = 0; k < subarraySize; k++) {
                        lower[j][k] = (int) Math.ceil(averages.elementAt(i)[j][k] - 1);
                        upper[j][k] = (int) Math.floor(averages.elementAt(i)[j][k] + 1);
                    }
                }
                codeBook.add(lower);
                codeBook.add(upper);
            }
            averages.clear();
            temp.clear();
            for (int i = 0; i < codeBook.size(); i++) {
                Vector<int[][]> innerVector = new Vector<>();
                temp.add(innerVector);
            }
            for (int i = 0; i < image.length; i++) {
                int max = Integer.MAX_VALUE;
                int index = -1;
                for (int j = 0; j < codeBook.size(); j++) {
                    int t = distance(image[i], codeBook.elementAt(j));
                    if (max > t) {
                        max = t;
                        index = j;
                    }
                }
                compressed[i] = index;
                temp.elementAt(index).add(image[i]);
            }
            MSE = Mse(compressed, codeBook, image);
        }
         n = 0;
        while (MSE>1&&n < 100) {
            n++;
             Vector<int[][]> codeBook2 = new Vector<>();
             for (int i = 0; i < temp.size(); i++) {
                averages.add(average(temp.elementAt(i)));
            }
            for (int i = 0; i < averages.size(); i++) {
                int[][] newcodebook = new int[subarraySize][subarraySize];
                for (int j = 0; j < subarraySize; j++) {
                    for (int k = 0; k < subarraySize; k++) {
                        newcodebook[j][k] = (int) Math.round(averages.elementAt(i)[j][k]);
                        
                    }
                }
                codeBook2.add(newcodebook);
            }
            if(noChange(codeBook,codeBook2)){
                break;
            }
            else {
                averages.clear();
                temp.clear();
                for (int i = 0; i < codeBook2.size(); i++) {
                    Vector<int[][]> innerVector = new Vector<>();
                    temp.add(innerVector);
                }
                for (int i = 0; i < image.length; i++) {
                    int max = Integer.MAX_VALUE;
                    int index = -1;
                    for (int j = 0; j < codeBook2.size(); j++) {
                        int t = distance(image[i], codeBook2.elementAt(j));
                        if (max > t) {
                            max = t;
                            index = j;
                        }
                    }
                    compressed[i] = index;
                    temp.elementAt(index).add(image[i]);
                }
                codeBook.clear();
                for(int i=0;i<codeBook2.size();i++){
                    int [][] tempcodeBook=codeBook2.elementAt(i);
                    codeBook.add(tempcodeBook);
                }
            }
            MSE = Mse(compressed, codeBook, image);
        }
        CompressionParsed result = new CompressionParsed(codeBook, compressed, rows, cols);
        return result;
    }

    public int[][][] devide(int[][] stream) {
        int numRows = stream.length;
        int numCols = stream[0].length;
        int numRowSubarrays = numRows / subarraySize;
        int numColSubarrays = numCols / subarraySize;
        int[][][] result = new int[numRowSubarrays * numColSubarrays][subarraySize][subarraySize];
        for (int i = 0; i < numRowSubarrays; i++) {
            for (int j = 0; j < numColSubarrays; j++) {
                for (int k = 0; k < subarraySize; k++) {
                    for (int l = 0; l < subarraySize; l++) {
                        result[i * numColSubarrays + j][k][l] = stream[i * subarraySize + k][j * subarraySize + l];
                    }
                }
            }
        }
        return result;
    }

    public double[][] average(Vector<int[][]> stream) {
        double[][] result = new double[subarraySize][subarraySize];
        for (int i = 0; i < subarraySize; i++) {
            for (int j = 0; j < subarraySize; j++) {
                result[i][j] = 0;
                for (int k = 0; k < stream.size(); k++) {
                    result[i][j] += stream.elementAt(k)[i][j];
                }
                result[i][j] = result[i][j] / (double) stream.size();
            }
        }
        return result;
    }

    public int distance(int[][] right, int[][] left) {
        int sum = 0;
        for (int i = 0; i < subarraySize; i++) {
            for (int j = 0; j < subarraySize; j++) {
                sum += (int) Math.abs(left[i][j] - right[i][j]);
            }
        }
        return sum;
    }

    public int distanceSquare(int[][] right, int[][] left) {
        int sum = 0;
        for (int i = 0; i < subarraySize; i++) {
            for (int j = 0; j < subarraySize; j++) {
                sum += (int) Math.pow(left[i][j] - right[i][j],2);
            }
        }
        return sum;
    }
    public double Mse(int[] compressed, Vector<int[][]> codeBook, int[][][] image) {
        double sum = 0;
        for (int i = 0; i < image.length; i++) {
            sum +=distanceSquare(codeBook.elementAt(compressed[i]), image[i]);
        }
        
        sum = sum / (double)( image.length*subarraySize*subarraySize);
        System.out.println(sum);
        return sum;
    }
    public boolean noChange (Vector<int[][]>c1,Vector<int[][]>c2){
        for(int i=0;i<c1.size();i++){
            if(distance(c1.elementAt(i), c2.elementAt(i))!=0)return false;
        }
        return true;
    }
    public boolean nochangestream(int [] a,int[]b){
        for(int i=0;i<a.length;i++){
            if(a[i]!=b[i])return false;
        }
        return true;
    }
}
