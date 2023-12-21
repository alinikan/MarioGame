package jade;

// Import necessary LWJGL and GLFW classes.
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

// Static import to make the code more readable by avoiding constant class qualifications.
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    // Fields for window dimensions and title.
    private int width, height;
    private String title;
    // The window handle.
    private long glfwWindow;

    // Singleton pattern to ensure only one window instance.
    private static Window window = null;

    // Private constructor for singleton pattern.
    private Window() {
        this.width = 1920;  // Default window width.
        this.height = 1080; // Default window height.
        this.title = "Mario"; // Default window title.
    }

    // Public method to get the singleton window instance.
    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    // Main method that starts the window lifecycle.
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init(); // Initialize the window.
        loop(); // Start the game/render loop.
    }

    // Initialize the GLFW window and context.
    public void init() {
        // Setup an error callback for GLFW.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Throws an exception if it fails.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        // Configure GLFW with default window hints.
        glfwDefaultWindowHints();
        // Additional window configurations.
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // Window will stay hidden after creation.
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // Window will be resizable.
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE); // Window will be maximized.
        /*glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);*/

        // Create the GLFW window.
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            // Throw an exception if window creation fails.
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Make the OpenGL context current.
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync.
        glfwSwapInterval(1);

        // Make the window visible.
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    // The main game loop.
    public void loop() {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            // Set the clear color and clear the framebuffer.
            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // Swap the color buffers.
            glfwSwapBuffers(glfwWindow);
        }
    }
}
