package project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AutoMarkerMain {

    public static void main(String[] args) {
        File zipFile = new File("src\\main\\java\\project\\A1_Files\\Fname_Lname_id_A1.zip");        //Insert respective information for zip file
        String destDirPath = new File("src\\main\\java\\project").getAbsolutePath();
        String compiledDirPath = new File("target/classes").getAbsolutePath();
        String outputDir = "src\\main\\java\\project\\A1_Files\\Feedback";

        List<String> allFeedback = new ArrayList<>(); // Collect feedback for all classes

        try {
            List<File> javaFiles = ZipHandler.extractAndPrependPackages(zipFile, destDirPath);

            compileJavaFiles(javaFiles, compiledDirPath);

            // Load compiled classes and evaluate
            URL[] urls = { new File(compiledDirPath).toURI().toURL() };
            try (URLClassLoader classLoader = new URLClassLoader(urls)) {
                for (File javaFile : javaFiles) {
                    String fullyQualifiedClassName = getFullyQualifiedClassName(javaFile, destDirPath);
                    String simpleClassName = javaFile.getName().replace(".java", "");
                    System.out.println("Evaluating class: " + simpleClassName);

                    try {
                        Class<?> clazz = classLoader.loadClass(fullyQualifiedClassName);
                        System.out.println("Class loaded: " + clazz.getName());

                        // Get evaluator and evaluate the class
                        BaseEvaluator evaluator = EvaluatorFactory.getEvaluator(simpleClassName);
                        evaluator.evaluateClass(clazz);

                        // Collect feedback for this class
                        String feedback = "Class: " + simpleClassName + "\n" + String.join("\n", evaluator.feedback) + "\n";
                        allFeedback.add(feedback);

                    } catch (ClassNotFoundException e) {
                        System.err.println("Class not found: " + fullyQualifiedClassName);
                        allFeedback.add("Class: " + simpleClassName + "\nClass not found.\n");
                    }
                }
            }

            // Generate merged feedback PDF
            FeedbackPDFGenerator.generateMergedFeedbackPDF(zipFile.getName(), allFeedback, outputDir);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void compileJavaFiles(List<File> javaFiles, String outputDir) throws IOException {
        List<String> command = new ArrayList<>();
        command.add("javac");
        command.add("-d");
        command.add(outputDir);
        for (File javaFile : javaFiles) {
            command.add(javaFile.getAbsolutePath());
        }

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        try {
            if (process.waitFor() != 0) {
                System.err.println("Compilation failed. Check the errors above.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Compilation interrupted.", e);
        }
    }

    private static String getFullyQualifiedClassName(File javaFile, String baseDir) {
        try {
            Path basePath = new File(baseDir).getCanonicalFile().toPath();
            Path javaFilePath = javaFile.getCanonicalFile().toPath();
            Path relativePath = basePath.relativize(javaFilePath);
            String className = relativePath.toString()
                    .replace(File.separator, ".")
                    .replace(".java", "");
            return "project." + className;
        } catch (IOException e) {
            throw new RuntimeException("Error calculating fully qualified class name for file: " + javaFile, e);
        }
    }
}
