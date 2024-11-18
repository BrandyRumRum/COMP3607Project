package project;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEvaluator {
    protected int totalScore = 0;
    protected List<String> feedback = new ArrayList<>();

    // Template method that defines the evaluation process
    public final void evaluateClass(Class<?> clazz) {
        setup();
        evaluateAttributes(clazz);
        evaluateMethods(clazz);
        calculateScore();
        teardown();
    }

    // Initial setup before evaluation
    protected void setup() {
        feedback.add("Starting evaluation...");
    }

    // Abstract methods for evaluating attributes and methods, to be implemented by subclasses
    protected abstract void evaluateAttributes(Class<?> clazz);
    protected abstract void evaluateMethods(Class<?> clazz);

    // Calculate the final score and add it to feedback
    protected void calculateScore() {
        feedback.add("Score: " + totalScore);
    }

    // Cleanup and display feedback after evaluation
    protected void teardown() {
        feedback.add("Evaluation completed.");
        displayFeedback();
    }

    // Display the feedback generated during evaluation
    private void displayFeedback() {
        for (String message : feedback) {
            System.out.println(message);
        }
    }

    // Observer method to log issues during evaluation
    protected void notifyObserver(String issue) {
        feedback.add("Observer notification: " + issue);
    }
}
