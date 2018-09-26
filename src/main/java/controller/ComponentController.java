package controller;

import org.gedcom4j.model.Gedcom;
import view.DashBoardPanels;
import view.MainPanel;

import javax.swing.*;

public class ComponentController {

    private DashBoardPanels dashBoardPanels;
    private JPanel mainpanel;
    static private ComponentController componentController = null;

    private Gedcom gedcom;

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

    public void setMainpanel(JPanel mainpanel) {
        this.mainpanel = mainpanel;
    }

    public Gedcom getGedcom() {
        return gedcom;
    }

    public void setGedcom(Gedcom gedcom) {
        this.gedcom = gedcom;
    }
}
