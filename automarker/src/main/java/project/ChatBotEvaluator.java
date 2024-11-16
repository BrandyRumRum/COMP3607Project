package project;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ChatBotEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        try {
            Field chatBotName = clazz.getDeclaredField("chatBotName");
            if (chatBotName.getType().equals(String.class)) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'chatBotName' should be of type String.");
            }
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'chatBotName'.");
            notifyObserver("Missing attribute: chatBotName");
        }

        try {
            Field numResponsesGenerated = clazz.getDeclaredField("numResponsesGenerated");
            if (numResponsesGenerated.getType().equals(int.class)) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'numResponsesGenerated' should be of type int.");
            }
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'numResponsesGenerated'.");
            notifyObserver("Missing attribute: numResponsesGenerated");
        }
        

        try {
            Field messageLimit = clazz.getDeclaredField("messageLimit");
        
            // Check if the field is of type int
            if (messageLimit.getType().equals(int.class)) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'messageLimit' should be of type int.");
            }
        
            // Check if the field is static
            if (Modifier.isStatic(messageLimit.getModifiers())) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'messageLimit' should be static.");
            }
        
            // Check if the field is initialized to 10
            messageLimit.setAccessible(true);
            int value = messageLimit.getInt(null); // Static field, so pass null
            if (value == 10) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'messageLimit' should be initialized to 10.");
            }
        
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'messageLimit'.");
            notifyObserver("Missing attribute: messageLimit");
        } catch (IllegalAccessException e) {
            feedback.add("Unable to access the value of 'messageLimit'.");
        }
        

        try {
            Field messageNumber = clazz.getDeclaredField("messageNumber");
        
            // Check if the field is of type int
            if (messageNumber.getType().equals(int.class)) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'messageNumber' should be of type int.");
            }
        
            // Check if the field is static
            if (Modifier.isStatic(messageNumber.getModifiers())) {
                totalScore += 1;
            } else {
                feedback.add("Attribute 'messageNumber' should be static.");
            }
        
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'messageNumber'.");
            notifyObserver("Missing attribute: messageNumber");
        }
        
    }

            @Override
            protected void evaluateMethods(Class<?> clazz) {
                try {
            Constructor<?> defaultConstructor = clazz.getConstructor();

            // Check if the default constructor initializes the attributes correctly
            Object instance = defaultConstructor.newInstance();

            // Reflectively check attribute initializations
            Field chatBotName = clazz.getDeclaredField("chatBotName");
            chatBotName.setAccessible(true);
            if ("ChatGPT-3.5".equals(chatBotName.get(instance))) {
                totalScore += 1;
            } else {
                feedback.add("Default constructor should initialize 'chatBotName' using ChatBotGenerator.generateChatBotLLM(0).");
            }

            Field numResponsesGenerated = clazz.getDeclaredField("numResponsesGenerated");
            numResponsesGenerated.setAccessible(true);
            if ((int) numResponsesGenerated.get(instance) == 0) {
                totalScore += 1;
            } else {
                feedback.add("Default constructor should initialize 'numResponsesGenerated' to 0.");
            }

            Field messageNumber = clazz.getDeclaredField("messageNumber");
            messageNumber.setAccessible(true);
            if ((int) messageNumber.get(instance) == 0) {
                totalScore += 1;
            } else {
                feedback.add("Default constructor should initialize 'messageNumber' to 0.");
            }

        } catch (NoSuchMethodException e) {
            feedback.add("Missing default constructor for ChatBot class.");
            notifyObserver("Missing default constructor.");
        } catch (NoSuchFieldException e) {
            feedback.add("One or more attributes are missing for constructor initialization check.");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            feedback.add("Error accessing or invoking default constructor: " + e.getMessage());
        }

        
        try {
            // Check for the parameterized constructor with an int parameter
            Constructor<?> paramConstructor = clazz.getConstructor(int.class);
            totalScore += 1; // Award 1 mark for the existence of the parameterized constructor
        
            // Create an instance using the parameterized constructor with a sample LLMCode (e.g., 1)
            Object instance = paramConstructor.newInstance(1);
        
            // Reflectively check that 'chatBotName' is initialized correctly
            Field chatBotName = clazz.getDeclaredField("chatBotName");
            chatBotName.setAccessible(true);
        
            // Define the expected chatbot name for the provided LLMCode
            String expectedChatBotName = getExpectedChatBotName(1); // Expected LLM name for code 1
            if (expectedChatBotName.equals(chatBotName.get(instance))) {
                totalScore += 1; // Award 1 mark for correct initialization of 'chatBotName'
            } else {
                feedback.add("Parameterized constructor should initialize 'chatBotName' based on the LLMCode.");
            }
        
            // Check that 'numResponsesGenerated' is initialized to 0
            Field numResponsesGenerated = clazz.getDeclaredField("numResponsesGenerated");
            numResponsesGenerated.setAccessible(true);
            if ((int) numResponsesGenerated.get(instance) == 0) {
                totalScore += 0.5; // Award 0.5 mark for initializing 'numResponsesGenerated' to 0
            } else {
                feedback.add("Parameterized constructor should initialize 'numResponsesGenerated' to 0.");
            }
        
            // Check that 'messageNumber' is initialized to 0
            Field messageNumber = clazz.getDeclaredField("messageNumber");
            messageNumber.setAccessible(true);
            if ((int) messageNumber.get(instance) == 0) {
                totalScore += 0.5; // Award 0.5 mark for initializing 'messageNumber' to 0
            } else {
                feedback.add("Parameterized constructor should initialize 'messageNumber' to 0.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing parameterized constructor for ChatBot class.");
            notifyObserver("Missing parameterized constructor.");
        } catch (NoSuchFieldException e) {
            feedback.add("One or more attributes are missing for constructor initialization check.");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            feedback.add("Error accessing or invoking parameterized constructor: " + e.getMessage());
        }


        try {
            Method getChatBotName = clazz.getDeclaredMethod("getChatBotName");
            if (getChatBotName.getReturnType().equals(String.class)) {
                totalScore += 1;
            } else {
                feedback.add("Method 'getChatBotName' should return type String.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getChatBotName'.");
            notifyObserver("Missing method: getChatBotName");
        }

        try {
            // Check getNumResponsesGenerated() - Accessor for numResponsesGenerated
            Method getNumResponsesGenerated = clazz.getDeclaredMethod("getNumResponsesGenerated");
            if (getNumResponsesGenerated.getReturnType().equals(int.class)) {
                totalScore += 1; // Award 1 mark for correct return type
            } else {
                feedback.add("Method 'getNumResponsesGenerated' should return type int.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getNumResponsesGenerated'.");
            notifyObserver("Missing method: getNumResponsesGenerated");
        } catch (Exception e) {
            feedback.add("Error validating 'getNumResponsesGenerated': " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method getTotalNumResponsesGenerated = clazz.getDeclaredMethod("getTotalNumResponsesGenerated");
            totalScore += 0.5; // Award 0.5 mark for the method's existence
        
            // Check if the method is static
            if (Modifier.isStatic(getTotalNumResponsesGenerated.getModifiers())) {
                totalScore += 0.5; // Award 0.5 mark for being static
            } else {
                feedback.add("Method 'getTotalNumResponsesGenerated' should be static.");
            }
        
            // Check if the method returns type int
            if (getTotalNumResponsesGenerated.getReturnType().equals(int.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'getTotalNumResponsesGenerated' should return type int.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getTotalNumResponsesGenerated'.");
            notifyObserver("Missing method: getTotalNumResponsesGenerated");
        } catch (Exception e) {
            feedback.add("Error validating 'getTotalNumResponsesGenerated': " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method getTotalNumMessagesRemaining = clazz.getDeclaredMethod("getTotalNumMessagesRemaining");
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method is static
            if (Modifier.isStatic(getTotalNumMessagesRemaining.getModifiers())) {
                totalScore += 1; // Award 1 mark for being static
            } else {
                feedback.add("Method 'getTotalNumMessagesRemaining' should be static.");
            }
        
            // Check if the method returns type int
            if (getTotalNumMessagesRemaining.getReturnType().equals(int.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'getTotalNumMessagesRemaining' should return type int.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getTotalNumMessagesRemaining'.");
            notifyObserver("Missing method: getTotalNumMessagesRemaining");
        } catch (Exception e) {
            feedback.add("Error validating 'getTotalNumMessagesRemaining': " + e.getMessage());
        }

        try {
            // Check if the method exists
            Method limitReached = clazz.getDeclaredMethod("limitReached");
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method is static
            if (Modifier.isStatic(limitReached.getModifiers())) {
                totalScore += 1; // Award 1 mark for being static
            } else {
                feedback.add("Method 'limitReached' should be static.");
            }
        
            // Check if the method returns type boolean
            if (limitReached.getReturnType().equals(boolean.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'limitReached' should return type boolean.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'limitReached'.");
            notifyObserver("Missing method: limitReached");
        } catch (Exception e) {
            feedback.add("Error validating 'limitReached': " + e.getMessage());
        }

        
        try {
            // Check if the method exists
            Method generateResponse = clazz.getDeclaredMethod("generateResponse");
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the method is private
            if (Modifier.isPrivate(generateResponse.getModifiers())) {
                totalScore += 1; // Award 1 mark for being private
            } else {
                feedback.add("Method 'generateResponse' should be private.");
            }
        
            // Check if the method returns type String
            if (generateResponse.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'generateResponse' should return type String.");
            }
        
            // Check if the method increments the chatbot's message count
            Field numResponsesGenerated = clazz.getDeclaredField("numResponsesGenerated");
            if (numResponsesGenerated != null) {
                totalScore += 1; // Award 1 mark for interacting with 'numResponsesGenerated'
            } else {
                feedback.add("Method 'generateResponse' should increment the chatbot's message count.");
            }
        
            // Check if the method increments the total message count
            Field messageNumber = clazz.getDeclaredField("messageNumber");
            if (messageNumber != null && Modifier.isStatic(messageNumber.getModifiers())) {
                totalScore += 1; // Award 1 mark for interacting with the static 'messageNumber'
            } else {
                feedback.add("Method 'generateResponse' should increment the total message count.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'generateResponse'.");
            notifyObserver("Missing method: generateResponse");
        } catch (NoSuchFieldException e) {
            feedback.add("One or more fields ('numResponsesGenerated' or 'messageNumber') are missing.");
        } catch (Exception e) {
            feedback.add("Error validating 'generateResponse': " + e.getMessage());
        }
        
        try {
            // Check if the prompt method exists
            Method prompt = clazz.getDeclaredMethod("prompt", String.class);
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the prompt method returns type String
            if (prompt.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'prompt(String requestMessage)' should return type String.");
            }
        
            // Check if the prompt method calls limitReached
            Method limitReached = clazz.getDeclaredMethod("limitReached");
            if (prompt.toString().contains(limitReached.getName())) {
                totalScore += 1; // Award 1 mark for calling 'limitReached'
            } else {
                feedback.add("Method 'prompt' should call 'limitReached' to check the message limit.");
            }
        
            // Check if the prompt method calls generateResponse
            Method generateResponse = clazz.getDeclaredMethod("generateResponse");
            if (prompt.toString().contains(generateResponse.getName())) {
                totalScore += 1; // Award 1 mark for calling 'generateResponse'
            } else {
                feedback.add("Method 'prompt' should call 'generateResponse' to process incoming messages.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: " + e.getMessage());
            notifyObserver("Missing method in class evaluation.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'prompt': " + e.getMessage());
        }

        try {
            // Check if the toString method exists
            Method toStringMethod = clazz.getDeclaredMethod("toString");
            totalScore += 1; // Award 1 mark for the method's existence
        
            // Check if the toString method returns type String
            if (toStringMethod.getReturnType().equals(String.class)) {
                totalScore += 1; // Award 1 mark for the correct return type
            } else {
                feedback.add("Method 'toString()' should return type String.");
            }
        
            // Check if the toString method references getChatBotName
            Method getChatBotName = clazz.getDeclaredMethod("getChatBotName");
            if (toStringMethod.toString().contains(getChatBotName.getName())) {
                totalScore += 1; // Award 1 mark for including 'getChatBotName'
            } else {
                feedback.add("Method 'toString' should include the chatbot's name using 'getChatBotName'.");
            }
        
            // Check if the toString method references getNumResponsesGenerated
            Method getNumResponsesGenerated = clazz.getDeclaredMethod("getNumResponsesGenerated");
            if (toStringMethod.toString().contains(getNumResponsesGenerated.getName())) {
                totalScore += 1; // Award 1 mark for including 'getNumResponsesGenerated'
            } else {
                feedback.add("Method 'toString' should include the number of responses using 'getNumResponsesGenerated'.");
            }
        
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: " + e.getMessage());
            notifyObserver("Missing method in class evaluation.");
        } catch (Exception e) {
            feedback.add("Error evaluating 'toString': " + e.getMessage());
        }

        //Additional checks can be added.
    }
    
        // Helper method to define the expected chatbot name based on the LLMCode
    private String getExpectedChatBotName(int LLMCode) {
    switch (LLMCode) {
        case 1:
            return "LLaMa";
        case 2:
            return "Mistral7B";
        case 3:
            return "Bard";
        case 4:
            return "Claude";
        case 5:
            return "Solar";
        default:
            return "ChatGPT-3.5"; // Default chatbot name if the code doesn't match
    }
}
}
