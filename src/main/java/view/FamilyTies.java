package view;

import controller.ComponentController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class FamilyTies extends JFrame {
    ComponentController componentController = ComponentController.getInstance();
    MainPanel mainPanel = new MainPanel();
    ButtonPanel buttonPanel = new ButtonPanel(mainPanel);

    // menu bar
    MainMenuBar menuBar = new MainMenuBar();
    ToolBarMenu toolBarMenu = new ToolBarMenu();

    String lastdir = "";
    File f = new File("server.properties");
    InputStream is = null;
    Properties properties = new Properties();


    public FamilyTies() {
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
        catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
               ClassNotFoundException e) {
            // handle exception
        }

        setLayout(new MigLayout("", "[100]rel[grow, fill]", "[][grow, fill]"));
        componentController.setMainpanel(mainPanel);
        JPopupMenu popupMenu = new JPopupMenu();
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        setJMenuBar(menuBar);
        toolBarMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(toolBarMenu, "span 2, wrap");
        add(buttonPanel, "");
        add(mainPanel, "grow");
        mainPanel.add(new DashBoardPanels());

        ImageIcon gramps = new ImageIcon( ClassLoader.getSystemResource("gramps.png"));
        setIconImage(gramps.getImage());
        pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize,ySize);
        setVisible(true);
        setSize(1024, 768);

    }
 }

