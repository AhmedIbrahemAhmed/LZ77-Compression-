package standardHuffman;

public class Node implements Comparable<Node>{
    protected String ch = "";
    protected String code = "" ;
    protected int value ;
    protected Node left ;
    protected Node right ;
    public Node(){
        this.value = 0;
        this.left = null ;
        this.right = null ;
    }

    public Node(char ch, int value) {
        this.ch += ch;
        this.value = value;
        this.left = null ;
        this.right = null ;
    }
    public Node(String ch, int value, Node left, Node right) {
        this.ch += ch;
        this.value = value;
        this.left = left ;
        this.right = right ;
    }

    public String getCh() {
        return ch;
    }

    public void addChar(char ch) {
        this.ch += ch;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    public String getCode() {
        return code;
    }

    public void addCode(String code) {
        this.code += code;
    }

    @Override
    public int compareTo(Node node) {
        int compareValue = node.getValue();
        return this.value - compareValue;
    }
}
