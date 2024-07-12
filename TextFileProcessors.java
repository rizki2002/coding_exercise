import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TextFileProcessor {

    /**
     * Processes the specified file to find words starting with 'M' or 'm' and words longer than 5 characters.
     * 
     * @param filePath the path to the text file
     * @return a Results object containing the count of words starting with M/m and a list of long words
     * @throws IOException if the file cannot be read
     */
    public static Results processFile(String filePath) throws IOException {
        Pattern mPattern = Pattern.compile("^m", Pattern.CASE_INSENSITIVE);
        List<String> longWords = new ArrayList<>();
        int mStartCount = 0;

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            for (String line : (Iterable<String>) lines::iterator) {
                for (String word : line.split("\\s+")) {
                    if (word.length() > 5) {
                        longWords.add(word);
                    }
                    if (mPattern.matcher(word).find()) {
                        mStartCount++;
                    }
                }
            }
        }

        return new Results(longWords, mStartCount);
    }

    public static class Results {
        private List<String> longWords;
        private int mStartCount;

        public Results(List<String> longWords, int mStartCount) {
            this.longWords = longWords;
            this.mStartCount = mStartCount;
        }

        public List<String> getLongWords() {
            return longWords;
        }

        public int getMStartCount() {
            return mStartCount;
        }
    }
}
