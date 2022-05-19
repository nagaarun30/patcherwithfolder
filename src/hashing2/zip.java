package hashing2;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zip {
    static String path = "D:\\ZOHO\\zippingmul\\hashing\\hashing1.1";
    public static String zip(HashMap<String, String> fileName, String patchloc) throws IOException
    {
        System.out.println("CREATING PATCH.ZIP IN SERVER PATH");

        String patchname = "patch";

        //System.out.println("patch name is "+patchname);
        int i = 0,j=0;
        File f1 = new File(path+"\\"+patchname+".zip");
        System.out.println("file name is "+f1.getName());
        while (i == 0)
        {
            if (f1.exists())
            {
                String patc = "patch";
                patchname = patc.concat("(" + Integer.toString(j) + ")");
                f1 = new File(path+"\\"+patchname+".zip");
                System.out.println("file name is "+f1.getName());
                j++;
            }
            else
            {
                i = 1;
            }
            //System.out.println(patchname);
        }
        System.out.println("patch name is "+patchname);



        try (FileOutputStream fos = new FileOutputStream(path +"\\" + patchname + ".zip");
             ZipOutputStream zos = new ZipOutputStream(fos))
        {

            for(String key: fileName.keySet())
            {
                String a = fileName.get(key);
                if(a != null)
                {
                    a = a.trim();
                    File f = new File(path + "\\NEW", a);
                    System.out.println("Compressing " + a);
                    if(f.exists())
                    {
                        try (FileInputStream in = new FileInputStream(f)) {
                            ZipEntry ze= new ZipEntry(a);
                            zos.putNextEntry(ze);
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = in.read(buffer)) > 0)
                            {
                                zos.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return patchname;
    }

}

