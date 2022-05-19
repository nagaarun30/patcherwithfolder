package jsoncreator;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class getHash {
    public static String getHash3(File filePath)
    {
        byte[] HashValue1;
        String md5sum = "";
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //System.out.println(filePath.getAbsolutePath());
            try (FileChannel fc = FileChannel.open(Paths.get(filePath.getAbsolutePath()))) {
                long minsize = 1024 * 1024 * 1024;

                long len = fc.size();
                long pos = 0;
                //System.out.println(Math.min(minsize, len - pos));
                while (pos < len) {
                    long size = Math.min(minsize, len - pos);
                    //System.out.println(size);
                    MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, pos, size);
                    md.update(mbb);
                    pos = pos + size;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            HashValue1 = md.digest();
            //StringBuilder sb = new StringBuilder();
            for (int i = 0; i < HashValue1.length; i++) {
                sb.append(Integer.toString((HashValue1[i] & 0xff) + 0x100, 16).substring(1));
            }
            md5sum = new String(HashValue1);



        } catch ( NoSuchAlgorithmException nsae) {

        }
        return sb.toString();
    }
}

