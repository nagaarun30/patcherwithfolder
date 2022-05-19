package jsoncreator;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.*;
import java.util.HashMap;

public class patcher {

    static String path = "D:\\\\ZOHO\\\\zippingmul\\\\hashing\\\\hashing1.1\\\\OLD";
    static String patchname = "";
    public static String patcher1(String path1) throws IOException{

        HashMap<String, String> map1 = new HashMap<String, String>();

        try{
            System.out.println("CREATING CURRENT OLD FILES HASH JSON");

            File file = new File(path1 + "\\oldfile2.json");
            file.createNewFile();
            File oldxml = new File(path + "\\file.xml");
            File dir = new File(path);
            HashMap<String, String> oldfilename = getfilename1.getfilename2(oldxml);
            HashMap<String, String> oldfoldername = getfilename1.getfoldername2(oldxml);
//            for (String key : oldfoldername.keySet()){
//                System.out.println(key + " " + oldfoldername.get(key));
//            }
            for(String key : oldfoldername.keySet()){
                //System.out.println(key + " " + oldfoldername.get(key));
                map1 = getfolderfilenames.getfilenames(path+ "\\\\"+ oldfoldername.get(key), path + "\\\\");
                oldfilename.putAll(map1);
            }



            for (String key : oldfilename.keySet()){
                System.out.println(key + " " + oldfilename.get(key));
            }

            HashMap<String, String> data = new HashMap<String, String>();
            for(String key : oldfilename.keySet()) {
                String hash = getHash.getHash3(new File(dir + "\\" + oldfilename.get(key).trim()));
//                System.out.println(key + ": " + hash);
                data.put(key, hash);

            }
            try ( //System.out.println(data);
                  FileWriter write = new FileWriter(file)) {
                Gson gson = new Gson();
                gson.toJson(data, write);
            }


            patchname = patchcreate.patchcreate1(file, path1);
        }
        catch(JsonIOException | IOException e)
        {
            System.out.println(e);
        }
        return patchname;
    }
}

