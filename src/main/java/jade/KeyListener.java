package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    // Singleton instance of KeyListener
    private static KeyListener instance;

    // Array to track the pressed state of each key
    private boolean keyPressed[] = new boolean[350];

    // Private constructor for the singleton pattern
    private KeyListener() {
        // This constructor is empty because the initialization of the keyPressed array
        // already sets all values to false, which is the desired initial state.
    }

    // Public method to get the singleton instance of KeyListener
    public static KeyListener get() {
        if (KeyListener.instance == null) {
            KeyListener.instance = new KeyListener();
        }

        return KeyListener.instance;
    }

    // Callback method for key events
    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        // Check if the key action is a press or release
        if (action == GLFW_PRESS) {
            // If the key is pressed, set its state to true
            get().keyPressed[key] = true;
        }
        else if (action == GLFW_RELEASE) {
            // If the key is released, set its state to false
            get().keyPressed[key] = false;
        }
        // NOTE FOR MYSELF: GLFW_REPEAT action is not handled here. If needed, it can be added
    }

    // Method to check if a specific key is pressed
    public static boolean isKeyPressed(int keyCode) {
        // Returns the current pressed state of the key
        return get().keyPressed[keyCode];
    }
}
