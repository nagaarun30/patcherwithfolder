package hashing2;

import jsoncreator.getfilename1;
import jsoncreator.getfolderfilenames;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class update {
    static String NEW = "D:\\ZOHO\\zippingmul\\hashing\\hashing1.1\\NEW\\";
    static String OLD = "D:\\ZOHO\\zippingmul\\hashing\\hashing1.1\\OLD\\";
    static String Unzip = "";
    static String serverpath = "D:\\\\ZOHO\\\\zippingmul\\\\hashing\\\\hashing1.1\\\\";
    public static void update1(File xml, HashMap<String, String> newfilename, HashMap<String, String> oldfilename, HashMap<String, String> filetozipname, String patchName) throws IOException {
        try {
            System.out.println("UPDATING FILES IN OLD FOLDER");
            HashMap<String, String> map1 = new HashMap<String, String>();
            Unzip = serverpath + patchName + "\\";
            HashMap<String,String> xmlfilename = getfilename.getfilename1(xml);
            HashMap<String,String> xmlfoldername = getfilename1.getfoldername2(xml);

            for(String key : xmlfoldername.keySet()){
                System.out.println(key + " " + xmlfoldername.get(key));
                map1 = getfolderfilenames.getfilenames(NEW+"\\" + xmlfoldername.get(key), serverpath + "\\\\NEW\\\\");
//                for(String key1 : map1.keySet()){
//                    System.out.println(key1 + " " + map1.get(key1));
//                }
                xmlfilename.putAll(map1);
                map1.clear();
            }

            System.out.println("XMLFILENAME");
            for (String key: xmlfilename.keySet())
            {
                System.out.println(key + " " + xmlfilename.get(key));
            }

            for(String key : xmlfilename.keySet()) {
                if(filetozipname.get(key)!=null) {
                    if (filetozipname.get(key).equals(xmlfilename.get(key))) {
                        //System.out.println("UPDATING " + filetozipname.get(key));
                        replace(filetozipname.get(key), xmlfilename.get(key), oldfilename.get(key));
                    }
                }
            }
            for (String key : oldfilename.keySet()) {
                if(!newfilename.containsKey(key)) {
                    File newfile = new File(OLD + oldfilename.get(key));
                    newfile.delete();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void replace(String s, String s1, String s2) throws IOException {
        File abc = new File(Unzip);
        File file = new File(Unzip + s);
        File parent = new File(file.getParent());
        File file1 = new File(OLD + s1);


        String a = abc.toURI().relativize(parent.toURI()).getPath();
        System.out.println(a);
        if(a!= null)
        {
            a = OLD + a;
            File dir = new File(a);
            if(!dir.exists())
            {
                dir.mkdirs();
            }
        }


//        if(!file1.exists())
//        {
//            file1.createNewFile();
//        }
        File file2 = new File(OLD + s2);
        System.out.println("REPLACING " + file1.getName() + " WITH " + file.getName());
        if(file2.exists()) {
            file2.delete();
        }

        Files.copy(file.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}

