package Vector_Quantization;

import java.util.Vector;

public class CompressionParsed {
    private Vector<int[][]> codeBook;
    private int []  compressedStream;

    private int rows ;
    private int cols ;
    public CompressionParsed(Vector<int[][]> codeBook, int[] compressedStream, int rows, int cols) {
            this.codeBook = codeBook;
            this.compressedStream = compressedStream;
            this.rows = rows ;
            this.cols = cols ;
    }
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
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
        public void printCompressedStream(){
            for(int i=0;i<compressedStream.length;i++){
                System.out.print(compressedStream[i]+" ");
            }
            System.out.println();
        }
    }

