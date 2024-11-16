package project;
public class EvaluatorFactory {

    public static BaseEvaluator getEvaluator(String className) {
        switch (className) {
            case "ChatBot":
                return new ChatBotEvaluator();
            case "ChatBotPlatform":
                return new ChatBotPlatformEvaluator();
            case: "ChatBotGenerator":
                return new ChatBotGeneratorEvaluator():
            case: "ChatBotSimulation":
                return new ChatBotSimulationEvaluator();
            default:
                throw new IllegalArgumentException("Unknown evaluator type: " + className);
        }
    }
}
