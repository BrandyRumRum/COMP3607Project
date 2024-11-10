package project;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipHandler {

    // Extracts a ZIP file to a destination directory and returns a list of Java files
    public static List<File> extractJavaFiles(File zipFile, String destDirPath) throws IOException {
        List<File> javaFiles = new ArrayList<>();
        Path destDir = Paths.get(destDirPath);
        Files.createDirectories(destDir); // Ensure destination directory exists

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path filePath = destDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    try (OutputStream os = Files.newOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zis.read(buffer)) > 0) {
                            os.write(buffer, 0, bytesRead);
                        }
                    }

                    // Add to list if the extracted file is a Java file
                    if (filePath.toString().endsWith(".java")) {
                        javaFiles.add(filePath.toFile());
                    }
                }
                zis.closeEntry();
            }
        }
        return javaFiles;
    }
}