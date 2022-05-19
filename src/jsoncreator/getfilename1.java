package jsoncreator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class getfilename1 {

    public static HashMap getfilename2(File fl)
    {

        HashMap<String, String> filename = new HashMap<String, String>();
        int i;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fl);
            NodeList list = doc.getElementsByTagName("Files");
            for(i = 0; i < list.getLength(); i++)
            {
                Node f = list.item(i);
                if(f.getNodeType() == Node.ELEMENT_NODE)
                {
                    int k = 0;
                    Element file = (Element) f;
                    NodeList filelist = file.getChildNodes();
                    for(int j = 0; j < filelist.getLength(); j++)
                    {
                        Node n = filelist.item(j);
                        if(n.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element name = (Element) n;
                            //System.out.println(name.getAttribute("id") + ": " + name.getTextContent());
                            if(name.getAttribute("type").equalsIgnoreCase("file")) {
                                if (name.getTextContent() != null)
                                    filename.put(name.getAttribute("id"), name.getTextContent().trim());
                            }
                        }
                    }
                }
            }

        }
        catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        catch(SAXException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return filename;
    }

    public static HashMap getfoldername2(File fl)
    {

        HashMap<String, String> foldername = new HashMap<String, String>();
        int i;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fl);
            NodeList list = doc.getElementsByTagName("Files");
            for(i = 0; i < list.getLength(); i++)
            {
                Node f = list.item(i);
                if(f.getNodeType() == Node.ELEMENT_NODE)
                {
                    int k = 0;
                    Element file = (Element) f;
                    NodeList filelist = file.getChildNodes();
                    for(int j = 0; j < filelist.getLength(); j++)
                    {
                        Node n = filelist.item(j);
                        if(n.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element name = (Element) n;
                            //System.out.println(name.getAttribute("id") + ": " + name.getTextContent());
                            if(name.getAttribute("type").equalsIgnoreCase("folder")) {
                                if (name.getTextContent() != null)
                                    foldername.put(name.getAttribute("id"), name.getAttribute("name"));
                            }
                        }
                    }
                }
            }

        }
        catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        catch(SAXException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return foldername;
    }
}

