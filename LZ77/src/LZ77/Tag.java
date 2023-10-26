package LZ77;

public class Tag {
    private int length ;
    private int position ;
    private char next ;
    public Tag(int length, int position, char next) {
        this.length = length;
        this.position = position;
        this.next = next;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public char getNext() {
        return next;
    }

    public void setNext(char next) {
        this.next = next;
    }


}
