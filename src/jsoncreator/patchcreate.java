package jsoncreator;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import hashing2.patcher1;
import jsoncreator.getHash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class patchcreate {
    static String patchname = "";
    public static String patchcreate1(File jsonfile1, String serverpath) throws IOException
    {File filetosave = null;
        try{
            String filename = "";
            String filepath = serverpath;
            File jsonfile = new File(serverpath + "oldfile.json");
            Files.copy(jsonfile1.toPath(), jsonfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            jsonfile1.delete();
            //Files.copy(, filetosave.toPath(), StandardCopyOption.REPLACE_EXISTING);




            File newfile = new File(filepath.concat("newfile.json"));
            File oldoldfile = new File(filepath.concat("oldoldfile.json"));
            if(getHash.getHash3(jsonfile).equals(getHash.getHash3(oldoldfile)))
            {

                if(newfile.exists())
                {
                    System.out.println("WORKING WITH NEWFILE.JSON");
                    patchname = patcher1.patcher2(newfile, serverpath);

                }
                else
                {
                    jsonfile.delete();
                    //filetosave1.renameTo(filetosavetemp);
                    System.out.println("WORKING WITH OLDOLDFILE.JSON");
                    patchname = patcher1.patcher2(jsonfile,serverpath);

                }
            }
            else{
                System.out.println("WORKING WITH NEW OLDFILE.JSON");
                patchname = patcher1.patcher2(jsonfile,serverpath);

            }


            //System.out.println(hashing2.getHash1.getHash(filetosave.getAbsolutePath()));

            if(!jsonfile.exists())
            {
                String filetoprocess = jsonfile.toString();
                Files.copy(jsonfile.toPath(), jsonfile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                //Files.copy(fileInputStream, filetosave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String[] arr = filename.split("\\.");
                if(arr[1].equals("json"))
                {
                    patchname = patcher1.patcher2(jsonfile,serverpath);
                }
                else
                {
                }
            }

            //Files.copy(jsonfile.toPath(), oldoldfile.toPath(), StandardCopyOption.REPLACE_EXISTING);


        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return patchname;
    }
}
