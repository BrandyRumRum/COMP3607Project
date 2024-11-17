# **Group13 AutoMarker**

A Java-based automated grading tool designed to extract, compile, and evaluate Java student submissions from ZIP archives. This project utlizes the Observer, Factory and Template design patterns to achieve this.

---

## **Overview**

The **Group13 AutoMarker** is a tool designed to assist instructors in automatically grading Java assignments. It extracts `.java` files from a ZIP archive, evaluates the submissions based on specific criteria, and generates a score report for each student.

---

## **Features**

- Extract `.java` files from ZIP archives.
- Dynamically load and evaluate Java classes.
- Modular evaluation with custom evaluator classes:
  - `ChatBotEvaluator`
  - `ChatBotPlatformEvaluator`
  - `ChatBotSimulationEvaluator`
  - `ChatBotGenerator`
- Summary report generation with individual scores.
- Handles invalid submissions gracefully.

---

## Project Structure

```
Group13_Automarker-main
├── automarker
│   ├── pom.xml               # Maven configuration file
│   ├── src                   # Source directory
│   │   ├── main
│   │   │   └── java
│   │   │       └── project   # Java source files
│   │   │           ├── A1_Files               # Resource files
│   │   │           ├── AutoMarkerMain.java    # Main entry point
│   │   │           ├── BaseEvaluator.java     # Base class for evaluators
│   │   │           ├── ChatBotEvaluator.java  # Evaluator for chatbot functionality
│   │   │           ├── ChatBotGeneratorEvaluator.java
│   │   │           ├── ChatBotPlatformEvaluator.java
│   │   │           ├── ChatBotSimulationEvaluator.java
│   │   │           ├── EvaluatorFactory.java  # Factory for creating evaluators
│   │   │           └── ZipHandler.java        # Utility for zip file handling
│   │   └── test            # Test directory (if applicable)
│   └── target              # Compiled output directory
└── README.md               # Project documentation
```

## Prerequisites

- **Java**: Ensure you have JDK 8 or higher installed.
- **Maven**: Required for dependency management and building the project.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/automarker.git
   cd automarker
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   java -cp target/automarker-1.0-SNAPSHOT.jar project.AutoMarkerMain
   ```

## Key Classes

- **AutoMarkerMain.java**: The main entry point of the application.
- **BaseEvaluator.java**: Template class for all evaluators.
- **ChatBotEvaluator.java**: Handles chatbot evaluation.
- **ChatBotPlatformEvaluator.java**: Handles chatbot platform evaluation.
- **ChatBotSimulationEvaluator.java**: Handles chatbot simulation evaluation.
- **ChatBotGeneratorEvaluator.java**: Handles chatbot generation evaluation.
- **EvaluatorFactory.java**: Factory for creating evaluator objects.
- **ZipHandler.java**: Utility for zip file extraction and management.
