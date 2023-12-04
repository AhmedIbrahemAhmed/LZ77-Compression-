import Vector_Quantization.* ;
import Vector_Quantization.Compression;
import Vector_Quantization.File_handler;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        // SwingUtilities.invokeLater(() -> {
        //    showMainFrame();
        //     });
        File_handler file = new File_handler();
        int[][] temp = file.Read_Original("images.png") ;
        Compression compression= new Compression(2);
    CompressionParsed t= compression.compress(temp);
    Decompression decompression = new Decompression() ;
        int [][] temp2 = decompression.decompress(t) ;

        file.Write_Decompressed(temp2,"decompressed");
        for(int i=0;i<temp2.length;i++){
            for(int j=0;j<temp2[i].length;j++) {
                System.out.print(temp2[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
//    for(int i=0;i<t.getCompressedStream().length;i++){
//        System.out.print(t.getCompressedStream()[i]+" ");
//    }
//    System.out.println();
//    t.printCodeBook();
}
}
   
    // private static void showchooseFrame(Path path){
    //     File_handler handler=new File_handler();
    //     JFrame frame = new JFrame("Operation Choose Frame");
    //     frame.setSize(1000, 750);
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     JPanel panel = new JPanel();
    //     JButton compressionButton = new JButton("Compression");
    //     JButton decompressionButton = new JButton("Decompression");
    //     JButton back =new JButton("Back");

    //     back.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             frame.setVisible(false);
    //             showMainFrame();
    //         }
    //     });
    //     decompressionButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             String result="";
    //             try{
    //             CompressionParsed stream= handler.Read_compressed(path);
    //             Decompression decompression=new Decompression();
    //             result= decompression.decompress(stream);
    //             System.out.println(result);
    //             }catch (Exception ex) {
    //                 JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    //             }
    //             frame.setVisible(false);
    //             showDecompressResult(result);
    //         }
    //     });
    //     // compressionButton.addActionListener(new ActionListener() {
    //     //     @Override
    //     //     public void actionPerformed(ActionEvent e) {
    //     //         String stream="";
    //     //         try{
    //     //         stream= handler.Read_Original(path.toString());
    //     //         }catch (Exception ex) {
    //     //             JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    //     //         }
    //     //         Compression compression=new Compression();
    //     //         String result=compression.compress(stream);
    //     //         frame.setVisible(false);
    //     //         saveCompressResult(result);
    //     //     }
    //     // });

    //     panel.add(compressionButton);
    //     panel.add(decompressionButton);
    //     panel.add(back);
    //     frame.add(panel);

    //     frame.setVisible(true);
    // }
    // private static void showMainFrame(){
        
    //     JFrame frame = new JFrame("File Chooser ");
    //         frame.setSize(1000, 750);
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //         JPanel panel = new JPanel();
    //         JButton chooseButton = new JButton("Choose File");

    //         chooseButton.addActionListener(new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 JFileChooser fileChooser = new JFileChooser();
    //                 int result = fileChooser.showOpenDialog(frame);

    //                 if (result == JFileChooser.APPROVE_OPTION) {
    //                     File selectedFile = fileChooser.getSelectedFile();
    //                     frame.setVisible(false);
    //                     showchooseFrame(selectedFile.toPath());
    //                 }
                   
    //             }
    //         });

    //         panel.add(chooseButton);
    //         frame.add(panel);

    //         frame.setVisible(true);
    // }
    // private static void showDecompressResult(String result){
    //     JFrame frame = new JFrame("show Decompress Result");
    //     frame.setSize(1000, 750);
    //         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //         JTextArea textArea = new JTextArea();
    //         textArea.append(result);
    //         int textAreaWidth = frame.getWidth() / 2;
    //         int textAreaHeight = frame.getHeight() / 2;
    //         textArea.setSize(textAreaWidth, textAreaHeight);
            
            
    //         JPanel panel = new JPanel(new GridBagLayout());
    //         GridBagConstraints constraints = new GridBagConstraints();
    //         textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //         constraints.gridx = 0;
    //         constraints.gridy = 0;
    //         constraints.weightx = 1.0; // Expand horizontally
    //         constraints.weighty = 1.0; // Expand vertically
    //         constraints.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
    //         panel.add(new JScrollPane(textArea), constraints);
    //         JButton back = new JButton("Choose Another File");
    //         JButton save = new JButton("Save As File");
    //         back.addActionListener(new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 frame.setVisible(false);
    //                 showMainFrame();
    //             }
    //         });
    //         save.addActionListener(new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 frame.setVisible(false);
    //                 saveDecompressed(result);
    //             }
    //         });
    //         panel.add(save);
    //         panel.add(back);
    //         frame.add(panel);

    //         frame.setVisible(true);
    // }
                    
    // private static void saveDecompressed (String stream){
    //     JFrame frame = new JFrame("Save Decompress Result");
    //     frame.setSize(1000, 750);
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     JPanel panel = new JPanel();

    //     JTextField inputField = new JTextField(20);
    //     JButton chooseFolderButton = new JButton("Choose Folder");
    //         chooseFolderButton.addActionListener(new ActionListener() {
    //             @Override
    //             public void actionPerformed(ActionEvent e) {
    //                 JFileChooser folderChooser = new JFileChooser();
    //                 folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //                 int result = folderChooser.showDialog(frame, "Choose Folder");

    //                 if (result == JFileChooser.APPROVE_OPTION) {
    //                    try{
    //                     File selectedFolder = folderChooser.getSelectedFile();
                        
    //                     String temp= selectedFolder.getAbsolutePath()+"/"+inputField.getText();
    //                     File_handler handler=new File_handler();
    //                     handler.Write_Decompressed(stream,temp);
    //                    }
    //                    catch(Exception ex) {
    //                     JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    //                 }
    //                 frame.setVisible(false);
    //                 showMainFrame();
    //                 }
    //             }
    //         });
    //     panel.add(inputField);
    //     panel.add(chooseFolderButton);
    //     frame.add(panel);

    //     frame.setVisible(true);
    // }
//     private static void saveCompressResult(String stream){
//         JFrame frame = new JFrame("Save Compress Result");
//         frame.setSize(1000, 750);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JPanel panel = new JPanel();

//         JTextField inputField = new JTextField(20);
//         JButton chooseFolderButton = new JButton("Choose Folder");
//             chooseFolderButton.addActionListener(new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     JFileChooser folderChooser = new JFileChooser();
//                     folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                     int result = folderChooser.showDialog(frame, "Choose Folder");

//                     if (result == JFileChooser.APPROVE_OPTION) {
//                        try{
//                         File selectedFolder = folderChooser.getSelectedFile();
                        
//                         String temp= selectedFolder.getAbsolutePath()+"/"+inputField.getText();
//                         File_handler handler=new File_handler();
//                         handler.Write_Compressed(stream,temp);
//                        }
//                        catch(Exception ex) {
//                         JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                     }
//                     frame.setVisible(false);
//                     showMainFrame();
//                 }
//             }
//         });
//     panel.add(inputField);
//     panel.add(chooseFolderButton);
//     frame.add(panel);

//     frame.setVisible(true);
//     }
// }