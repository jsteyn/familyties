package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

//http://www.miglayout.com/mavensite/

public class SidePanel extends JPanel {
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

    public SidePanel() {
        super();
        MigLayout migLayout = new MigLayout("wrap 1");
        setLayout(migLayout);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
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


        add(dashboard);
        add(people);
        add(relationships);
        add(families);
        add(charts);
        add(events);
        add(places);
        add(geography);
        add(sources);
        add(citations);
        add(repositories);
        add(media);
        add(notes);
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
