package hashing2;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import jsoncreator.getHash;
import jsoncreator.getfilename1;
import jsoncreator.getfolderfilenames;

import java.io.*;
import java.util.HashMap;

public class patcher1 {

    static String path = "D:\\\\ZOHO\\\\zippingmul\\\\hashing\\\\hashing1.1";
    static String patchName = "";

    public static String patcher2(File f1, String serverPath) throws IOException {
        System.out.println("ADDING FILES NEEDED TO ZIP IN HASHMAP");
        HashMap<String, String> fileToZip = new HashMap<String, String>();
        HashMap<String, String> newFileName = new HashMap<String, String>();
        HashMap<String, String> newfoldername = new HashMap<String, String>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        File file = new File(serverPath + "newfile.json");
        int k = 0;
        System.out.println("patcher1.java");
        try {
            File newxml = new File(path + "\\\\NEW\\\\file.xml");

            HashMap<String, String> newfilehash = new HashMap<String, String>();
            newFileName = getfilename.getfilename1(newxml);

            newfoldername = getfilename1.getfoldername2(newxml);

            for(String key : newfoldername.keySet()){
                System.out.println(key + " " + newfoldername.get(key));
                map1 = getfolderfilenames.getfilenames(path + "\\\\NEW\\\\" + newfoldername.get(key), path + "\\\\NEW\\\\");
//                for(String key1 : map1.keySet()){
//                    System.out.println(key1 + " " + map1.get(key1));
//                }
                newFileName.putAll(map1);
                map1.clear();
            }


            System.out.println("NEWFOLDERNAME");
            for (String key: newfoldername.keySet())
            {
                System.out.println(key + " " + newfoldername.get(key));
            }

            for (String key: newFileName.keySet())
            {
                System.out.println(key + " " + newFileName.get(key));
            }

            for(String key : newFileName.keySet()) {
                String hash = getHash.getHash3(new File(path + "\\\\NEW\\\\" + newFileName.get(key).trim()));
                System.out.println(key + ": " + hash);
                newfilehash.put(key, hash);

            }

            try (FileReader fr = new FileReader(f1)) {
                HashMap<String, String> map = new Gson().fromJson(fr, HashMap.class);
                //System.out.println(map);
                k = 0;
                for (String key : map.keySet()) {
                    System.out.println(key + ":" +map.get(key));
                    if(!(key.equals("patchloc")))
                    {
                        if (newfilehash.containsKey(key)) {
                            if (!newfilehash.get(key).equals(map.get(key))) {
                                System.out.println(key + ": " + newfilehash.get(key) + " " + map.get(key));
                                fileToZip.put(key, newFileName.get(key));
                                k++;
                            }
                        } else {
                            System.out.println(key + " " + "DELETED");
                        }
                    }

                }
                for (String key : newfilehash.keySet()) {
                    if (!map.containsKey(key)) {
                        System.out.println(key + " " + newFileName.get(key) + "ADDED");
                        fileToZip.put(key, newFileName.get(key));
                        k++;
                    }
                }
           }


            try (Writer write = new FileWriter(file)) {
                Gson gson = new Gson();
                gson.toJson(newfilehash, write);
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
        }

        System.out.println("ADDED FILES NEEDED TO ZIP IN HASHMAP");
        for(String key: fileToZip.keySet()){
            System.out.println(key + " " + fileToZip.get(key));
        }

        if(!fileToZip.isEmpty()){
            patchName = zip.zip(fileToZip, serverPath);//passing file needed to hashing2.zip in hashmap
            Unzip.Unzip1(patchName, path);//Unziping patched hashing2.zip in server path
            changexml.changexml1(newFileName, fileToZip , newfoldername ,patchName);//Changing XML in OLD Folder
            //System.out.println(patchName);
        }
        else{
            System.out.println("No changes");
        }
        return patchName;

    }
}
