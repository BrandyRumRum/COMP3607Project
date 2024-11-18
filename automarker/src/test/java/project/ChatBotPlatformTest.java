package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Brandon_Ramcharitar_816037181_A1.ChatBotPlatform;

public class ChatBotPlatformTest {
    private ChatBotPlatform platform;

    @BeforeEach
    public void setUp() {
        platform = new ChatBotPlatform();
    }

    @Test
    public void testAddChatBot() {
        // Test adding a chatbot when within the limit
        assertTrue(platform.addChatBot(1), "Should successfully add ChatBot when under limit.");
        assertTrue(platform.getChatBotList().contains("Bot Number: 0"), "ChatBotPlatform should list the added bot.");

        // Test adding another chatbot
        assertTrue(platform.addChatBot(2), "Should successfully add another ChatBot.");
        assertTrue(platform.getChatBotList().contains("Bot Number: 1"), "ChatBotPlatform should list the second added bot.");
}


    @Test
    public void testAddChatBotWhenLimitReached() {
        // Add chatbots up to the limit
        for (int i = 0; i < 10; i++) {
            assertTrue(platform.addChatBot(i), "Should add ChatBot when under limit.");
        }

        // Check that adding a chatbot beyond the limit fails
        assertFalse(platform.addChatBot(11), "Should fail to add ChatBot when limit is reached.");
    }

    @Test
    public void testGetChatBotList() {
        platform.addChatBot(1);
        platform.addChatBot(2);
        String list = platform.getChatBotList();

        // Verify the list format and contents
        assertTrue(list.contains("ChatBot Name: LLaMa"), "List should contain ChatBot with name 'LLaMa'.");
        assertTrue(list.contains("ChatBot Name: Mistral7B"), "List should contain ChatBot with name 'Mistral7B'.");
        assertTrue(list.contains("Total Messages Remaining: 10"), "Total Messages Remaining should match initial limit.");
    }

    @Test
    public void testInteractWithBot() {
        platform.addChatBot(1);
        platform.addChatBot(2);

        // Valid bot interaction
        String response1 = platform.interactWithBot(0, "Hello, LLaMa!");
        assertTrue(response1.contains("Response from LLaMa"), "Interaction with bot 0 should be successful.");

        String response2 = platform.interactWithBot(1, "Hello, Mistral7B!");
        assertTrue(response2.contains("Response from Mistral7B"), "Interaction with bot 1 should be successful.");

        // Invalid bot interaction
        String responseInvalid = platform.interactWithBot(3, "Hello?");
        assertEquals("Incorrect Bot Number (3) Selected. Try again", responseInvalid, 
                "Should return error message for invalid bot number.");
    }
}