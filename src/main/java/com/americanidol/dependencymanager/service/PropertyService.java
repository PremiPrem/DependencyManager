package com.americanidol.dependencymanager.service;
import org.jdom2.Document;
import org.jdom2.Element;
import com.americanidol.dependencymanager.model.Property;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Service;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Service
public class PropertyService {

    String pName=null;

    private List<Property> properties = new ArrayList<>(Arrays.asList(
            new Property( "app.runtime", "4.3", "DEPLOY", "uuid")
    ));

    public void addProperty(String projectName) {
        try (InputStream in = new FileInputStream("src/main/java/com/americanidol/dependencymanager/pomNew.xml");
             OutputStream out = System.out;) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(
                    new FileReader("src/main/java/com/americanidol/dependencymanager/pomNew.xml"));
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File("src/main/java/com/americanidol/dependencymanager/pomNew.xml"));

            Element rootElement = document.getRootElement();
            List<Element> projectElements = rootElement.getChildren(); //getting all the children of the project



            for(int i = 0;i < projectElements.size();i++) {
                Element projectElement = projectElements.get(i);


                if (projectElement.getName().equalsIgnoreCase("name")) {
                    String projectNameofElement = projectElement.getValue();

                    pName=projectNameofElement;
//
                }
            }

            /*<groupId>org.apache.activemq</groupId>
            <artifactId>activemq-client</artifactId>
            <version>5.16.3</version>*/
            if (pName.equals(projectName)){
                Element maven=new Element("maven.plugin.version");
                Element app_runtime=new Element("app.runtime");

                maven.addContent("groupID");
                app_runtime.addContent("artifactID");

                Element propertiesEle=new Element("properties");
                propertiesEle.addContent(maven);
                propertiesEle.addContent(app_runtime);

                projectElements.add(propertiesEle);
            }


            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
            }

            XMLOutputter xmlOutput = new XMLOutputter();

            // display xml
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, System.out);

            try (FileOutputStream output =
                         new FileOutputStream("src/main/java/com/americanidol/dependencymanager/pomNew2.xml")) {
                xmlOutput.output(document, output);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

//    public void updateProperty(Property property, String projectName) {
//        for (int i = 0; i < properties.size(); i++) {
//            Property p = properties.get(i);
//            if (p.getProjectName().equals(projectName)) {
//                properties.set(i, property);
//                return;
//            }
//        }
//    }
        public void updateProperty(String projectName ){

            try (InputStream in = new FileInputStream("src/main/java/com/americanidol/dependencymanager/pomNew.xml");
                 OutputStream out = System.out;) {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                XMLOutputFactory xof = XMLOutputFactory.newInstance();
                XMLEventReader eventReader = factory.createXMLEventReader(
                        new FileReader("src/main/java/com/americanidol/dependencymanager/pomNew.xml"));
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(new File("src/main/java/com/americanidol/dependencymanager/pomNew.xml"));

                Element rootElement = document.getRootElement();
                List<Element> projectElements = rootElement.getChildren(); //getting all the children of the project


                for (int i = 0; i < projectElements.size(); i++) {
                    Element projectElement = projectElements.get(i);

                    if (projectElement.getName().equalsIgnoreCase("name")) {
                        String projectNameofElement = projectElement.getValue();

                        pName=projectNameofElement;
//
                    }
                    System.out.println(pName);
                    if (projectName.equals(pName)){
                        if (projectElement.getName().equalsIgnoreCase("properties")) {
                            List<Element> Propertiess = projectElement.getChildren();
                            for (int j = 0; j < Propertiess.size(); j++) {
                                Element propertyy = Propertiess.get(j);
                                if (propertyy.getName().equalsIgnoreCase("app.runtime")) {
                                    Propertiess.remove(j);
                                    Propertiess.add(new Element("app.runtime").setText("3.5.5.5.5"));
                                }
                            }
                        }
                    }

                }

                while(eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();
                }

                XMLOutputter xmlOutput = new XMLOutputter();

                // display xml
                xmlOutput.setFormat(Format.getPrettyFormat());
                xmlOutput.output(document, System.out);

                try (FileOutputStream output =
                             new FileOutputStream("src/main/java/com/americanidol/dependencymanager/pomNew2.xml")) {
                    xmlOutput.output(document, output);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
}
