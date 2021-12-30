package com.americanidol.dependencymanager.service;

import com.americanidol.dependencymanager.model.Dependency;
import com.americanidol.dependencymanager.model.Project;
import org.jdom2.Document;
import org.jdom2.Element;
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
public class DependencyService {

    String pName=null;

    public static String gID;
    public static String aID;

    private List<Dependency> dependencies=new ArrayList<>(Arrays.asList(
            new Dependency("com.mysql", "jdbc-driver", "45.4.1", "DEPLOY","UUID","first-project")
            ));

    public void addDependency(String projectName) {
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

            List<Element> el = null;

            for(int i = 0;i < projectElements.size();i++) {
                Element projectElement = projectElements.get(i);

                if(projectElement.getName().equalsIgnoreCase("dependencies")) {
                    List<Element> dependencies = projectElement.getChildren();
                    el=dependencies;
                }
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
                Element groupId=new Element("groupId");
                Element artifactId=new Element("artifactId");
                Element version=new Element("version");

                groupId.addContent("groupID");
                artifactId.addContent("artifactID");
                version.addContent("Version");

                Element dependencyEle=new Element("dependency");
                dependencyEle.addContent(groupId);
                dependencyEle.addContent(artifactId);
                dependencyEle.addContent(version);

                el.add(dependencyEle);
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

    public void updateDependency(String projectName) {
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

                //Dependencies

                if(pName.equals(projectName)){
                    if(projectElement.getName().equalsIgnoreCase("dependencies")) {
                        List<Element> dependencies=projectElement.getChildren(); //Dependencies list

                        for(int j = 0;j <dependencies.size();j++) {
                            Element dependency = dependencies.get(j); //Specific dependency


                            List<Element> dependencyChildren=dependency.getChildren();

                            for(int k = 0;k <dependencyChildren.size();k++) {
                                Element dependencyelement = dependencyChildren.get(k);//groupid,arId,Vrtion

                                if(dependencyelement.getName().equalsIgnoreCase("groupId")) {
                                    gID=dependencyelement.getValue();

                                }
                                if(dependencyelement.getName().equalsIgnoreCase("artifactId")) {
                                    aID=dependencyelement.getValue();

                                }
                                if (dependencyelement.getName().equalsIgnoreCase("version")){
                                    if (gID.equalsIgnoreCase("org.apache.activemq") && aID.equalsIgnoreCase("activemq-core")){
                                        dependencyChildren.remove(k);
                                        dependencyChildren.add(new Element("version").setText("3.5.1.0.0.0.0.0.0"));
                                        gID=null;
                                    }
                                }
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

    public List<Dependency> getAllDependency() {
        return  dependencies;
    }
}
