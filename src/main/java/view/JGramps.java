package view;

import controller.ComponentController;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class JGramps {
    JFrame mainFrame = new JFrame();
    MainPanel mainPanel = new MainPanel();
    SidePanel sidePanel = new SidePanel();

    // menu bar
    MainMenuBar menuBar = new MainMenuBar();
    ToolBarMenu toolBarMenu = new ToolBarMenu();


    String lastdir = "";
    File f = new File("server.properties");
    InputStream is = null;
    Properties properties = new Properties();

    ComponentController componentController = ComponentController.getInstance();

    JGramps() {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    //"com.seaglasslookandfeel.siteskin"
                    //"javax.swing.plaf.metal.MetalLookAndFeel"
                    // UIManager.getSystemLookAndFeelClassName()
                    //"com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
                    //"com.sun.java.swing.plaf.motif.MotifLookAndFeel"
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
                    // UIManager.getCrossPlatformLookAndFeelClassName()
                    // UIManager.getSystemLookAndFeelClassName()
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

        componentController.setMainpanel(mainPanel);
        JPopupMenu popupMenu = new JPopupMenu();
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

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(toolBarMenu, BorderLayout.PAGE_START);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(150 + splitPane.getInsets().left);
        mainFrame.add(splitPane);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image gramps = toolkit.getImage( ClassLoader.getSystemResource("gramps.png"));
        mainFrame.setIconImage(gramps);
        mainFrame.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        mainFrame.setSize(xSize,ySize);
        mainFrame.show();
        //mainFrame.setSize(1024, 768);

    }

    static public void main(String[] args) {

        JGramps genDB2GEDCOM = new JGramps();
    }

 }

