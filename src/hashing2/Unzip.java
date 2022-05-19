package hashing2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.zip.*;

public class Unzip {
    public static void Unzip1(String patchname, String pathtounzip) throws IOException {
        //File zipfile = new File(pathtounzip + "\\" + patchname);
        //File unzipdir = new File(pathtounzip);
        System.out.println("UNZIPPING PATCH.ZIP TO SERVER PATH");

        unzipFolder(Paths.get(pathtounzip + "\\" + patchname.concat(".zip")), Paths.get(pathtounzip.concat("\\").concat(patchname)));
    }

    public static void unzipFolder(Path source, Path target) throws IOException {

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {


            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {

                boolean isDirectory = false;

                if (zipEntry.getName().endsWith(File.separator)) {
                    isDirectory = true;
                }

                Path newPath = zipSlipProtect(zipEntry, target);

                if (isDirectory) {
                    Files.createDirectories(newPath);
                } else {

                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent());
                        }
                    }


                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }

                zipEntry = zis.getNextEntry();

            }
            zis.closeEntry();

        }

    }


    public static Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {


        Path targetDirResolved = targetDir.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }

        return normalizePath;
    }
}

