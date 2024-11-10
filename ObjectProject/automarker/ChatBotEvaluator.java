package project;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
            if (messageLimit.getType().equals(int.class)) {
                totalScore += 3;
            } else {
                feedback.add("Attribute 'messageLimit' should be of type int.");
            }
        } catch (NoSuchFieldException e) {
            feedback.add("Missing attribute: 'messageLimit'.");
            notifyObserver("Missing attribute: messageLimit");
        }

        try {
            Field messageNumber = clazz.getDeclaredField("messageNumber");
            if (messageNumber.getType().equals(int.class)) {
                totalScore += 2;
            } else {
                feedback.add("Attribute 'messageNumber' should be of type int.");
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
            totalScore += 3;
        } catch (NoSuchMethodException e) {
            feedback.add("Missing default constructor for ChatBot class.");
            notifyObserver("Missing default constructor");
        }

        try {
            Constructor<?> paramConstructor = clazz.getConstructor(int.class);
            totalScore += 3;
        } catch (NoSuchMethodException e) {
            feedback.add("Missing parameterized constructor for ChatBot class.");
            notifyObserver("Missing parameterized constructor");
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
            Method generateResponse = clazz.getDeclaredMethod("generateResponse");
            if (generateResponse.getReturnType().equals(String.class) && Modifier.isPrivate(generateResponse.getModifiers())) {
                totalScore += 5;
            } else {
                feedback.add("Method 'generateResponse' should return type String and be private.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'generateResponse'.");
            notifyObserver("Missing method: generateResponse");
        }

        //Additional checks can be added.
    }
}
