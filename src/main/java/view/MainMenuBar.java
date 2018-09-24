package view;

import controller.ComponentController;
import controller.GenDB2GEDCOM;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

class MainMenuBar extends JMenuBar implements ActionListener {
    // to be moved
    private JMenu menuFile = new JMenu("Conversions");
    private JMenuItem openFile = new JMenuItem("GenDB to GEDCOM");

    // gramps layout
    private JMenu menuFamilyTrees = new JMenu("Family Trees");
    private JMenuItem ft_item_manage = new JMenuItem("Manage Family Trees ...");
    private JMenuItem ft_item_openRecent = new JMenuItem("Open Recent");
    private JMenuItem ft_item_close = new JMenuItem("Close");
    private JMenuItem ft_item_import = new JMenuItem("Import ...");
    private JMenuItem ft_item_export = new JMenuItem("Export ...");
    private JMenuItem ft_item_exportView = new JMenuItem("Export View ...");
    private JMenuItem ft_item_makeBackup = new JMenuItem("Make Backup ...");
    private JMenuItem ft_item_abandonChange = new JMenuItem("Abandon Changes and Quit");
    private JMenuItem ft_item_quit = new JMenuItem("Quit ...");

    private JMenu menuAdd = new JMenu("Add");
    private JMenuItem ad_item_person = new JMenuItem("Person");
    private JMenuItem ad_item_family = new JMenuItem("Family");
    private JMenuItem ad_item_event = new JMenuItem("Event");
    private JMenuItem ad_item_place = new JMenuItem("Place");
    private JMenuItem ad_item_source = new JMenuItem("Source");
    private JMenuItem ad_item_citation = new JMenuItem("Citation");
    private JMenuItem ad_item_repository = new JMenuItem("Repository");
    private JMenuItem ad_item_media = new JMenuItem("Media");
    private JMenuItem ad_item_note = new JMenuItem("Note");

    private JMenu menuEdit = new JMenu("Edit");
    private JMenu menuView = new JMenu("View");
    private JMenu menuReports = new JMenu("Reports");
    private JMenu menuTools = new JMenu("Tools");
    private JMenu menuHelp = new JMenu("Help");

    private String lastdir = "";
    private File f = new File("server.properties");
    private InputStream is = null;
    private Properties properties = new Properties();
    private String database;
    private ComponentController componentController = ComponentController.getInstance();
    private JFrame mainFrame;

    public MainMenuBar() {
        super();
        mainFrame = (JFrame) this.getParent();

        // Main menu
        add(menuFamilyTrees);
        menuFamilyTrees.setMnemonic(KeyEvent.VK_F);
        menuFamilyTrees.add(ft_item_manage);
        ft_item_manage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        ft_item_manage.addActionListener(this);
        ft_item_manage.setActionCommand("manage");
        menuFamilyTrees.add(ft_item_openRecent);
        menuFamilyTrees.add(ft_item_close);
        ft_item_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        ft_item_close.addActionListener(this);
        ft_item_close.setActionCommand("close");
        menuFamilyTrees.addSeparator();
        menuFamilyTrees.add(ft_item_import);
        ft_item_import.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        ft_item_import.addActionListener(this);
        menuFamilyTrees.add(ft_item_export);
        ft_item_export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        ft_item_export.addActionListener(this);
        menuFamilyTrees.add(ft_item_exportView);
        menuFamilyTrees.add(ft_item_makeBackup);
        menuFamilyTrees.addSeparator();
        menuFamilyTrees.add(ft_item_abandonChange);
        menuFamilyTrees.add(ft_item_quit);
        ft_item_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        ft_item_quit.addActionListener(this);
        ft_item_quit.setActionCommand("quit");

        add(menuAdd);
        menuAdd.add(ad_item_person);
        ad_item_person.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_person.addActionListener(this);
        menuAdd.addSeparator();
        menuAdd.add(ad_item_family);
        ad_item_family.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_family.addActionListener(this);
        menuAdd.addSeparator();
        menuAdd.add(ad_item_event);
        ad_item_event.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_event.addActionListener(this);
        menuAdd.addSeparator();
        menuAdd.add(ad_item_place);
        ad_item_place.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_place.addActionListener(this);
        menuAdd.add(ad_item_source);
        ad_item_source.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_source.addActionListener(this);
        menuAdd.add(ad_item_citation);
        ad_item_citation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_citation.addActionListener(this);
        menuAdd.add(ad_item_repository);
        ad_item_repository.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_repository.addActionListener(this);
        menuAdd.add(ad_item_media);
        ad_item_media.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_media.addActionListener(this);
        menuAdd.add(ad_item_note);
        ad_item_note.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
        ad_item_note.addActionListener(this);

        add(menuEdit);
        add(menuView);
        add(menuReports);
        add(menuTools);
        add(menuHelp);

        add(menuFile);
        menuFile.setMnemonic(KeyEvent.VK_I);
        openFile.addActionListener(this);
        openFile.setMnemonic(KeyEvent.VK_S);
        openFile.setActionCommand("convert");
        menuFile.add(openFile);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // CONVERT GENDB TO GEDCOM
        if (e.getActionCommand().equals("convert")) {
            lastdir = properties.getProperty("lastdir");
            if (lastdir == null || lastdir.equals("")) {
                lastdir = "~";
            }
            JFileChooser fileChooser = new JFileChooser(lastdir);
            fileChooser.setDialogTitle("Select GenDB file");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("GenDB File", "gfdb");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(mainFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                lastdir = file.getParent();
                properties.setProperty("lastdir", lastdir);
                try {
                    OutputStream out = null;
                    out = new FileOutputStream(f);
                    properties.store(out, "This is an optional header comment string");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String filename = file.getAbsolutePath();
                JFileChooser fileChooser1 = new JFileChooser(lastdir);
                fileChooser1.setDialogTitle("GEDCOM file name to save to ...");
                FileNameExtensionFilter filter1 = new FileNameExtensionFilter("GEDCOM File", "ged");
                fileChooser.setFileFilter(filter1);
                int userSelection = fileChooser.showSaveDialog(mainFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    GenDB2GEDCOM.dothis(filename, fileToSave.getAbsolutePath());
                }
            }
            // QUIT
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("manage")) {
            // MANAGE DATABASES
            File dataDir = new File("data/");
            FilenameFilter textFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.endsWith(".ged")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            String[] filesInDir = dataDir.list(textFilter);
            Arrays.sort(filesInDir);
            database = (String) JOptionPane.showInputDialog(null, "Select database...", "Databases", JOptionPane.QUESTION_MESSAGE, null, filesInDir, filesInDir[0]);
            componentController.getDashBoardPanels().setVisible(true);
            System.out.println("set vis true");
        } else if (e.getActionCommand().equals("close")) {
            componentController.getMainpanel().setVisible(false);
            System.out.println("set vis false");
        } else {
            System.out.println(e.getActionCommand());
        }
    }
}
