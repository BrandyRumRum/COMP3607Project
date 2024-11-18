package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
// uncomment and fill in respective information after running for the first time for the extraction and evaluation
// import project.FName_LName_ID_A1.ChatBotGenerator;

public class ChatBotGeneratorTest {

    @Test
    public void testGenerateChatBotLLMWithValidCodes() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1), "Code 1 should generate 'LLaMa'.");
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2), "Code 2 should generate 'Mistral7B'.");
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3), "Code 3 should generate 'Bard'.");
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4), "Code 4 should generate 'Claude'.");
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5), "Code 5 should generate 'Solar'.");
    }

    @Test
    public void testGenerateChatBotLLMWithInvalidCode() {
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(99),
                "Unknown code should generate default 'ChatGPT-3.5'.");
    }
}
