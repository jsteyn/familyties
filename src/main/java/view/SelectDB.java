package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class SelectDB extends JPanel implements ListSelectionListener, ActionListener {

    static private String database = null;
    static private JPanel panel;
    static private JList list;
    static private DefaultListModel listModel;
    static private File dataDir = new File("data/");
    static JButton loadTree = new JButton("Load Family Tree");

    SelectDB() {
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
        listModel = new DefaultListModel();
        for (int file = 0; file < filesInDir.length; file++) {
            listModel.addElement(filesInDir[file]);
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);

        loadTree.addActionListener(this);
        loadTree.setActionCommand("load");
        add(loadTree);
    }

    static public void createAndShowGUI() {
        JFrame frame = new JFrame("Manage databases");
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new SelectDB();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(600, 480));
    }

    static public String getDatabase() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        return database;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("load")) {
            database = (String) list.getSelectedValue();
//            System.out.println(database);

        }
    }
}
