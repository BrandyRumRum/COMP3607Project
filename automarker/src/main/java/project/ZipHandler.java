package project;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class ZipHandler {
    public static List<File> extractAndPrependPackages(File zipFile, String destDirPath) throws IOException {
        List<File> extractedFiles = new ArrayList<>();
        Path destDir = Paths.get(destDirPath);
        Files.createDirectories(destDir);

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String normalizedEntryName = Paths.get(entry.getName()).normalize().toString();

                if (!normalizedEntryName.startsWith(destDir.toString()) && normalizedEntryName.contains("..")) {
                    System.err.println("Skipping invalid entry: " + entry.getName());
                    continue;
                }

                Path filePath = destDir.resolve(normalizedEntryName);
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    // Create parent directories if necessary
                    Files.createDirectories(filePath.getParent());
                    try (OutputStream os = Files.newOutputStream(filePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zis.read(buffer)) > 0) {
                            os.write(buffer, 0, bytesRead);
                        }
                    }

                    // Prepend package declaration if the file is a Java file
                    if (filePath.toString().endsWith(".java")) {
                        prependPackageDeclaration(filePath, destDir, normalizedEntryName);
                        extractedFiles.add(filePath.toFile());
                    }
                }
                zis.closeEntry();
            }
        }

        return extractedFiles;
    }

    private static void prependPackageDeclaration(Path javaFilePath, Path destDir, String entryName)
            throws IOException {
        // Determine the package name based on the file's relative path
        Path relativePath = destDir.relativize(javaFilePath.getParent());
        String packageName = relativePath.toString().replace(File.separatorChar, '.');
        String originalContent = Files.readString(javaFilePath);

        // Create the package declaration and prepend it
        String packageDeclaration = "package project." + packageName + ";\n\n";
        String updatedContent = packageDeclaration + originalContent;
        Files.writeString(javaFilePath, updatedContent);
    }
}
