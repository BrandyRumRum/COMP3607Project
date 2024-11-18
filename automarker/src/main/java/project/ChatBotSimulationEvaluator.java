package project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class ChatBotSimulationEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
    }

    @Override
    protected void evaluateMethods(Class<?> clazz) {
        evaluateMainMethod(clazz);
    }

    private void evaluateMainMethod(Class<?> clazz) {
        try {

            Method mainMethod = clazz.getDeclaredMethod("main", String[].class);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
        
            mainMethod.invoke(null, (Object) new String[]{});
        
            System.setOut(originalOut);
        
            String output = outputStream.toString();
        
            try {
                if (output.contains("Hello World!")) {
                    totalScore += 1; // Award 1 mark for printing "Hello World!"
                } else {
                    feedback.add("The 'main' method should print 'Hello World!' to the screen.");
                }
            } catch (Exception e) {
                feedback.add("Error checking for 'Hello World!': " + e.getMessage());
            }
        
            try {
                if (output.contains("Bot Number:")) {
                    totalScore += 2; // Award 2 marks for adding ChatBot objects
                } else {
                    feedback.add("The 'main' method should add several ChatBot objects (at least one of each kind) to the ChatBotPlatform.");
                }
            } catch (Exception e) {
                feedback.add("Error checking for ChatBot addition: " + e.getMessage());
            }
        
            try {
                if (output.contains("Total Messages Used: 0") && output.contains("Total Messages Remaining: 10")) {
                    totalScore += 2; // Award 2 marks for printing initial ChatBot list
                } else {
                    feedback.add("The 'main' method should print a list of all ChatBots managed by the ChatBotPlatform with their summary statistics.");
                }
            } catch (Exception e) {
                feedback.add("Error checking for initial ChatBot list: " + e.getMessage());
            }
        
            try {
                int interactionCount = (int) output.lines().filter(line -> line.contains("Response from")).count();
                if (interactionCount >= 15) {
                    totalScore += 4; // Award 4 marks for interacting with ChatBots up to 15 times
                } else {
                    feedback.add("The 'main' method should interact with ChatBots by sending messages and printing their responses to the screen.");
                }
            } catch (Exception e) {
                feedback.add("Error checking for ChatBot interactions: " + e.getMessage());
            }
        
            try {
                if (output.contains("Total Messages Used:") && output.contains("Total Messages Remaining:")) {
                    totalScore += 2; // Award 2 marks for printing final ChatBot list
                } else {
                    feedback.add("The 'main' method should print the final list of all ChatBots managed by the ChatBotPlatform with their summary statistics.");
                }
            } catch (Exception e) {
                feedback.add("Error checking for final ChatBot list: " + e.getMessage());
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("The 'main' method is missing in the class.");
            notifyObserver("Missing main method in ChatBotSimulation class.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'main' method: " + e.getMessage());
        }
        
        feedback.add("Main method evaluation completed.");
        
    }
}
