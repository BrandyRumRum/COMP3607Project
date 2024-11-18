package project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

// uncomment and fill in respective information after running for the first time for the extraction and evaluation
// import project.FName_LName_ID_A1.ChatBotPlatform;

public class ChatBotSimulationTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    void testHelloWorldPrinted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.out.println("Hello World!");
        String capturedOutput = outputStream.toString().trim();

        assertTrue(capturedOutput.startsWith("Hello"), "Output should start with 'Hello'.");
    }

    @Test
    void testChatBotPlatformInitialization() {
        ChatBotPlatform platform = new ChatBotPlatform();

        assertNotNull(platform, "ChatBotPlatform should be initialized.");
    }

    @Test
    void testInteractWithChatBots() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize ChatBotPlatform and add bots
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0); // Add ChatGPT-3.5
        platform.addChatBot(1); // Add LLaMa

        // Interact with ChatBots 15 times
        for (int i = 0; i < 15; i++) {
            int randomBotIndex = i % 2; // Alternate between the two ChatBots
            String response = platform.interactWithBot(randomBotIndex, "Test message " + i);
            System.out.println(response); // Simulate output
        }

        String output = outputStream.toString();
        System.out.println("Captured Output:\n" + output);
        assertTrue(output.startsWith("(Message"),
                "Output should start with '(Message #1)");
    }

    @Test
    void testPrintChatBotList() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize the ChatBotPlatform and add bots
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0); // ChatGPT-3.5
        platform.addChatBot(1); // LLaMa
        platform.addChatBot(2); // Mistral7B
        platform.addChatBot(3); // Bard
        platform.addChatBot(4); // Claude
        platform.addChatBot(5); // Solar

        String chatBotList = platform.getChatBotList();
        System.out.println(chatBotList);

        String output = outputStream.toString().trim();
        System.out.println("Captured Output:\n" + output);

        assertTrue(output.contains("Total Messages Used: 0"), "Output should include 'Total Messages Used: 0'.");
        assertTrue(output.contains("Total Messages Remaining: 10"),
                "Output should include 'Total Messages Remaining: 10'.");

        assertTrue(output.contains("Your ChatBots"), "Output should contain the header 'Your ChatBots'.");
    }

    @Test
    void testPrintFinalChatBotList() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialize ChatBotPlatform and add ChatBots
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0); // Add ChatGPT-3.5
        platform.addChatBot(1); // Add LLaMa

        platform.interactWithBot(0, "Test message 1"); // CharGPT-3.5
        platform.interactWithBot(1, "Test message 2"); // LLaMa

        System.out.println(platform.getChatBotList());

        String output = outputStream.toString().trim();
        System.out.println("Captured Output:\n" + output);

        assertTrue(output.contains("Total Messages Used: 2"),
                "Output should include the overall 'Total Messages Used: 2 statistic.");
        assertTrue(output.contains("Bot Number: 0 ChatBot Name: ChatGPT-3.5"),
                "Output should include usage details for Bot Number 0.");
        assertTrue(output.contains("Bot Number: 1 ChatBot Name: LlaMa"),
                "Output should include usage details for Bot Number 1.");
    }
}
