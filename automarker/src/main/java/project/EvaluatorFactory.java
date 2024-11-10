package project;
public class EvaluatorFactory {

    public static BaseEvaluator getEvaluator(String className) {
        switch (className) {
            case "ChatBot":
                return new ChatBotEvaluator();
            case "ChatBotPlatform":
                return new ChatBotPlatformEvaluator();
            default:
                throw new IllegalArgumentException("Unknown evaluator type: " + className);
        }
    }
}
