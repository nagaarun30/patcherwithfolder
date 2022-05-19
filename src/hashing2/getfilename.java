package hashing2;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class getfilename {
    static String path = "D:\\\\ZOHO\\\\zippingmul\\\\hashing\\\\hashing1.1\\\\NEW\\\\";

    public static HashMap getfilehash(File fl)
    {
        System.out.println("STORING HASH-VALUE IN HASH MAP");

        HashMap<String, String> filename = new HashMap<String, String>();
        HashMap<String, String> filehash = new HashMap<String, String>();

        int i;
        //Instant starttime = Instant.now();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            File file1 = new File(path + "\\file.json");
            file1.createNewFile();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fl);
            NodeList list = doc.getElementsByTagName("Files");
            //String[] name = new String[5];
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
                        //System.out.println(n.getNodeName());
                        if(n.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element name = (Element) n;
                            if(name.getAttribute("type").equalsIgnoreCase("file")) {
                                if (name.getTextContent() != null) {
                                    System.out.println(name.getAttribute("id").trim() + ": " + getHash1.getHash(path + name.getTextContent().trim()));
                                    filehash.put(name.getAttribute("id"), getHash1.getHash(path + name.getTextContent().trim()));
                                    //System.out.println(name.getAttribute("id")+ ": " + getHash.getHash(name.getTextContent().trim()));

                                }
                            }
                        }
                    }
                }
            }
            Writer write = new FileWriter(file1);
            Gson gson = new Gson();
            gson.toJson(filehash, write);
            write.close();

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
        //System.out.println(filenametozip[1]);
        return filehash;
    }
    public static HashMap getfilename1(File fl)
    {
        System.out.println("STORING FILENAME IN HASH MAP");

        HashMap<String, String> filename = new HashMap<String, String>();


        int i;
        //Instant starttime = Instant.now();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fl);
            NodeList list = doc.getElementsByTagName("Files");
            //String[] name = new String[5];
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
                        //System.out.println(n.getNodeName());
                        if(n.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element name = (Element) n;
                            if(name.getAttribute("type").equalsIgnoreCase("file")) {
                                if (name.getTextContent() != null) {
                                    System.out.println(name.getAttribute("id").trim() + ": " + name.getTextContent().trim());
                                    filename.put(name.getAttribute("id"), name.getTextContent().trim());
                                    //System.out.println(name.getAttribute("id")+ ": " + name.getTextContent().trim());

                                }
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
        //System.out.println(filenametozip[1]);
        return filename;
    }


}
