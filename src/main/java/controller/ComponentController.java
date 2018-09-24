package controller;

import view.DashBoardPanels;
import view.MainPanel;

import javax.swing.*;

public class ComponentController {

    private DashBoardPanels dashBoardPanels;
    private MainPanel mainpanel;
    static private ComponentController componentController = null;

    private  ComponentController() {

    }

    static public ComponentController getInstance() {
        if (componentController == null) {
            componentController = new ComponentController();
        }
        return componentController;
    }

    public DashBoardPanels getDashBoardPanels() {
        return dashBoardPanels;
    }

    public void setDashBoardPanels(DashBoardPanels dashBoardPanels) {
        this.dashBoardPanels = dashBoardPanels;
    }

    public JPanel getMainpanel() {
        return mainpanel;
    }

    public void setMainpanel(MainPanel mainpanel) {
        this.mainpanel = mainpanel;
    }
}
