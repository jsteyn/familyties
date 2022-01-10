package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DashBoardPanels extends JPanel {
    JPanel topsurnames = new JPanel(new BorderLayout());
    JPanel welcome = new JPanel(new BorderLayout());
    JTextArea surnameArea = new JTextArea();
    JTextArea welcomeArea = new JTextArea();


    DashBoardPanels() {
        super();
        setLayout(new BorderLayout());
        surnameArea.setLineWrap(true);
        welcomeArea.setLineWrap(true);
        topsurnames.add(surnameArea);
        welcome.add(welcomeArea);
        JScrollPane surnameScrollPane = new JScrollPane(topsurnames);
        surnameScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        surnameScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane welcomeScrollPane = new JScrollPane(welcome);
        welcomeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        welcomeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, surnameScrollPane, welcomeScrollPane);
        splitPane.setSize(splitPane.getPreferredSize());
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        add(splitPane);
        splitPane.setMinimumSize(splitPane.getLeftComponent().getPreferredSize());
        splitPane.setMinimumSize(splitPane.getRightComponent().getPreferredSize());

        surnameArea.setMinimumSize(new Dimension(surnameArea.getParent().getWidth(), surnameArea.getParent().getHeight()));

        surnameArea.append("Surname Area");

        welcomeArea.append("Welcome Area");

    }
}
