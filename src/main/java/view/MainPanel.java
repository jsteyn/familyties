package view;

import controller.ComponentController;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    DashBoardPanels dashBoardPanels = new DashBoardPanels();
    ComponentController componentController = ComponentController.getInstance();

    public MainPanel() {
        super();
        setLayout(new BorderLayout());
        componentController.setDashBoardPanels(dashBoardPanels);
        add(dashBoardPanels);
        dashBoardPanels.setVisible(false);


    }
}
