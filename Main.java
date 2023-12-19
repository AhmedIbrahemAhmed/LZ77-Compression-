import twoDimensionsPredictiveCoding.* ;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        File_handler handler = new File_handler();

        int[][] original = handler.Read_Original("E:\\Data Compression\\Assignment1\\LZ77\\mytest.jpg");
        Compression compression = new Compression();
        int[][] quantized = compression.compress(original) ;
        handler.write_compressed(quantized,"E:\\Data Compression\\Assignment1\\LZ77\\mytest2compressed1");
        int[][] compressed = handler.Read_compressed("E:\\Data Compression\\Assignment1\\LZ77\\mytest2compressed1");
        Decompression decompression = new Decompression();
        int[][] decompressed = decompression.decompress(compressed) ;
        handler.Write_Decompressed(decompressed,"E:\\Data Compression\\Assignment1\\LZ77\\mydecompressedtest1file");

//        SwingUtilities.invokeLater(() -> {
//           showMainFrame();
//            });
    }
   
//    private static void showchooseFrame(Path path){
//        File_handler handler=new File_handler();
//        JFrame frame = new JFrame("Operation Choose Frame");
//        frame.setSize(1000, 750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        JButton compressionButton = new JButton("Compression");
//        JButton decompressionButton = new JButton("Decompression");
//        JButton back =new JButton("Back");
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false);
//                showMainFrame();
//            }
//        });
//        decompressionButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Image temp2= new Image(null,null,null);
//                try{
//                CompressionParsedRPG RPG2=handler.Read_compressed(path);
//                Decompression decompression = new Decompression() ;
//                temp2.setRed(decompression.decompress(RPG2.getRed()) );
//                temp2.setGreen(decompression.decompress(RPG2.getGreen()));
//                temp2.setBlue(decompression.decompress(RPG2.getBlue()));
//
//                }catch (Exception ex) {
//                    JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                frame.setVisible(false);
//                saveDecompressed(temp2);
//            }
//        });
//        compressionButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Image stream=new Image(null, null, null);
//                try{
//                stream= handler.Read_Original(path.toString());
//                }catch (Exception ex) {
//                    JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                Compression compression= new Compression();
//                CompressionParsed red= compression.compress(stream.getRed());
//                CompressionParsed green= compression.compress(stream.getGreen());
//                CompressionParsed blue= compression.compress(stream.getBlue());
//                CompressionParsedRPG RPG= new CompressionParsedRPG(red, green, blue);
//                frame.setVisible(false);
//                saveCompressResult(RPG);
//            }
//        });
//
//        panel.add(compressionButton);
//        panel.add(decompressionButton);
//        panel.add(back);
//        frame.add(panel);
//
//        frame.setVisible(true);
//    }
//    private static void showMainFrame(){
//
//        JFrame frame = new JFrame("File Chooser ");
//            frame.setSize(1000, 750);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            JPanel panel = new JPanel();
//            JButton chooseButton = new JButton("Choose File");
//
//            chooseButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    JFileChooser fileChooser = new JFileChooser();
//                    int result = fileChooser.showOpenDialog(frame);
//
//                    if (result == JFileChooser.APPROVE_OPTION) {
//                        File selectedFile = fileChooser.getSelectedFile();
//                        frame.setVisible(false);
//                        showchooseFrame(selectedFile.toPath());
//                    }
//
//                }
//            });
//
//            panel.add(chooseButton);
//            frame.add(panel);
//
//            frame.setVisible(true);
//    }
//
//    private static void saveDecompressed (Image stream){
//        JFrame frame = new JFrame("Save Decompress Result");
//        frame.setSize(1000, 750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//
//        JTextField inputField = new JTextField(20);
//        JButton chooseFolderButton = new JButton("Choose Folder");
//            chooseFolderButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    JFileChooser folderChooser = new JFileChooser();
//                    folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                    int result = folderChooser.showDialog(frame, "Choose Folder");
//
//                    if (result == JFileChooser.APPROVE_OPTION) {
//                       try{
//                        File selectedFolder = folderChooser.getSelectedFile();
//
//                        String temp= selectedFolder.getAbsolutePath()+"/"+inputField.getText();
//                        File_handler handler=new File_handler();
//                        handler.Write_Decompressed(stream,temp);
//                       }
//                       catch(Exception ex) {
//                        JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                    frame.setVisible(false);
//                    showMainFrame();
//                    }
//                }
//            });
//        panel.add(inputField);
//        panel.add(chooseFolderButton);
//        frame.add(panel);
//
//        frame.setVisible(true);
//    }
//    private static void saveCompressResult(CompressionParsedRPG stream){
//        JFrame frame = new JFrame("Save Compress Result");
//        frame.setSize(1000, 750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//
//        JTextField inputField = new JTextField(20);
//        JButton chooseFolderButton = new JButton("Choose Folder");
//            chooseFolderButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    JFileChooser folderChooser = new JFileChooser();
//                    folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                    int result = folderChooser.showDialog(frame, "Choose Folder");
//
//                    if (result == JFileChooser.APPROVE_OPTION) {
//                       try{
//                        File selectedFolder = folderChooser.getSelectedFile();
//
//                        String temp= selectedFolder.getAbsolutePath()+"/"+inputField.getText();
//                        File_handler handler=new File_handler();
//                        handler.Write_Compressed(stream,temp);
//                       }
//                       catch(Exception ex) {
//                        JOptionPane.showMessageDialog(frame, "Error selecting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                    frame.setVisible(false);
//                    showMainFrame();
//                }
//            }
//        });
//    panel.add(inputField);
//    panel.add(chooseFolderButton);
//    frame.add(panel);
//
//    frame.setVisible(true);
//    }
}