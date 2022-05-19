package jsoncreator;

import java.io.File;
import java.util.HashMap;

public class getfolderfilenames {
    static HashMap<String, String> filenames = new HashMap<String, String>();

    public static HashMap<String, String> getfilenames(String filename, String path)
    {
        File abc = new File(path);
        File file = new File(filename);
        System.out.println(file.getAbsolutePath());
        File[] files = file.listFiles();
        if (files != null) {
            for(File f: files)
            {
                if(f.isDirectory())
                {
                    getfilenames(f.getAbsolutePath(), path);
                }
                else
                {
                    String a = abc.toURI().relativize(f.toURI()).getPath();
                    filenames.put(a, a);
                }
            }
        }

//        for (String key : filenames.keySet()) {
//            System.out.println(key + " " + filenames.get(key));
//        }
        return filenames;
    }

}
