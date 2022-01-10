package view;

import controller.ComponentController;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class GenDBLA {
    JFrame mainFrame = new JFrame();
    String lastdir = "";
    File f = new File("server.properties");
    InputStream is = null;
    Properties properties = new Properties();
    GenDBLAMainPanel mainPanel = new GenDBLAMainPanel();

    ComponentController componentController = ComponentController.getInstance();

    GenDBLA() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                     "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
            );
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
        mainFrame.setDefaultLookAndFeelDecorated(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            File f = new File("server.properties");
            is = new FileInputStream(f);
            if (is == null) {
                // Try loading from classpath
                is = getClass().getResourceAsStream("server.properties");
            }
            // Try loading properties from the file (if found)
            properties.load(is);
            lastdir = properties.getProperty("lastdir");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image gramps = toolkit.getImage( ClassLoader.getSystemResource("gramps.png"));
        mainFrame.setIconImage(gramps);
        mainFrame.setTitle("Genealogiese Familie Databasis");
        mainFrame.add(mainPanel);
        mainFrame.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = 700;
        int ySize = 100;
        mainFrame.setSize(xSize,ySize);
        mainFrame.setVisible(true);

    }

    static public void main(String[] args) {

        GenDBLA genDBLA = new GenDBLA();
    }
}
