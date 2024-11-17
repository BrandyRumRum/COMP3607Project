package project;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ChatBotGeneratorEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        feedback.add("No specific attributes required for ChatBotGenerator.");
    }

    @Override
    protected void evaluateMethods(Class<?> clazz) {
        try {
            Method generateChatBotLLM = clazz.getDeclaredMethod("generateChatBotLLM", int.class);
            if (generateChatBotLLM.getReturnType().equals(String.class) && Modifier.isStatic(generateChatBotLLM.getModifiers())) {
                totalScore += 7;
                testGenerateChatBotLLMMethod(clazz, generateChatBotLLM);
            } else {
                feedback.add("Method 'generateChatBotLLM' should be static and return type String.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'generateChatBotLLM'.");
            notifyObserver("Missing generateChatBotLLM method in ChatBotGenerator class.");
        }
    }

    // Testing  behavior of generateChatBot with different input codes
    private void testGenerateChatBotLLMMethod(Class<?> clazz, Method method) {
        try {
            String result1 = (String) method.invoke(null, 1);
            if (!"LLaMa".equals(result1)) {
                feedback.add("For input 1, expected 'LLaMa' but got '" + result1 + "'.");
            }

            String result2 = (String) method.invoke(null, 2);
            if (!"Mistral7B".equals(result2)) {
                feedback.add("For input 2, expected 'Mistral7B' but got '" + result2 + "'.");
            }

            String result3 = (String) method.invoke(null, 3);
            if (!"Bard".equals(result3)) {
                feedback.add("For input 3, expected 'Bard' but got '" + result3 + "'.");
            }

            String result4 = (String) method.invoke(null, 4);
            if (!"Claude".equals(result4)) {
                feedback.add("For input 4, expected 'Claude' but got '" + result4 + "'.");
            }

            String result5 = (String) method.invoke(null, 5);
            if (!"Solar".equals(result5)) {
                feedback.add("For input 5, expected 'Solar' but got '" + result5 + "'.");
            }

            String resultDefault = (String) method.invoke(null, 99);
            if (!"ChatGPT-3.5".equals(resultDefault)) {
                feedback.add("For any other input, expected 'ChatGPT-3.5' but got '" + resultDefault + "'.");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            feedback.add("Error invoking method 'generateChatBotLLM': " + e.getMessage());
        }
    }
}