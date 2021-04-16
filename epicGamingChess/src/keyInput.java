import static org.lwjgl.glfw.GLFW.*;

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
