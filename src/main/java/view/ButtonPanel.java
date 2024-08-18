package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//http://www.miglayout.com/mavensite/

public class ButtonPanel extends JPanel {
    JButton dashboard;
    JButton people;
    JButton relationships;
    JButton families;
    JButton charts;
    JButton events;
    JButton places;
    JButton geography;
    JButton sources;
    JButton citations;
    JButton repositories;
    JButton media;
    JButton notes;
    MainPanel mainPanel;

    public ButtonPanel(MainPanel mainPanel) {
        super();
        this.mainPanel = mainPanel;
        MigLayout migLayout = new MigLayout("", "[][]", "[grow]");
        setLayout(migLayout);
        JPanel buttons = new JPanel();
        buttons.setLayout(new MigLayout("", "[]", ""));
        dashboard = new JButton("Dashboard", createImageIcon("/gramps-gramplet.png"));
        people = new JButton("People", createImageIcon("/gramps-person.png"));
        relationships = new JButton("Relationships", createImageIcon("/gramps-relation.png"));
        families = new JButton("Families", createImageIcon("/gramps-family.png"));
        charts = new JButton("Charts", createImageIcon("/gramps.png"));
        events = new JButton("Events", createImageIcon("/gramps-event.png"));
        places = new JButton("Places", createImageIcon("/gramps-place.png"));
        geography = new JButton("Geography", createImageIcon("/gramps-geo.png"));
        sources = new JButton("Sources", createImageIcon("/gramps-source.png"));
        citations = new JButton("Citations", createImageIcon("/gramps-citation.png"));
        repositories = new JButton("Repositories", createImageIcon("/gramps-repository.png"));
        media = new JButton("Media", createImageIcon("/gramps-media.png"));
        notes = new JButton("Notes", createImageIcon("/gramps-notes.png"));

        dashboard.addActionListener(mainPanel);
        people.addActionListener(mainPanel);

        buttons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        buttons.add(dashboard, "split 11, flowy, top, sgx");
        buttons.add(people, "sgx");
        buttons.add(relationships, "wrap");
        buttons.add(families, "wrap");
        buttons.add(charts, "wrap");
        buttons.add(events, "wrap");
        buttons.add(places, "wrap");
        buttons.add(geography, "wrap");
        buttons.add(sources, "wrap");
        buttons.add(citations, "wrap");
        buttons.add(repositories, "wrap");
        buttons.add(media, "wrap");
        buttons.add(notes, "wrap");
        add(buttons);
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

}
