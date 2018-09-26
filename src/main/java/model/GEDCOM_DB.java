package model;

import controller.ComponentController;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;

public class GEDCOM_DB {
    static private ComponentController componentController = ComponentController.getInstance();

    static public Gedcom readFile(String filename) {
        Gedcom gedcom = null;
        GedcomParser parser = new GedcomParser();
        parser.setStrictCustomTags(false);
        parser.setStrictLineBreaks(false);
        try {
            parser.load(filename);
            gedcom = parser.getGedcom();
            componentController.setGedcom(gedcom);
        } catch (IOException e) {
            e.printStackTrace();
            // Stop if there were parser errors
            if (!parser.getErrors().isEmpty()) {
                for (String s : parser.getErrors()) {
                    System.err.println(s);
                }
                return null;
            }
        } catch (GedcomParserException e) {
            e.printStackTrace();
        }
        return gedcom;
    }
}
