package hashing2;

import jsoncreator.getfilename1;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class changexml {
    static String fl = "D:\\ZOHO\\zippingmul\\hashing\\hashing1.1\\OLD\\file.xml";
    public static void changexml1 (HashMap<String, String> newfilename, HashMap<String, String> filetozipname, HashMap<String, String> newFolderName ,String patchname) {
        int i;
        HashMap<String, String> oldfilename = (HashMap<String, String>) getfilename.getfilename1(new File(fl));
        HashMap<String, String> oldfoldername = (HashMap<String, String>) getfilename1.getfoldername2(new File(fl));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            System.out.println("CHANGING XML IN OLD FOLDER");
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fl);
            NodeList list = doc.getElementsByTagName("Files");
            //String[] name = new String[5];
            for(i = 0; i < list.getLength(); i++)
            {
                Node f = list.item(i);
                if(f.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element file = (Element) f;
                    NodeList filelist = file.getChildNodes();

                    for(int j = 0; j < filelist.getLength(); j++)
                    {
                        Node n = filelist.item(j);
                        //System.out.println(n.getNodeName());
                        if(n.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element name = (Element) n;
                            if(name.getTextContent() != null ) {

                                if(!name.getAttribute("type").equals("Folder")) {
                                    if (newfilename.containsKey(name.getAttribute("id")) && filetozipname.containsKey(name.getAttribute("id"))) {
                                        System.out.println(name.getAttribute("id") + ": " + name.getChildNodes().item(1).getNodeName() + " Changed To: " + newfilename.get(name.getAttribute("id")));
                                        if ("FileName".equals(name.getChildNodes().item(1).getNodeName()) && newfilename.get(name.getAttribute("id")) != null)
                                            name.getChildNodes().item(1).setTextContent(newfilename.get(name.getAttribute("id")));
                                    } else if (!newfilename.containsKey(name.getAttribute("id"))) {
                                        System.out.println(name.getAttribute("id") + ": " + name.getChildNodes().item(1) + " DELETED To: " + "NULL");
                                        file.removeChild(name);
                                    }
                                }

                                else
                                {
                                    if (newFolderName.containsKey(name.getAttribute("id")) && oldfoldername.containsKey(name.getAttribute("id"))) {
                                        System.out.println(name.getAttribute("id") + " Changed To: " + newFolderName.get(name.getAttribute("id")));
                                        if (newFolderName.get(name.getAttribute("id")) != null)
                                            name.setAttribute("name", newFolderName.get(name.getAttribute("id")));
                                    } else if (!newfilename.containsKey(name.getAttribute("id"))) {
                                        System.out.println(name.getAttribute("id") + " DELETED To: " + "NULL");
                                        file.removeChild(name);
                                    }
                                }

                            }
                        }

                    }

                }
                Node f1 = list.item(i);
                Element file1 = (Element) f1;
                for(String key : newfilename.keySet())
                {
                    if (newfilename.get(key).contains("\\") || newfilename.get(key).contains("/")) {
                    } else {
                        if (!oldfilename.containsKey(key)) {
                            Element name = doc.createElement("File");
                            name.setAttribute("id", key);
                            name.setAttribute("type", "File");
                            name.appendChild(doc.createElement("FileName")).appendChild(doc.createTextNode(newfilename.get(key)));
                            file1.appendChild(name);
                        }
                    }
                }


                for(String key : newFolderName.keySet())
                {
                    if(!oldfoldername.containsKey(key))
                    {
                        Element name = doc.createElement("Folder");
                        name.setAttribute("id", key);
                        name.setAttribute("type", "Folder");
                        name.setAttribute("name", newFolderName.get(key));
                        file1.appendChild(name);
                    }
                }
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);

            StreamResult streamResult = new StreamResult(fl);
            transformer.transform(domSource, streamResult);
            update.update1(new File(fl), newfilename, oldfilename, filetozipname, patchname);

        }
        catch(ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}

