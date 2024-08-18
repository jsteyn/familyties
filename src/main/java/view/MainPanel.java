package view;

import controller.ComponentController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    DashBoardPanels pnl_dashboard = new DashBoardPanels();
    PersonPanel pnl_people = new PersonPanel();
    CardLayout cardLayout = new CardLayout();
    ComponentController controller = ComponentController.getInstance();

    public MainPanel() {
        super();
        controller.setDashBoardPanels(pnl_dashboard);
        setLayout(cardLayout);
        setBorder(BorderFactory.createLineBorder(Color.red));
        add(pnl_dashboard, "Dashboard");
        add(pnl_people, "People");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "People":
                cardLayout.show(this, "People");
                break;
            case "Dashboard":
                cardLayout.show(this, "Dashboard");
                break;
        }

    }
}
