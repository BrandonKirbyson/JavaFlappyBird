import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE_PATH = "data/" + "highscore.txt";

    public static int getHighScore() {
        String projectRootPath = new File("").getAbsolutePath();
        File file = new File(projectRootPath, HIGH_SCORE_FILE_PATH);
        file.getParentFile().mkdirs();

        try {
            String content = Files.readString(Paths.get(projectRootPath, HIGH_SCORE_FILE_PATH));
            if (content.trim().isEmpty()) return 0;
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setHighScore(int score) {
        System.out.println(score + " |" + HighScoreManager.getHighScore());
        if (score < HighScoreManager.getHighScore()) return;

        System.out.println("Setting high score to " + score);

        try {
            String projectRootPath = new File("").getAbsolutePath();
            final var writer = new BufferedWriter(new FileWriter(projectRootPath + "/" + HIGH_SCORE_FILE_PATH));
            writer.write("" + score);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
