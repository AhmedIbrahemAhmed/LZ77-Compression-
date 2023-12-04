package Vector_Quantization;

import java.util.Vector;

public class CompressionParsed {
    private Vector<int[][]> codeBook;
    private int []  compressedStream;
    public CompressionParsed(Vector<int[][]> codeBook, int[] compressedStream) {
            this.codeBook = codeBook;
            this.compressedStream = compressedStream;
    }
        public Vector<int[][]> getCodeBook() {
            return codeBook;
        }
        public void setCodeBook(Vector<int[][]> codeBook) {
            this.codeBook = codeBook;
        }
        public int[] getCompressedStream() {
            return compressedStream;
        }
        public void setCompressedStream(int[] compressedStream) {
            this.compressedStream = compressedStream;
        }
        public void printCodeBook() {
            for (int i = 0; i < codeBook.size(); i ++) {
               print2D(codeBook.elementAt(i));
            }
        }
        private void print2D(int [][] array){
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

