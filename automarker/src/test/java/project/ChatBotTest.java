package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Brandon_Ramcharitar_816037181_A1.ChatBot;

public class ChatBotTest {
    private ChatBot chatBot;

    @BeforeEach
    public void setUp() {
        chatBot = new ChatBot();
    }

    @Test
    public void testDefaultChatBotName() {
        assertEquals("ChatGPT-3.5", chatBot.getChatBotName(), "Default ChatBot name should be ChatGPT-3.5.");
    }

    @Test
    public void testParameterizedConstructor() {
        ChatBot bot1 = new ChatBot(1);
        assertEquals("LLaMa", bot1.getChatBotName(), "ChatBot name for code 1 should be LLaMa.");

        ChatBot bot2 = new ChatBot(2);
        assertEquals("Mistral7B", bot2.getChatBotName(), "ChatBot name for code 2 should be Mistral7B.");

        ChatBot botDefault = new ChatBot(99);
        assertEquals("ChatGPT-3.5", botDefault.getChatBotName(), "Unknown code should default to ChatGPT-3.5.");
    }

    @Test
    public void testResponseGenerationWithinLimit() {
        for (int i = 0; i < ChatBot.getTotalNumMessagesRemaining(); i++) {
            assertNotNull(chatBot.generateResponse(), "ChatBot should generate a response within the limit.");
        }
    }

    @Test
    public void testResponseLimitReached() {
        ChatBot chatBot = new ChatBot();

        while (!ChatBot.limitReached()) {
        chatBot.generateResponse();
        }

        assertTrue(ChatBot.limitReached(), "ChatBot should reach the message limit.");

        String response = chatBot.generateResponse();

        assertTrue(response.startsWith("Daily Limit Reached"),
            "Response should start with 'Daily Limit Reached'.");
}


    @Test
    public void testToStringFormatIgnoringSpaces() {
    ChatBot chatBot = new ChatBot();

    String expectedFormat = "ChatBot Name: ChatGPT-3.5 Number Messages Used: 0";

    String normalizedExpected = expectedFormat.replaceAll("\\s+", "");
    String normalizedActual = chatBot.toString().replaceAll("\\s+", "");

    assertEquals(normalizedExpected, normalizedActual, "toString format should match expected format, ignoring spaces.");
}

}