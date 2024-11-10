import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatBotSimulationTest {

    @Test
    public void testMainSimulationRuns() {
        try {
            ChatBotSimulation.main(new String[]{});
        } catch (Exception e) {
            fail("ChatBotSimulation should run without exceptions.");
        }
    }

    @Test
    public void testSimulationInitializesPlatform() {
        ChatBotPlatform platform = new ChatBotPlatform();
        assertEquals(0, platform.bots.size(), "Platform should start with no ChatBots.");
    }

    @Test
    public void testMultipleBotInteraction() {
        ChatBotPlatform platform = new ChatBotPlatform();
        platform.addChatBot(1); // LLaMa
        platform.addChatBot(2); // Mistral7B

        // Interact with each bot and ensure responses are generated
        assertTrue(platform.interactWithBot(0, "Hello").contains("Response from LLaMa"),
                "Interaction with first bot should return response from LLaMa.");
        assertTrue(platform.interactWithBot(1, "Hello").contains("Response from Mistral7B"),
                "Interaction with second bot should return response from Mistral7B.");
    }
}