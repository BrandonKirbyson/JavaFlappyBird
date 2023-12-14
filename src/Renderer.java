public class Renderer {
    private static final int width = 100;
    private static final int height = 80;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void render(Bird bird) {
        clearScreen();
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1) {
                    System.out.print("-");
                    continue;
                }
                String[] birdArr = bird.getBirdDrawing();
                int birdRow = i - bird.getY() + birdArr.length / 2;
                int birdCol = j - bird.getX() + birdArr[0].length() / 2;
                if (birdRow >= 0 && birdRow < birdArr.length &&
                        birdCol >= 0 && birdCol < birdArr[birdRow].length()) {
                    System.out.print(birdArr[birdRow].charAt(birdCol));
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
    }
}
