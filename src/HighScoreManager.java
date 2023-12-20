import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for managing the high score
 */
public final class HighScoreManager {
    private static final String HIGH_SCORE_FILE_PATH = "data/" + "highscore.txt";

    /**
     * @return the current high score
     * @throws RuntimeException if the file cannot be read
     */
    public static int getHighScore() throws RuntimeException {
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

    /**
     * Sets the high score
     *
     * @param score the new high score
     * @throws RuntimeException if the file cannot be written to
     */
    public static void setHighScore(int score) throws RuntimeException {
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
