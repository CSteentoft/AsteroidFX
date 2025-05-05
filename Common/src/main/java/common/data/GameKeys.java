package common.data;

public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pKeys;

    private static final int NUM_KEYS = 4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pKeys = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pKeys[i] = keys[i];
        }
    }

    public void setKey(int i, boolean b) {
        if (i >= 0 && i < NUM_KEYS) {
            keys[i] = b;
        }
    }

    public boolean isPressed(int i) {
        return keys[i] && !pKeys[i];
    }

    public boolean isDown(int i) {
        return keys[i];
    }
}
