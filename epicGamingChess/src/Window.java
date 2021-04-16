import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    private static Window window = null;
    
    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "gaming";
    }
    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run() {
        init();
        loop();

        //free memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new IllegalStateException("unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        
        if(glfwWindow == NULL) {
            throw new IllegalStateException("failed to create glfw window");
        }

        glfwSetCursorPosCallback(glfwWindow, mouseInput::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, mouseInput::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, mouseInput::mouseScrollCallback);

        glfwMakeContextCurrent(glfwWindow);
        //vsync
        glfwSwapInterval(1);

        //make visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();

            glClearColor(.5f, .5f, .5f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }

    }
    
}