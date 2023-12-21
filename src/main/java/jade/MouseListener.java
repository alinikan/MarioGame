package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    // Singleton instance of MouseListener
    private static MouseListener instance;

    // Variables to hold scroll, position, and last position data
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;

    // Array to keep track of mouse button states
    private boolean mouseButtonPressed[] = new boolean[3];

    // Flag to determine if the mouse is being dragged
    private boolean isDragging;

    // Private constructor for singleton pattern
    private MouseListener() {
        // Initialize variables
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    // Public method to get the singleton instance of MouseListener
    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    // Callback for mouse position changes
    public static void mousePosCallback(long window, double xpos, double ypos) {
        // Update last positions before setting new ones
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        // Set new positions
        get().xPos = xpos;
        get().yPos = ypos;
        // Check if any mouse button is pressed to set dragging state
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    // Callback for mouse button actions
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        // Check if the action is a press or release
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                // Set the button state to pressed
                get().mouseButtonPressed[button] = true;
            }
        }
        else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                // Set the button state to released and stop dragging
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    // Callback for mouse scroll actions
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        // Set the scroll offsets
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    // Method to be called at the end of each frame
    public static void endFrame() {
        // Reset scroll offsets and update last position
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    // Getters for mouse position, delta position, scroll, and button state
    public static float getX() {
        return (float) get().xPos;
    }

    public static float getY() {
        return (float) get().yPos;
    }

    public static float getDx() {
        return (float) (get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollx() {
        return (float) get().scrollX;
    }

    public static float getScrolly() {
        return (float) get().scrollY;
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    // Method to check if a specific mouse button is down
    public static boolean mouseButtonDown (int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        }
        else {
            return false;
        }
    }
}
