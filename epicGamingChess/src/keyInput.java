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

public class keyInput {
    private static keyInput instance;
    private boolean keyPressed[] = new boolean[350];

    private keyInput() {
        int x = 1;
    }

    public static keyInput get() {
        if(keyInput.instance == null) {
            keyInput.instance = new keyInput();
        }

        return keyInput.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if(action == GLFW_PRESS) {
            get().keyPressed[key] = true;

        } else if(action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        if(keyCode < get().keyPressed.length) {
        return get().keyPressed[keyCode];
        }else{
            return false;
        }
    }
    
}
