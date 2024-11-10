package project;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
            Constructor<?> defaultConstructor = clazz.getConstructor();
            totalScore += 2;
        } catch (NoSuchMethodException e) {
            feedback.add("Missing default constructor for ChatBotPlatform class.");
            notifyObserver("Missing default constructor");
        }

        try {
            Method addChatBot = clazz.getDeclaredMethod("addChatBot", int.class);
            if (addChatBot.getReturnType().equals(boolean.class)) {
                totalScore += 5;
            } else {
                feedback.add("Method 'addChatBot' should return type boolean.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'addChatBot'.");
            notifyObserver("Missing method: addChatBot");
        }

        try {
            Method getChatBotList = clazz.getDeclaredMethod("getChatBotList");
            if (getChatBotList.getReturnType().equals(String.class)) {
                totalScore += 6;
            } else {
                feedback.add("Method 'getChatBotList' should return type String.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'getChatBotList'.");
            notifyObserver("Missing method: getChatBotList");
        }

        try {
            Method interactWithBot = clazz.getDeclaredMethod("interactWithBot", int.class, String.class);
            if (interactWithBot.getReturnType().equals(String.class)) {
                totalScore += 5;
            } else {
                feedback.add("Method 'interactWithBot' should return type String.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'interactWithBot'.");
            notifyObserver("Missing method: interactWithBot");
        }
    }
}
