package project;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class AutoMarkerMain {

    public static void main(String[] args) {
        File zipFile = new File("path/to/studentSubmissions.zip");
        String destDirPath = "path/to/extractedSubmissions";

        try {
            // Extract Java files from the zip
            List<File> javaFiles = ZipHandler.extractJavaFiles(zipFile, destDirPath);

            // Evaluating each extracted Java file
            for (File javaFile : javaFiles) {
                String className = javaFile.getName().replace(".java", "");
                System.out.println("Evaluating class: " + className);
                
                try {
                    Class<?> clazz = Class.forName(className);
                    BaseEvaluator evaluator = EvaluatorFactory.getEvaluator(className);
                    evaluator.evaluateClass(clazz);
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found: " + className);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error extracting ZIP file: " + e.getMessage());
        }
    }
}
