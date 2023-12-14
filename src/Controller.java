import java.util.Scanner;

class Controller implements Runnable {
    private boolean jump = false;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine();
            jump = true;
        }
    }

    public boolean getJump() {
        if (jump) {
            jump = false;
            return true;
        }
        return false;
    }
}
