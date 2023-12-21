# Mario Game!

## Important Note Before Everything
Below is the essential information for setting up and running the Java application. The application uses LWJGL (Lightweight Java Game Library) and GLFW, which necessitates specific configurations for different operating systems, particularly macOS.

## Prerequisites

### Installing Gradle
To build and run this application, Gradle is required. Follow these steps to install Gradle:

1. **Visit the Gradle Installation Guide**: Go to [Gradle Installation](https://docs.gradle.org/current/userguide/installation.html#ex-installing-with-a-package-manager).
2. **Choose an Installation Method**: The page provides various methods to install Gradle, including using a package manager or manual installation.
3. **Follow the Instructions**: Based on your operating system and preferred method, follow the detailed instructions provided on the page.

Ensure that Gradle is properly installed by running `gradle -v` in your terminal or command prompt to check the version.

## Building the Application
1. **Open a Terminal or Command Prompt**.
2. **Navigate to the Project Directory** where the `build.gradle` file is located.
3. **Execute the Build Command**: Run `gradle jar`. This will create an executable JAR file in the `build/libs` directory. The JAR file will be named `Mario-1.0-SNAPSHOT.jar`.

## Running the Application

### General Instructions
1. **Navigate to the `build/libs` Directory** in your terminal or command prompt, where the JAR file is located.
2. **Run the Application**:
    - For **Windows/Linux users**: Use `java -jar Mario-1.0-SNAPSHOT.jar`.
    - For **macOS users**: Special instructions are provided below.

### Special Instructions for macOS Users
macOS requires all UI-related code to run on the main thread. When running the application on macOS, include the `-XstartOnFirstThread` VM option to ensure compliance with macOS's threading model for UI components. Use the following command:

```shell
java -XstartOnFirstThread -jar Mario-1.0-SNAPSHOT.jar
```
