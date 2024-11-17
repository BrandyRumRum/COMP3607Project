package project;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ChatBotSimulationEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        feedback.add("No specific attributes required for ChatBotSimulation.");
    }

    @Override
protected void evaluateMethods(Class<?> clazz) {
    try {
        // Check if the main method exists
        Method mainMethod = clazz.getDeclaredMethod("main", String[].class);

        // Check for "Hello World!" printed to the screen
        if (mainMethod.toString().contains("Hello World")) {
            totalScore += 1; // Award 1 mark for printing "Hello World!"
        } else {
            feedback.add("The 'main' method should print 'Hello World!' to the screen.");
        }

        // Check for declaration and initialization of ChatBotPlatform
        Field platformField = clazz.getDeclaredField("platform");
        if (platformField != null) {
            totalScore += 1; // Award 1 mark for initializing ChatBotPlatform
        } else {
            feedback.add("The 'main' method should declare and initialize a ChatBotPlatform object.");
        }

        // Check for adding several ChatBot objects to the platform
        if (mainMethod.toString().contains("addChatBot")) {
            totalScore += 2; // Award 2 marks for adding ChatBot objects
        } else {
            feedback.add("The 'main' method should add several ChatBot objects (at least one of each kind) to the ChatBotPlatform.");
        }

        // Check for printing initial ChatBot list with summary statistics
        if (mainMethod.toString().contains("getChatBotList") && mainMethod.toString().contains("System.out.print")) {
            totalScore += 2; // Award 2 marks for printing the ChatBot list
        } else {
            feedback.add("The 'main' method should print a list of all ChatBots managed by the ChatBotPlatform with their summary statistics.");
        }

        // Check for interacting with ChatBots up to 15 times
        if (mainMethod.toString().contains("interactWithBot")) {
            totalScore += 4; // Award 4 marks for interacting with ChatBots up to 15 times
        } else {
            feedback.add("The 'main' method should interact with ChatBots by sending messages and printing their responses to the screen.");
        }

        // Check for printing the final ChatBot list with summary statistics
        if (mainMethod.toString().contains("getChatBotList") && mainMethod.toString().contains("System.out.print")) {
            totalScore += 2; // Award 2 marks for printing the final ChatBot list
        } else {
            feedback.add("The 'main' method should print the final list of all ChatBots managed by the ChatBotPlatform with their summary statistics.");
        }

    } catch (NoSuchMethodException e) {
        feedback.add("Missing method: 'main'.");
        notifyObserver("Missing main method in ChatBotSimulation class.");
    } catch (NoSuchFieldException e) {
        feedback.add("Missing ChatBotPlatform declaration or initialization in 'main'.");
    } catch (Exception e) {
        feedback.add("Error evaluating 'main' method: " + e.getMessage());
    }

    feedback.add("Main method evaluation completed.");
}
}
