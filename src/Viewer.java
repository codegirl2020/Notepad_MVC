import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class Viewer {
    private JTextArea textArea;
    private JFrame frame;
    private Controller controller;
    private JDialog dialogFont;
    private JTextField textFieldFontName;
    private JTextField textFieldStyleName;
    private JTextField textFieldSizeName;
    private JDialog dialogFind;
    private JTextField textFieldFindText;
    private JLabel previewLabel;
    private Font font;
    private JList listFontName;
   private  JList listSizeName;
   private JList listStyleName;

    public Viewer() {
        controller = new Controller(this);

        Font fontForTextarea = new Font("Helvetica", Font.BOLD, 25);
        textArea = new JTextArea();
                textArea.setFont(fontForTextarea);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuItem menuItemNew = new JMenuItem("New ", new ImageIcon("images/new.gif"));
        menuItemNew.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        JMenuItem menuItemOpen = new JMenuItem("Open ", new ImageIcon("images/open.gif"));
        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItemOpen.addActionListener(controller);
        menuItemOpen.setActionCommand("Open_File");

        JMenuItem menuItemSave = new JMenuItem("Save ", new ImageIcon("images/save.gif"));
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItemSave.addActionListener(controller);
        menuItemSave.setActionCommand("Save_File");

        JMenuItem menuItemSaveAs = new JMenuItem("Save As ", new ImageIcon("images/save_as.gif"));
        JMenuItem menuItemPrint = new JMenuItem("Print ", new ImageIcon("images/print.gif"));
        menuItemPrint.addActionListener(controller);
        menuItemPrint.setActionCommand("Print_Document");

        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(controller);
        menuItemExit.setActionCommand("Close_File");

        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.add(menuItemNew);
        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemSaveAs);
        menuFile.add(new JSeparator());
        menuFile.add(menuItemPrint);
        menuFile.add(new JSeparator());
        menuFile.add(menuItemExit);

        JMenuItem menuItemCut = new JMenuItem("Cut", new ImageIcon("images/cut.gif"));
        menuItemCut.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuItemCut.addActionListener(controller);
        menuItemCut.setActionCommand("Cut_Text");
        JMenuItem menuItemCopy = new JMenuItem("Copy", new ImageIcon("images/copy.gif"));
        menuItemCopy.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItemCopy.addActionListener(controller);
        menuItemCopy.setActionCommand("Copy_Text");
        JMenuItem menuItemPaste = new JMenuItem("Paste", new ImageIcon("images/past.gif"));
        menuItemPaste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItemPaste.addActionListener(controller);
        menuItemPaste.setActionCommand("Paste_Text");
        JMenuItem menuItemClear = new JMenuItem("Clear", new ImageIcon("images/delit.gif"));
        menuItemClear.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, 0));
        menuItemClear.addActionListener(controller);
        menuItemClear.setActionCommand("Clear_Text");

        JMenuItem menuItemFind = new JMenuItem("Find", new ImageIcon("images/find.gif"));
        menuItemFind.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        menuItemFind.addActionListener(controller);
        menuItemFind.setActionCommand("Find_Text");

        JMenuItem menuItemFindMore = new JMenuItem("Find More");
        menuItemFindMore.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F3, 0));
        menuItemSaveAs.addActionListener(controller);
        menuItemSaveAs.setActionCommand("SaveAs_Text");

        JMenuItem menuItemGo = new JMenuItem("Go", new ImageIcon("images/go.gif"));
        menuItemGo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        JMenuItem menuItemMarkerAll = new JMenuItem("Marker All", new ImageIcon("images/marker.gif"));
        menuItemMarkerAll.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        JMenuItem menuItemTime_Date = new JMenuItem("Time and date", new ImageIcon("images/time.gif"));
        menuItemTime_Date.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F5, 0));
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic(KeyEvent.VK_E);
        menuEdit.add(menuItemCut);
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemPaste);
        menuEdit.add(menuItemClear);
        menuEdit.add(new JSeparator());
        menuEdit.add(menuItemFind);
        menuEdit.add(menuItemFindMore);
        menuEdit.add(menuItemGo);
        menuEdit.add(new JSeparator());
        menuEdit.add(menuItemMarkerAll);
        menuEdit.add(menuItemTime_Date);

        JMenuItem menuItemWordSpace = new JMenuItem("Word Space", new ImageIcon("images/wordSpace.gif"));
        JMenuItem menuItemFont = new JMenuItem("Font", new ImageIcon("images/font.gif"));
        menuItemFont.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        menuItemFont.addActionListener(controller);
        menuItemFont.setActionCommand("Font_Select");

        JMenu menuFormat = new JMenu("Format");
        menuFormat.setMnemonic(KeyEvent.VK_R);
        menuFormat.add(menuItemWordSpace);
        menuFormat.add(menuItemFont);

        JMenuItem menuItemStatusSpace = new JMenuItem("Status Space", new ImageIcon("images/options.gif"));
        JMenu menuView = new JMenu("View");
        menuView.setMnemonic(KeyEvent.VK_V);
        menuView.add(menuItemStatusSpace);
        JMenuItem menuItemViewHelp = new JMenuItem("View Help");
        menuItemViewHelp.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        menuHelp.add(menuItemViewHelp);
        menuHelp.add(menuItemAbout);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

        frame = new JFrame("Notepad");
        frame.setSize(800, 800);
        frame.setLocation(300, 50);
        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void update(String text) {
        textArea.setText(text);
    }

    public String getTextFromTextarea() {
        return textArea.getText();
    }

    public void doAction(String command) {
        switch (command) {
            case "Copy_Text":
                textArea.copy();
                break;
            case "Cut_Text":
                textArea.cut();
                break;
            case "Paste_Text":
                textArea.paste();
                break;
            case "Clear_Text":
                textArea.replaceSelection("");
                break;
        }
    }

    public void selectedFont(String fontName){
        textFieldFontName.setText(fontName);
    }
    public void selectedFontStyle(String styleFont) {
        textFieldStyleName.setText(styleFont);
    }
    public void selectedFontSize(String sizeFont) {
        textFieldSizeName.setText(sizeFont);
    }

    public void fontSelect() {
        JFontChooser fontChooser = new JFontChooser();
        fontChooser.setVisible(true);

        //Font
//        JLabel labelFontName = new JLabel("Font");
//        labelFontName.setBounds(50, 50, 150, 40);
//        textFieldFontName = new JTextField();
//        textFieldFontName.setBounds(50, 80, 150, 40);
//        GraphicsEnvironment ob = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String [] nameFontName= ob.getAvailableFontFamilyNames();
//        listFontName  = new JList(nameFontName);
//        listFontName = new JList(nameFontName); //data has type Object[]
//        listFontName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        listFontName.setLayoutOrientation(JList.VERTICAL);
//        listFontName.setVisibleRowCount(-1);
//        listFontName.addListSelectionListener(controller);
//        listFontName.setName("FontNameChange");
//        JScrollPane listFontScroller = new JScrollPane(listFontName);
//        listFontScroller.setBounds(50, 125, 150, 150);
//
//        //Style
//        JLabel labelStyleName = new JLabel("Style");
//        labelStyleName.setBounds(220, 50, 150, 40);
//        textFieldStyleName = new JTextField();
//        textFieldStyleName.setBounds(220, 80, 150, 40);
//        String[] nameStyleName = {"Plain", "Bold", "Italic", "Bold Italic"};
//        listStyleName  = new JList(nameStyleName);
//        listStyleName = new JList(nameStyleName); //data has type Object[]
//        listStyleName.setModel(new javax.swing.AbstractListModel(){
//            String[] nameStyleName = {"Plain", "Bold", "Italic", "Bold Italic"};
//            public int getSize(){return nameStyleName.length;}
//            public Object getElementAt(int i){
//                return nameStyleName[i];
//            }
//        });
//        listStyleName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        listStyleName.setLayoutOrientation(JList.VERTICAL);
//        listStyleName.setVisibleRowCount(-1);
//        listStyleName.addListSelectionListener(controller);
//        listStyleName.setName("StyleNameChange");
//        JScrollPane listStyleScroller = new JScrollPane(listStyleName);
//        listStyleScroller.setBounds(220, 125, 150, 150);
//
//        //Size
//        JLabel labelSizeName = new JLabel("Size");
//        labelSizeName.setBounds(390, 50, 150, 40);
//        textFieldSizeName = new JTextField();
//        textFieldSizeName.setBounds(390, 80, 100, 40);
//        String[] nameSizeName = {"8", "10", "12", "14", "16",
//                "18","20","24","28","36","48","72", "96"};
//        JList listSizeName  = new JList(nameSizeName);
//        listSizeName = new JList(nameSizeName); //data has type Object[]
//        listSizeName.setModel(new javax.swing.AbstractListModel(){
//            String[] nameSizeName = {"8", "10", "12", "14", "16",
//                    "18","20","24","28","36","48","72", "96"};
//            public int getSize() {return nameSizeName.length;}
//            public Object getElementAt(int i){return nameSizeName[i];}
//        });
//        listSizeName.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        listSizeName.setLayoutOrientation(JList.VERTICAL);
//        listSizeName.setVisibleRowCount(-1);
//        listSizeName.addListSelectionListener(controller);
//        listSizeName.setName("SizeChange");
//        JScrollPane listSizeScroller = new JScrollPane(listSizeName);
//        listSizeScroller.setBounds(390, 125, 100, 150);
//
//
//        previewLabel = new JLabel();
//        previewLabel.setFont(new Font("Dialog", 0, 14));
//        previewLabel.setText("ABCDEFG abcdefg");
//        previewLabel.setBounds(250, 290, 150, 100);
//        previewLabel.setForeground(Color.BLUE);
//
//
//
//        if (dialogFont == null) {
//            JButton okButton = new JButton("Ok");
//            okButton.setBounds(250, 400, 120, 50);
//            okButton.setBackground(Color.BLUE);
//            okButton.setOpaque(true);
//            okButton.setBorderPainted(false);
//            okButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
//            okButton.setForeground(Color.WHITE);
//            okButton.addActionListener(controller);
//            okButton.setActionCommand("Change_Font");
//
//            JButton cancelButton = new JButton("Cancel");
//            cancelButton.setBounds(380, 400, 120, 50);
//            cancelButton.setBackground(Color.BLUE);
//            cancelButton.setOpaque(true);
//            cancelButton.setBorderPainted(false);
//            cancelButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
//            cancelButton.setForeground(Color.WHITE);
//            cancelButton.addActionListener(controller);
//            cancelButton.setActionCommand("Close_Dialog");
//
//            int x = frame.getX();
//            int y = frame.getY();
//            dialogFont = new JDialog(frame, "Select Font", true);
//            dialogFont.setSize(600, 600);
//            dialogFont.setResizable(false);
//            dialogFont.setLayout(null);
//            dialogFont.setLocation(x + 100, y + 100);
//            dialogFont.add(labelFontName);
//            dialogFont.add(textFieldFontName);
//            dialogFont.add(listFontScroller);
//            dialogFont.add(labelStyleName);
//            dialogFont.add(textFieldStyleName);
//            dialogFont.add(listStyleScroller);
//            dialogFont.add(labelSizeName);
//            dialogFont.add(textFieldSizeName);
//            dialogFont.add(listSizeScroller);
//            dialogFont.add(okButton);
//            dialogFont.add(cancelButton);
//            dialogFont.add( previewLabel);
//
//
//            dialogFont.setVisible(true);
//        } else {
//            int x = frame.getX();
//            int y = frame.getY();
//            dialogFont.setLocation(x + 100, y + 100);
//            dialogFont.setVisible(true);
//        }
    }

    public void closeDialog() {
        dialogFont.setVisible(false);
    }

    public void changeFont(Font font) {
        textArea.setFont(font);
    }


    public void openFindDialog() {
        int x = frame.getX();
        int y = frame.getY();

        JLabel labelWhat = new JLabel("What:");
        labelWhat.setBounds(20, 70, 50, 50);

        textFieldFindText = new JTextField();
        textFieldFindText.setBounds(70, 70, 150, 50);

        JButton buttonFindNext = new JButton("Find Next ....");
        buttonFindNext.setBounds(230, 50, 100, 50);
        buttonFindNext.addActionListener(controller);
        buttonFindNext.setActionCommand("FindText_FromTextarea");

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(230, 100, 100, 50);
        buttonCancel.addActionListener(controller);
        buttonCancel.setActionCommand("Close_FindDialog");

        dialogFind = new JDialog(frame, "Find", false);
        dialogFind.setSize(400, 250);
        dialogFind.setLocation(x+150, y+150);
        dialogFind.setLayout(null);
        dialogFind.add(labelWhat);
        dialogFind.add(textFieldFindText);
        dialogFind.add(buttonFindNext);
        dialogFind.add(buttonCancel);
        dialogFind.setVisible(true);


    }
    public void closeFindDialog(){
        dialogFind.setVisible(false);
    }
    public String getFindText(){
        return textFieldFindText.getText();
    }

    public void findTextInTextarea(String text)  {
        String searchTerm = textArea.getText();
        if(searchTerm.contains(text)){
            Highlighter highlighter = textArea.getHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
            int p0 =  textArea.getText().indexOf(textFieldFindText.getText());
            int p1 = p0 + textFieldFindText.getText().length();

            try {
                highlighter.removeAllHighlights();
                highlighter.addHighlight(p0, p1, painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(frame,
                    "Word is not found",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public void previewSizeValueChanged(Font font) {
        previewLabel.setFont(font);
    }

    public void previewStyleValueChanged(Font font) {
        previewLabel.setFont(font);
    }

    public void previewFontValueChanged(Font font) {
        previewLabel.setFont(font);
    }
}



