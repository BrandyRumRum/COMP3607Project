package project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class FeedbackPDFGenerator {

    public static void generateMergedFeedbackPDF(String studentFileName, List<String> feedbackList, String outputDir) throws IOException {
        // Parse student details from the file name
        String[] fileParts = studentFileName.split("_");
        if (fileParts.length < 4) {
            throw new IllegalArgumentException("Invalid file name format. Expected format: Fname_Lname_id_A1.zip");
        }
        String firstName = fileParts[0];
        String lastName = fileParts[1];
        String studentId = fileParts[2];
        String assignmentNumber = fileParts[3].replace(".zip", "");

        // Create the PDF output path
        String pdfFileName = String.format("%s/%s_%s_%s_A1_Feedback.pdf", outputDir, firstName, lastName, studentId);
        File outputFile = new File(pdfFileName);
        outputFile.getParentFile().mkdirs(); 

        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Add all feedback to the pdf
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.setLeading(15f);
            contentStream.newLineAtOffset(50, 750);

            // Header
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.showText("Assignment Feedback");
            contentStream.newLine();

            // Student details
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("Student Name: " + firstName + " " + lastName);
            contentStream.newLine();
            contentStream.showText("Student ID: " + studentId);
            contentStream.newLine();
            contentStream.showText("Assignment: " + assignmentNumber);
            contentStream.newLine();
            contentStream.newLine();

            for (String feedback : feedbackList) {
                String[] lines = feedback.split("\n");
                for (String line : lines) {
                    // Wrap text if it exceeds the page width
                    List<String> wrappedLines = wrapText(line, 70); 
                    for (String wrappedLine : wrappedLines) {
                        contentStream.showText(wrappedLine);
                        contentStream.newLine();
                    }
                }
                contentStream.newLine();
            }

            contentStream.endText();
        }

        document.save(outputFile);
        document.close();

        System.out.println("Merged Feedback PDF generated: " + pdfFileName);
    }


    private static List<String> wrapText(String text, int maxLineLen) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            if (currentLine.length() + word.length() + 1 > maxLineLen) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (currentLine.length() > 0) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        lines.add(currentLine.toString());

        return lines;
    }
}
