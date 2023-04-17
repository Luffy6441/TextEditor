import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file; JMenu edit;
    JMenuItem newFile; JMenuItem openFile; JMenuItem saveFile;
    JMenuItem cut ; JMenuItem copy ; JMenuItem paste ; JMenuItem selectAll ; JMenuItem close;
    JTextArea textArea;
    TextEditor()
    {
        frame = new JFrame();

        menuBar = new JMenuBar();

        textArea = new JTextArea();
//        textArea.setLineWrap(true);


        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file Menu Items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add Action Listener to file menu Items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add the Items in file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit Menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add Action Listener to edit menu Items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add Items in edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add the component file and edit in menuBar
        menuBar.add(file);
        menuBar.add(edit);

        // Frame Adjustments

        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5 , 5 , 5 , 5));
        panel.setLayout(new BorderLayout(0 , 0));
        panel.add(textArea , BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

//        frame.add(new JScrollPane(textArea));
        frame.add(panel);
        frame.setTitle("Text Editor");
        Dimension min = new Dimension(700 ,500);
        frame.setLocationByPlatform(true);
        frame.setMinimumSize(min);
        frame.setResizable(false);
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (int) ((screen.getWidth() - getWidth()) /2);
//        int y = (int) ((screen.getHeight() - getHeight()) /2);
//        frame.setBounds(100 , 100 , 900 , 680);
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension max = toolkit.getScreenSize();
//        frame.setMaximumSize(max);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // Impelementing File menu ActionEvent
        if(actionEvent.getSource() == newFile)
        {
            new TextEditor();
        }

        if(actionEvent.getSource() == openFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");

            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try
                {
                    FileReader fileReader = new FileReader(filePath);

                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = "";
                    String output = "";

                    while((intermediate = bufferedReader.readLine()) != null)
                        output += intermediate + "\n";

                    textArea.setText(output);
                }
                catch(IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");

            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try
                {
                        FileWriter fileWriter = new FileWriter(file);

                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        textArea.write(bufferedWriter);

                        bufferedWriter.close();
                    }
                    catch(IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }
            }


        //Impelementing Edit menu ActionEvent
        if(actionEvent.getSource() == cut)
            textArea.cut();

        if(actionEvent.getSource() == copy)
            textArea.copy();

        if(actionEvent.getSource() == paste)
            textArea.paste();

        if(actionEvent.getSource() == selectAll)
            textArea.selectAll();

        if(actionEvent.getSource() == close)
            frame.dispose();
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}