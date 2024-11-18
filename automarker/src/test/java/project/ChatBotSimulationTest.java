package project;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class ChatBotSimulationTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    void testHelloWorldPrinted() {
        System.out.println("Hello World!");

        assertTrue(outputStream.toString().contains("Hello World!"), "Hello World! should be printed to the screen.");
    }

    @Test
    void testChatBotPlatformInitialization() {
        ChatBotPlatform platform = new ChatBotPlatform();

        assertNotNull(platform, "ChatBotPlatform should be initialized.");
    }

    @Test
    void testInteractWithChatBots() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0);
        platform.addChatBot(1);

        // Interact with ChatBots up to 15 times
        for (int i = 0; i < 15; i++) {
            int randomBotIndex = i % 2; // Alternate between the two ChatBots
            String response = platform.interactWithBot(randomBotIndex, "Test message " + i);
            System.out.println(response);
        }

        String output = outputStream.toString();
        assertTrue(output.contains("Response from ChatGPT-3.5"), "Responses should include ChatGPT-3.5.");
        assertTrue(output.contains("Response from LLaMa"), "Responses should include LLaMa.");
    }

    @Test
    void testPrintChatBotList() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0); // ChatGPT-3.5
        platform.addChatBot(1); // LLaMa
        platform.addChatBot(2); // Mistral7B
        platform.addChatBot(3); // Bard
        platform.addChatBot(4); // Claude
        platform.addChatBot(5); // Solar

        String chatBotList = platform.getChatBotList();
        System.out.println(chatBotList);

        // Verify the output contains expected information
        String output = outputStream.toString();

        assertTrue(output.contains("Your ChatBots"), "Output should contain the header 'Your ChatBots'.");
        assertTrue(output.contains("Total Messages Used: 0"), "Output should include 'Total Messages Used: 0'.");
        assertTrue(output.contains("Total Messages Remaining: 10"), "Output should include 'Total Messages Remaining: 10'.");
        assertTrue(output.contains("Bot Number: 0"), "Output should list Bot Number: 0.");
        assertTrue(output.contains("Bot Number: 5"), "Output should list Bot Number: 5.");
    }

    @Test
    void testPrintFinalChatBotList() {
        // Initialize ChatBotPlatform and add ChatBots
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(0); 
        platform.addChatBot(1); 

        // Simulate interactions to update statistics
        platform.interactWithBot(0, "Test message 1");
        platform.interactWithBot(1, "Test message 2");

        // Simulate printing final ChatBot list
        System.out.println(platform.getChatBotList());
        String output = outputStream.toString();
        assertTrue(output.contains("Number Messages Used: 1"), "Final ChatBot list should include updated usage statistics.");
    }
}
