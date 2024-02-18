import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotePad extends JFrame implements ActionListener {

    private JTextArea textarea;
    private JMenuBar menubar;
    private JMenu file;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem exit;

    public NotePad() {
        super("Sample Note Bad"); // name of the program
        textarea = new JTextArea();
        menubar = new JMenuBar();
        file = new JMenu("File"); // Create a button on the menu
        add(textarea);
        setJMenuBar(menubar);
        open = new JMenuItem("Open", 'O'); // Create item names open and mnemonic O
        open.addActionListener(this); // means ActionListener exists in this class
        open.setActionCommand("O");  // Open equal O in ActionCommand

        save = new JMenuItem("Save", 'S'); // Create item names save and mnemonic S
        save.addActionListener(this);
        save.setActionCommand("S"); // save equal S in ActionCommand

        exit = new JMenuItem("Exit", 'X'); // Create item exit open and mnemonic E
        exit.addActionListener(this);
        exit.setActionCommand("E"); // exit equal E in ActionCommand
        file.add(open); // add open in file
        file.add(save); // add save in file
        file.addSeparator(); // means new line
        file.add(exit); // add exit in file

        menubar.add(file); // add file in menubar
        setSize(500, 500); // set the size of program
        setLocation(400, 150); // set the location of program on computer
        setDefaultCloseOperation(EXIT_ON_CLOSE); // when click close knows us on the IDE

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand()) { //knows What's the user clicked
            case "O":  // if the user clicked open
                openfile(); // this uses
                break; // then stopped the case
            case "S": // if the user clicked save
                savefile();  // this uses
                break; // then stopped the case
            case "E": // if the user clicked exit
                System.exit(0); // this uses
                break; // then stopped the case
        }
    }

    void openfile() {
        FileInputStream fis = null;
        JFileChooser f = new JFileChooser(); // create a JFileChooser Class
        int result = f.showOpenDialog(this); // opens on the same path IDE
        if (result == JFileChooser.APPROVE_OPTION) { // if the user chooses File
            try { // try this
                String path = f.getSelectedFile().getPath(); // knows path of file
                fis = new FileInputStream(path); // works on the same path for user
                int size = fis.available();
                byte[] b = new byte[size];
                fis.read(b); // reads a file
                textarea.setText(new String(b)); // create a String method for organize char
            } catch (FileNotFoundException e) { // first catch
                e.printStackTrace();
            } catch (IOException e) { // second catch
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    void savefile() {
        JFileChooser j = new JFileChooser(); // Create a JFileChooser Class
        int result = j.showSaveDialog(this); // saves on the same path IDE
        if (result == JFileChooser.APPROVE_OPTION) {
            FileOutputStream fos =null;
            String path = j.getSelectedFile().getPath(); // knows path of file
            try {
                fos = new FileOutputStream(path);
                byte[] b = textarea.getText().getBytes(); // convert text to byte in the array
                fos.write(b);
            } catch (FileNotFoundException e ){e.printStackTrace();}
              catch (IOException e ){e.printStackTrace();}
              finally
               {  try {
                   fos.close();
               } catch(IOException e){e.printStackTrace();}
               }


        }
    }
}

