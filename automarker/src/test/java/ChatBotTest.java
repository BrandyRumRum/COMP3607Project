import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        // Generate responses until the limit is reached
        for (int i = 0; i < ChatBot.getTotalNumMessagesRemaining(); i++) {
            chatBot.generateResponse();
        }
        assertTrue(ChatBot.limitReached(), "ChatBot should reach the message limit.");
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage.", chatBot.generateResponse(),
                "Response should indicate daily limit reached.");
    }

    @Test
    public void testToStringFormat() {
        assertEquals("ChatBot Name: ChatGPT-3.5  Number Messages Used: 0", chatBot.toString(),
                "toString format should match expected format.");
    }
}