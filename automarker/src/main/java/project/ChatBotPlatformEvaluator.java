package project;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public class ChatBotPlatformEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        evaluateBots(clazz);
    }

    private void evaluateBots(Class<?> clazz) {
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
        evaluateChatBotPlatformConstructor(clazz);
        evaluateAddChatBot(clazz);
        evaluateGetChatBotList(clazz);
        evaluateInteractWithBot(clazz);
    }

    private void evaluateChatBotPlatformConstructor(Class<?> clazz) {
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
    }

    private void evaluateAddChatBot(Class<?> clazz) {
        try {
            System.out.println("Evaluating class: " + clazz.getName());

            Method addChatBot = clazz.getDeclaredMethod("addChatBot", int.class);

            if (addChatBot.getReturnType().equals(boolean.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'addChatBot' should return type boolean.");
            }

            Field botsField = null;
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().getName().contains("ArrayList")) {
                    botsField = field;
                    totalScore += 1; // Award 1 mark for having a collection field
                    break;
                }
            }

            if (botsField == null) {
                feedback.add("Method 'addChatBot' should interact with a bots collection.");
            } else {
                botsField.setAccessible(true);

                Object instance = clazz.getDeclaredConstructor().newInstance();

                addChatBot.invoke(instance, 1); // Simulate adding a ChatBot with LLMCode 1

                Object bots = botsField.get(instance);
                if (bots instanceof List && ((List<?>) bots).size() > 0) {
                    totalScore += 1; // Award 1 mark for successfully adding a ChatBot
                } else {
                    feedback.add("Method 'addChatBot' should add a ChatBot to the bots collection.");
                }
            }

            try {
                Method limitReached = clazz.getDeclaredMethod("limitReached");
                String addChatBotString = addChatBot.toString();
                if (addChatBotString.contains(limitReached.getName())) {
                    totalScore += 1; // Award 1 mark for limit checking
                } else {
                    feedback.add("Method 'addChatBot' should call 'limitReached' to check the message limit.");
                }
            } catch (NoSuchMethodException e) {
                feedback.add("Missing method: 'limitReached'.");
            }

        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'addChatBot'.");
            notifyObserver("Missing method: addChatBot");
        } catch (Exception e) {
            feedback.add("Error evaluating 'addChatBot': " + e.getMessage());
        }
    }


    private void evaluateGetChatBotList(Class<?> clazz) {
        try {
            Method getChatBotList = clazz.getDeclaredMethod("getChatBotList");
            totalScore += 1; // Award 1 mark for the method's existence

            if (getChatBotList.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'getChatBotList' should return type String.");
            }

            Field botsField = clazz.getDeclaredField("bots");
            if (botsField != null) {
                totalScore += 1; // Award 1 mark for interacting with the bots collection
            } else {
                feedback.add("Method 'getChatBotList' should interact with the bots collection.");
            }

            if (getChatBotList.toString().contains("toString")) {
                totalScore += 1; // Award 1 mark for formatting chatbot details
            } else {
                feedback.add("Method 'getChatBotList' should generate formatted information about chatbots.");
            }

            if (getChatBotList.toString().contains("Total Messages Used")
                    && getChatBotList.toString().contains("Total Messages Remaining")) {
                totalScore += 1; // Award 1 mark for including summary statistics
            } else {
                feedback.add("Method 'getChatBotList' should include summary usage statistics.");
            }

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
    }

    private void evaluateInteractWithBot(Class<?> clazz) {
        try {
            Class<?> platformClass = clazz;
        
            Object platformInstance = platformClass.getDeclaredConstructor().newInstance();
        
            Method addChatBotMethod = platformClass.getDeclaredMethod("addChatBot", int.class);
        
            addChatBotMethod.invoke(platformInstance, 0); 

            Method interactWithBotMethod = platformClass.getDeclaredMethod("interactWithBot", int.class, String.class);
        
            String invalidResponse = (String) interactWithBotMethod.invoke(platformInstance, 10, "Test Message");
            if (invalidResponse.contains("Incorrect Bot Number")) {
                totalScore += 2; // Award 2 marks for handling invalid botNumber values
            } else {
                feedback.add("Method 'interactWithBot' should handle invalid botNumber values and return an appropriate message.");
            }

            String validResponse = (String) interactWithBotMethod.invoke(platformInstance, 0, "Test Message");
            if (validResponse.contains("Response from")) {
                totalScore += 2; // Award 2 marks for passing the message to the correct chatbot and returning the response
            } else {
                feedback.add("Method 'interactWithBot' should pass the message to the correct chatbot and return its response.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method 'interactWithBot' in ChatBotPlatform class.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'interactWithBot' method: " + e.getMessage());
        }
    }
}
