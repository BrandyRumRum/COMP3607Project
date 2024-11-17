package project;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class ChatBotPlatformEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        try {
            Field bots = clazz.getDeclaredField("bots");
            if (bots.getType().getSimpleName().equals("ArrayList")) {
                totalScore += 2;
            } else {
                feedback.add("Attribute 'bots' should be of type ArrayList<ChatBot>.");
            }
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'bots'.");
            notifyObserver("Missing attribute: bots");
        }
    }

    @Override
    protected void evaluateMethods(Class<?> clazz) {
        try {
            // Check for the existence of the default constructor
            Constructor<?> defaultConstructor = clazz.getConstructor();
            totalScore += 1; // Award 1 mark for the constructor's existence

            // Check if the constructor initializes the bots collection
            Object instance = defaultConstructor.newInstance(); // Create an instance of the class
            Field botsField = clazz.getDeclaredField("bots"); // Ensure the bots collection exists
            botsField.setAccessible(true);

            // Check if bots is initialized
            Object botsValue = botsField.get(instance);
            if (botsValue instanceof Collection) {
                totalScore += 1; // Award 1 mark for initializing the bots collection
            } else {
                feedback.add("The bots collection should be initialized in the default constructor.");
            }

        } catch (NoSuchMethodException e) {
            feedback.add("Missing default constructor for ChatBotPlatform class.");
            notifyObserver("Missing default constructor");
        } catch (NoSuchFieldException e) {
            feedback.add("Missing field: 'bots' in ChatBotPlatform class.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            feedback.add("Error creating an instance of ChatBotPlatform: " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method addChatBot = clazz.getDeclaredMethod("addChatBot", int.class);
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method returns type boolean
            if (addChatBot.getReturnType().equals(boolean.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'addChatBot' should return type boolean.");
            }
        
            // Check if the method references the bots collection
            Field botsField = clazz.getDeclaredField("bots");
            if (botsField != null) {
                totalScore += 1; // Award 1 mark for interacting with the bots collection
            } else {
                feedback.add("Method 'addChatBot' should interact with the bots collection.");
            }
        
            // Check if the method calls limitReached()
            Method limitReached = clazz.getDeclaredMethod("limitReached");
            if (addChatBot.toString().contains(limitReached.getName())) {
                totalScore += 1; // Award 1 mark for checking the message limit with limitReached
            } else {
                feedback.add("Method 'addChatBot' should call 'limitReached' to check the message limit.");
            }
        
            // Check if a new ChatBot is added using the bots collection
            if (addChatBot.toString().contains("new ChatBot") && addChatBot.toString().contains("add")) {
                totalScore += 1; // Award 1 mark for creating and adding a ChatBot to the bots collection
            } else {
                feedback.add("Method 'addChatBot' should create a new ChatBot object and add it to the bots collection.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'addChatBot'.");
            notifyObserver("Missing method: addChatBot");
        } catch (NoSuchFieldException e) {
            feedback.add("Missing field: 'bots' in ChatBotPlatform class.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'addChatBot': " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method getChatBotList = clazz.getDeclaredMethod("getChatBotList");
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method returns type String
            if (getChatBotList.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'getChatBotList' should return type String.");
            }
        
            // Check if the method references the bots collection
            Field botsField = clazz.getDeclaredField("bots");
            if (botsField != null) {
                totalScore += 1; // Award 1 mark for interacting with the bots collection
            } else {
                feedback.add("Method 'getChatBotList' should interact with the bots collection.");
            }
        
            // Check if the method generates formatted chatbot information
            if (getChatBotList.toString().contains("toString")) {
                totalScore += 1; // Award 1 mark for formatting chatbot details
            } else {
                feedback.add("Method 'getChatBotList' should generate formatted information about chatbots.");
            }
        
            // Check if the method includes summary statistics
            if (getChatBotList.toString().contains("Total Messages Used") && getChatBotList.toString().contains("Total Messages Remaining")) {
                totalScore += 1; // Award 1 mark for including summary statistics
            } else {
                feedback.add("Method 'getChatBotList' should include summary usage statistics.");
            }
        
            // Check if the method handles an empty bots collection gracefully
            if (getChatBotList.toString().contains("No chatbots available")) {
                totalScore += 1; // Award 1 mark for handling empty bots collection
            } else {
                feedback.add("Method 'getChatBotList' should handle an empty bots collection gracefully.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getChatBotList'.");
            notifyObserver("Missing method: getChatBotList");
        } catch (NoSuchFieldException e) {
            feedback.add("Missing field: 'bots' in ChatBotPlatform class.");
        } catch (Exception e) {
            feedback.add("Error validating 'getChatBotList': " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method interactWithBot = clazz.getDeclaredMethod("interactWithBot", int.class, String.class);
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method returns type String
            if (interactWithBot.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'interactWithBot' should return type String.");
            }
        
            // Check if the method references the bots collection
            Field botsField = clazz.getDeclaredField("bots");
            if (botsField != null) {
                totalScore += 1; // Award 1 mark for interacting with the bots collection
            } else {
                feedback.add("Method 'interactWithBot' should interact with the bots collection.");
            }
        
            // Check if the method handles invalid botNumber values
            if (interactWithBot.toString().contains("Incorrect Bot Number")) {
                totalScore += 1; // Award 1 mark for handling invalid botNumber correctly
            } else {
                feedback.add("Method 'interactWithBot' should handle invalid botNumber values and return an appropriate message.");
            }
        
            // Check if the method passes a message to the correct chatbot
            if (interactWithBot.toString().contains("prompt")) {
                totalScore += 1; // Award 1 mark for passing the message to the correct chatbot
            } else {
                feedback.add("Method 'interactWithBot' should pass the message to the correct chatbot and return its response.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'interactWithBot'.");
            notifyObserver("Missing method: interactWithBot");
        } catch (NoSuchFieldException e) {
            feedback.add("Missing field: 'bots' in ChatBotPlatform class.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'interactWithBot': " + e.getMessage());
        }
    }
}
