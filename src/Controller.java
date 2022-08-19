import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Controller implements ActionListener, ListSelectionListener {

    private Viewer viewer;
    private JList list;
    private Font font;
    public Controller(Viewer viewer){

       this.viewer = viewer;
    }
    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();
        if(command.equals("Open_File")){
          openFile();
        } else if(command.equals("Save_File")){
           saveFile();
        } else if(command.equals("Print_Document")){
            printDocument();
        }
        else if(command.equals("Close_File")){
            closeFile();
        } else if(command.equals("Copy_Text")
                || (command.equals("Cut_Text")
                || (command.equals("Paste_Text")
                || (command.equals("Clear_Text"))))){
                    viewer.doAction(command);
        } else if(command.equals("Font_Select")){
            viewer.fontSelect();
        } else if(command.equals("Change_Font")){

            viewer.changeFont(font);
        } else if(command.equals("Find_Text")){
           viewer.openFindDialog();
        }else if(command.equals("Close_FindDialog")){
            viewer.closeFindDialog();
        }else if(command.equals("Close_Dialog")){
            viewer.closeDialog();
        }else if(command.equals("FindText_FromTextarea")){
           String text =  viewer.getFindText();
            viewer.findTextInTextarea(text);
        }
    }

    private void printDocument() {
        PrinterJob job = PrinterJob.getPrinterJob();
        String text = viewer.getTextFromTextarea();
        DocumentPrintable documentPrintable = new DocumentPrintable(text);
        job.setPrintable(documentPrintable);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                        "Document is printed");
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
    }

    private  void closeFile(){
        System.exit(0);
    }

    private void openFile(){
        String textFromFile = "";
       JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileInputStream in = new FileInputStream(file);
                int unicode;
                while ((unicode = in.read()) != -1) {
                    char symbol = (char) unicode;
                  textFromFile = textFromFile + symbol;
                } in.close();
                }catch(IOException ioe){
                System.out.println(ioe);
            }
        }
        viewer.update(textFromFile);
    }
    private void saveFile(){
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try{
                FileOutputStream out = new FileOutputStream(file);
                String textForSaving = viewer.getTextFromTextarea();
                for(int i = 0; i < textForSaving.length(); i++){
                    int unicode = textForSaving.charAt(i);
                    out.write(unicode);
                }
                out.flush();
                out.close();
            }catch (IOException ioe){
                System.out.println(ioe);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent event ) {
       list = (javax.swing.JList)event.getSource();
        String nameFont = (String)list.getSelectedValue();
        String styleFont = (String)list.getSelectedValue();
        String sizeFont = (String) list.getSelectedValue();


        String nameList = list.getName();
       if( nameList.equals("FontNameChange")){
            viewer.selectedFont(nameFont);
        }else if(nameList.equals("StyleNameChange")) {
           viewer.selectedFontStyle(styleFont);
       } else if(nameList.equals("SizeChange")){
            viewer.selectedFontSize(sizeFont);
        }
    }


//    private void listSizePreviewValueChanged(String sizeFont) {
//        int size = Integer.parseInt((String) list.getSelectedValue());
//        System.out.println(size);
//        font = new Font(font.getFamily(), font.getStyle(),size);
//        viewer.previewSizeValueChanged(font);
//
//    }
//    private void listStylePreviewValueChanged(String styleFont) {
//        int style = -1;
//        String setStyle = (String)list.getSelectedValue();
//        if(setStyle=="Plain")
//            style = Font.PLAIN;
//        else if(setStyle=="Bold")
//            style = Font.BOLD;
//        else if(setStyle=="Italic")
//            style = Font.ITALIC;
//        else if(setStyle=="Bold Italic")
//            style = Font.BOLD + Font.ITALIC;
//       font= new Font(font.getFamily(), style, font.getSize());
//      viewer.previewStyleValueChanged(font);
//    }
//    private void listFontPreviewValueChanged(String nameFont) {
//        String name = (String) list.getSelectedValue();
//        font = new Font(name, font.getStyle(),font.getSize());
//        viewer.previewFontValueChanged(font);
//
//    }
}

