import java.io.*;
import java.nio.charset.StandardCharsets;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE_PATH = "data/" + "highscore.txt";

    public static int getHighScore() {
        File file = new File(HIGH_SCORE_FILE_PATH);
        if (!file.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setHighScore(int score) {
        try {
            String projectRootPath = new File("").getAbsolutePath();
            File file = new File(projectRootPath, HIGH_SCORE_FILE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile(); // Creates a new file if it does not exist
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8))) {

                writer.write(String.valueOf(score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
