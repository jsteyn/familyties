package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GenDBLAMainPanel extends JPanel implements ActionListener {
    static final private String SEARCH = "Soek";
    static final private String ADD = "Byvoeg";
    static final private String COMPARE = "Vergelyk";

    GenDBLAMainPanel() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setLayout(new FlowLayout());
        JToolBar toolBar = new JToolBar();
        JLabel label = new JLabel("Soek na persone");
        JTextField textField = new JTextField(52);
        addButtons(toolBar);
//        add(label);
//        add(textField);
        add(toolBar);
    }

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        //first button
        button = makeNavigationButton("search", SEARCH,
                "Soek persoon",
                "Soek");
        toolBar.add(button);

        //second button
        button = makeNavigationButton("add", ADD,
                "Voeg nuwe persoon by",
                "Byvoeg");
        toolBar.add(button);

        //third button
        button = makeNavigationButton("left_right", COMPARE,
                "Forward to something-or-other",
                "Next");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "/" + imageName + ".png";
        URL imageURL = GenDBLA.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        ImageIcon imageIcon = createImageIcon(imgLocation);

        if (imageIcon != null) {                      //image found
            button.setIcon(imageIcon);
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }

        return button;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FamilyTies.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
