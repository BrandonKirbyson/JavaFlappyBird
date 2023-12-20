import java.util.Scanner;

/**
 * A Controller class that runs in a separate thread and listens for user to press enter
 */
class Controller implements Runnable {
    private boolean jump = false;

    /**
     * Init the listener
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine();
            jump = true;
        }
    }

    /**
     * Every tick we check if the user has pressed enter.
     * If they have we return true and set jump to false
     *
     * @return whether the user has pressed enter
     */
    public boolean getJump() {
        if (jump) {
            jump = false;
            return true;
        }
        return false;
    }
}
