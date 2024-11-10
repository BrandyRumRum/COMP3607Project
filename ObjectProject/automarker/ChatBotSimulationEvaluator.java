package project;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ChatBotSimulationEvaluator extends BaseEvaluator {

    @Override
    protected void evaluateAttributes(Class<?> clazz) {
        feedback.add("No specific attributes required for ChatBotSimulation.");
    }

    @Override
    protected void evaluateMethods(Class<?> clazz) {
        try {
            Method mainMethod = clazz.getDeclaredMethod("main", String[].class);
            if (Modifier.isStatic(mainMethod.getModifiers()) && mainMethod.getReturnType().equals(void.class)) {
                totalScore += 5;
            } else {
                feedback.add("The 'main' method should be static and return void.");
            }
        } catch (NoSuchMethodException e) {
            feedback.add("Missing method: 'main'.");
            notifyObserver("Missing main method in ChatBotSimulation class.");
        }

        feedback.add("Main method presence and signature evaluation completed.");
    }
}
