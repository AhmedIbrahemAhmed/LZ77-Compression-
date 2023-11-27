package standardHuffman;

import java.util.*;

public class CodeFinder {
//    method to get every character code given its frequency
    public Map<Character,String> find(Map<Character,Integer> frequencies){
//        this priority queue is used in constructing the huffman tree
        PriorityQueue<Node> huffman = new PriorityQueue<>() ;
//        the table of the characters and their codes
        HashMap<Character,String> table = new HashMap<>() ;
//        adding the frequencies to the priority queue
        for (Map.Entry<Character,Integer> entry : frequencies.entrySet()){
            Node node = new Node(entry.getKey(), entry.getValue()) ;
            huffman.add(node) ;
        }
//        merging two lowest frequencies into one node
        while(huffman.size()>2) {
            Node temp1 = huffman.poll();
            Node temp2 = huffman.poll();
//            merging the nodes in one node and keeping the two nodes as children for the new node
            Node parent = new Node(temp1.getCh() + temp2.getCh(), temp1.getValue() + temp2.getValue(), temp1, temp2);
            huffman.add(parent);
        }
//        the root for the huffman tree with two childs
        Node root = new Node() ;
        root.setLeft(huffman.poll());
        root.setRight(huffman.poll());
        codeHuffman(root,"",table);



        return table ;
    }
//    function to traverse the huffman tree and giving every character a code
    public void codeHuffman(Node root, String code, HashMap<Character,String> table){
        if(root.left== null && root.right==null) {
            root.code = code ;
            table.put(root.ch.charAt(0),code) ;
        }
        else{
            if(root.left!=null)
                codeHuffman(root.left,code+"1", table);
            if(root.right!=null)
               codeHuffman(root.right,code+"0", table);
        }
    }
}
