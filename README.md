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
│   │   │             ├── Feedback               # Folder that contains the PDFs of student's marks
│   │   │           ├── AutoMarkerMain.java    # Main entry point
│   │   │           ├── BaseEvaluator.java     # Base class for evaluators
│   │   │           ├── ChatBotEvaluator.java  # Evaluator for chatbot functionality
│   │   │           ├── ChatBotGeneratorEvaluator.java
│   │   │           ├── ChatBotPlatformEvaluator.java
│   │   │           ├── ChatBotSimulationEvaluator.java
│   │   │           ├── EvaluatorFactory.java  # Factory for creating evaluators
│   │   │           ├── ZipHandler.java        # Utility for zip file handling
│   │   │           └── FeedbackPDFGenerator.java  # Utility for zip file handling
│   │   └── test            # Test directory containing JUnit tests
│   └── target              # Compiled output directory
└── README.md               # Project documentation
```

## Prerequisites

- **Java**: Ensure you have JDK 8 or higher installed.
- **Maven**: Required for dependency management and building the project.

## Getting Started

1. Download the files as a zip


2. Open the automarker folder on your IDE of your choice


3. Place the correctly named and zipped student files in the path:

   ```bash
   src\main\java\project\A1_Files\
   ```
   *Note the student's zip file must contain a parent folder containing their assignment files, the automarker will not work otherwise

   
5. Run the AutoMarkerMain class and feedback will be provided in the Feedback folder located in:

   ```bash
   src\main\java\project\A1_Files\Feedback
   ```
6. After running and reviewing the PDF, the JUnit tests can be ran by substituing the relevant information at the top of the file

   ```bash
   uncomment and fill in respective information after running for the first time for the extraction and evaluation
   import project.FName_LName_ID_A1.ChatBotPlatform;
   ```
7. Finally, observe the passing and failing JUnit tests.
## Key Classes

- **AutoMarkerMain.java**: The main entry point of the application.
- **BaseEvaluator.java**: Template class for all evaluators.
- **ChatBotEvaluator.java**: Handles chatbot evaluation.
- **ChatBotPlatformEvaluator.java**: Handles chatbot platform evaluation.
- **ChatBotSimulationEvaluator.java**: Handles chatbot simulation evaluation.
- **ChatBotGeneratorEvaluator.java**: Handles chatbot generation evaluation.
- **EvaluatorFactory.java**: Factory for creating evaluator objects.
- **ZipHandler.java**: Utility for zip file extraction and management.
- **FeedbackPDFGenerator.java**: Utility for displaying student feedback in a PDF.

## Testing Classes
- **ChatBotGeneratorTest.java**: JUnit test suite for testing the ChatBotGenerator class.
- **ChatBotTest.java**: JUnit test suite for testing the ChatBotTest class.
- **ChatBotPlatformTest.java**: JUnit test suite for testing the ChatBotPlatformTest class.
- **ChatBotSimulationTest.java**: JUnit test suite for testing the ChatBotSimulationTest class.


