package controller;

import org.gedcom4j.exception.GedcomWriterException;
import org.gedcom4j.exception.WriterCancelledException;
import org.gedcom4j.model.*;
import org.gedcom4j.model.enumerations.FamilyEventType;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.validate.Validator;
import org.gedcom4j.writer.GedcomWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Map;

public class GenDB2GEDCOM {

    // TODO Child needs to refer back to family (or something like that).

    static public void dothis(String gfdbFile, String gedcomFile) {
        File gdfile = new File(gfdbFile);
        //File gedfile = new File( gedcomFile);
        System.out.println("Reading gfdb file ...");
        Gedcom gedcom = new Gedcom();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(gdfile);
            doc.getDocumentElement().normalize();

            // INDIVIDUAL
            NodeList personNodes = doc.getElementsByTagName("p");
            for (int i = 0; i < personNodes.getLength(); i++) {

                Node node = personNodes.item(i);

                Individual individual = new Individual();
                individual.setXref("@I" + node.getAttributes().getNamedItem("id").getNodeValue() + "@");
                String gender;
                if (node.getAttributes().getNamedItem("g").getNodeValue() != null)
                    gender = node.getAttributes().getNamedItem("g").getNodeValue();
                else
                    gender = "";
                individual.setSex(gender);
                PersonalName personalName = new PersonalName();
                String fn;
                if (node.getAttributes().getNamedItem("fn").getNodeValue().contains("Onbekend"))
                    fn = "";
                else
                    fn = node.getAttributes().getNamedItem("fn").getNodeValue();
                String sn;
                if (node.getAttributes().getNamedItem("sn").getNodeValue().contains("Onbekend"))
                    sn = "";
                else
                    sn = node.getAttributes().getNamedItem("sn").getNodeValue();
                personalName.setBasic(fn + " /"
                        + node.getAttributes().getNamedItem("sn").getNodeValue() + "/");
                personalName.setGivenName(new StringWithCustomFacts(node.getAttributes().getNamedItem("fn").getNodeValue()));
                personalName.setSurname(new StringWithCustomFacts(node.getAttributes().getNamedItem("sn").getNodeValue()));

                individual.getNames(true).add(personalName);

                // DE VILLIERS/PAMA NUMBER
                if (node.getAttributes().getNamedItem("gen") != null) {
                    CustomFact customFact = new CustomFact("_DVP");
                    customFact.setDescription(node.getAttributes().getNamedItem("gen").getNodeValue());
                    individual.getCustomFacts(true).add(customFact);
                }

                // BIRTH
                if (node.getAttributes().getNamedItem("dob") != null) {
                    IndividualEvent birthEvent = new IndividualEvent();
                    String date = parseDate(node.getAttributes().getNamedItem("dob").getNodeValue());
                    birthEvent.setDate(date);
                    if (node.getAttributes().getNamedItem("pob") != null) {
                        birthEvent.setPlace(new Place());
                        birthEvent.getPlace().setPlaceName(node.getAttributes().getNamedItem("pob").getNodeValue());
                    }
                    birthEvent.setType(IndividualEventType.BIRTH);
                    individual.getEvents(true).add(birthEvent);
                }

                // CHRISTENING
                if (node.getAttributes().getNamedItem("doc") != null) {
                    IndividualEvent christeningEvent = new IndividualEvent();
                    String date = parseDate(node.getAttributes().getNamedItem("doc").getNodeValue());
                    christeningEvent.setDate(date);
                    if (node.getAttributes().getNamedItem("poc") != null) {
                        christeningEvent.setPlace(new Place());
                        christeningEvent.getPlace().setPlaceName(node.getAttributes().getNamedItem("poc").getNodeValue());
                    }
                    christeningEvent.setType(IndividualEventType.CHRISTENING);
                    individual.getEvents(true).add(christeningEvent);
                }


                // DEATH
                if (node.getAttributes().getNamedItem("dod") != null) {
                    IndividualEvent deathevent = new IndividualEvent();
                    String date = parseDate(node.getAttributes().getNamedItem("dod").getNodeValue());
                    deathevent.setDate(date);
                    if (node.getAttributes().getNamedItem("pod") != null) {
                        deathevent.setPlace(new Place());
                        deathevent.getPlace().setPlaceName(node.getAttributes().getNamedItem("pod").getNodeValue());
                    }
                    deathevent.setType(IndividualEventType.DEATH);
                    individual.getEvents(true).add(deathevent);
                }

                NodeList childNodes = node.getChildNodes();
                for (int index = 0; index < childNodes.getLength(); index++) {
                    Node childNode = childNodes.item(index);
                    // MULTIMEDIA
                    if (childNode.getNodeName().equals("img")) {
                        Multimedia multimedia = new Multimedia();
                        FileReference fileReference = new FileReference();
                        String fileName = childNode.getAttributes().getNamedItem("src").getNodeValue();
                        fileReference.setReferenceToFile(fileName);
                        fileReference.setMediaType("photo");
                        fileReference.setFormat(fileName.substring(fileName.indexOf("."), fileName.length()));
                        fileReference.setTitle(childNode.getTextContent());
                        multimedia.getFileReferences(true).add(fileReference);
                        MultimediaReference multimediaReference = new MultimediaReference(multimedia);
                        individual.getMultimedia(true).add(multimediaReference);
                    }
                    if (childNode.getNodeName().equals("his")) {
                        String text = childNode.getTextContent();
                        NoteStructure noteStructure = new NoteStructure();
                        noteStructure.getLines(true).add(text);


                        individual.getNoteStructures(true).add(noteStructure);
                    }
                }

                gedcom.getIndividuals().put(individual.getXref(), individual);
            }

            // MARRIAGE
            NodeList marriageNodes = doc.getElementsByTagName("m");
            for (int i = 0; i < marriageNodes.getLength(); i++) {
                Node node = marriageNodes.item(i);

                Family family = new Family();
                family.setXref("@F" + node.getAttributes().getNamedItem("id").getNodeValue() + "@");
                String hid = "@I" + node.getAttributes().getNamedItem("hid").getNodeValue() + "@";
                Individual husband = gedcom.getIndividuals().get(hid);
                family.setHusband(new IndividualReference(husband));
                FamilySpouse familySpouseHusband = new FamilySpouse();
                familySpouseHusband.setFamily(family);
                husband.getFamiliesWhereSpouse(true).add(familySpouseHusband);
                String wid = "@I" + node.getAttributes().getNamedItem("wid").getNodeValue() + "@";
                Individual wife = gedcom.getIndividuals().get(wid);
                family.setWife(new IndividualReference(wife));
                FamilySpouse familySpouseWife = new FamilySpouse();
                familySpouseWife.setFamily(family);
                wife.getFamiliesWhereSpouse(true).add(familySpouseWife);

                // MARRIAGE
                if (node.getAttributes().getNamedItem("dom") != null || node.getAttributes().getNamedItem("pom") != null) {
                    FamilyEvent marriageEvent = new FamilyEvent();
                    if (node.getAttributes().getNamedItem("dom") != null) {
                        String date = parseDate(node.getAttributes().getNamedItem("dom").getNodeValue());
                        marriageEvent.setDate(date);
                    }
                    if (node.getAttributes().getNamedItem("pom") != null) {
                        marriageEvent.setPlace(new Place());
                        marriageEvent.getPlace().setPlaceName(node.getAttributes().getNamedItem("pom").getNodeValue());
                    }
                    marriageEvent.setType(FamilyEventType.MARRIAGE);
                    family.getEvents(true).add(marriageEvent);
                }


                // DIVORCE
                if (node.getAttributes().getNamedItem("dodi") != null || node.getAttributes().getNamedItem("podi") != null) {
                    FamilyEvent divorceEvent = new FamilyEvent();
                    if (node.getAttributes().getNamedItem("dodi") != null) {
                        String date = parseDate(node.getAttributes().getNamedItem("dodi").getNodeValue());
                        divorceEvent.setDate(date);
                    }
                    if (node.getAttributes().getNamedItem("podi") != null) {
                        divorceEvent.setPlace(new Place());
                        divorceEvent.getPlace().setPlaceName(node.getAttributes().getNamedItem("podi").getNodeValue());
                    }
                    divorceEvent.setType(FamilyEventType.DIVORCE);
                    family.getEvents(true).add(divorceEvent);
                }

                gedcom.getFamilies().put(family.getXref(), family);

            }
            Map families = gedcom.getFamilies();
            // Go through list again to get children of families
            personNodes = doc.getElementsByTagName("p");
            for (int i = 0; i < personNodes.getLength(); i++) {
                Node node = personNodes.item(i);
                String personXRef = "@I" + node.getAttributes().getNamedItem("id").getNodeValue() + "@";
                if (node.getAttributes().getNamedItem("mid") != null) {
                    String motherXRef = "@I" + node.getAttributes().getNamedItem("mid").getNodeValue() + "@";
                    if (node.getAttributes().getNamedItem("fid") != null) {
                        String fatherXRef = "@I" + node.getAttributes().getNamedItem("fid").getNodeValue() + "@";
                        Individual individual = gedcom.getIndividuals().get(personXRef);
                        families.forEach((key, value) -> {
                            Family family = (Family) value;
                            if (family.getHusband().getIndividual().getXref().equals(fatherXRef) && family.getWife().getIndividual().getXref().equals(motherXRef)) {
                                family.getChildren(true).add(new IndividualReference(individual));
                                FamilyChild familyChild = new FamilyChild();
                                familyChild.setFamily(family);
                                individual.getFamiliesWhereChild(true).add(familyChild);
                            }
                        });
                    }
                }
            }

            Validator validator = new Validator(gedcom);
            validator.validate();
            if (!validator.getResults().getAllFindings().isEmpty()) {
                // There were some problems, so display them on stderr
                for (Validator.Finding finding : validator.getResults().getAllFindings()) {
                    System.err.println(finding);
                }
            }

            GedcomWriter gedcomWriter = new GedcomWriter(gedcom);
            gedcomWriter.write(gedcomFile);
        } catch (WriterCancelledException e) {
            e.printStackTrace();
        } catch (GedcomWriterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println("Done ...");

    }

    public static String parseDate(String date) {
        String ret;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        String[] tokens = date.split("-");
        if (tokens.length == 3) {
            ret = tokens[2] + " " + months[Integer.valueOf(tokens[1]) - 1].toUpperCase() + " " + tokens[0];
        } else if (tokens.length == 2) {
            ret = months[Integer.valueOf(tokens[1])].toUpperCase() + " " + tokens[0];
        } else if (tokens.length == 1) {
            ret = tokens[0];
        } else ret = "";
        return ret.trim();
    }
}
