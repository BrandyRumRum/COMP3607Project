# **Group13 AutoMarker**

A Java-based automated grading tool designed to extract, compile, and evaluate Java student submissions from ZIP archives. This project utlizes the Observer, Factory and Template design patterns to achieve this.

---

## **Table of Contents**

1. [Overview](#overview)  
2. [Features](#features)  
3. [Installation](#installation)  
4. [Usage](#usage)  
5. [Directory Structure](#directory-structure)  
6. [Technologies Used](#technologies-used)  
7. [Contributing](#contributing)  
8. [License](#license)  

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
- Summary report generation with individual scores.
- Handles invalid submissions gracefully.

---

## **Installation**

### Prerequisites
- **Java Development Kit (JDK)** 11 or later
- **Maven** (recommended for dependency management)
### Steps
1. Clone the repository: ```git clone https://github.com/yourusername/group13-automarker.git```
3. Navigate to the project directory: ```cd group13-automarker```
4. Compile the project: ```mvn compile```
5. Run the application: ```mvn exec:java -Dexec.mainClass="project.AutoMarkerMain"```
