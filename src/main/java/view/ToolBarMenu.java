package view;

import org.openide.awt.DropDownButtonFactory;

import javax.swing.*;
import java.awt.*;

// https://www.codejava.net/java-se/swing/how-to-create-drop-down-button-in-swing

public class ToolBarMenu  extends JToolBar{

    JButton grampsButton;
    JPopupMenu popupMenu = new JPopupMenu();
    JButton dropDownButton;

    ToolBarMenu() {
        super();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        ImageIcon grampsIcon = createImageIcon("/gramps.png");
        grampsButton = new JButton(grampsIcon);
        dropDownButton = DropDownButtonFactory.createDropDownButton(grampsIcon, popupMenu);


        JMenuItem menuItemCreateSpringProject = new JMenuItem("Spring Project");
        popupMenu.add(menuItemCreateSpringProject);

        JMenuItem menuItemCreateHibernateProject = new JMenuItem("Hibernate Project");
        popupMenu.add(menuItemCreateHibernateProject);

        JMenuItem menuItemCreateStrutsProject = new JMenuItem("Struts Project");
        popupMenu.add(menuItemCreateStrutsProject);

        add(grampsButton);
        add(dropDownButton);

    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = JGramps.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
