import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileProcessorTest {

    @Test
    public void testProcessFile() throws IOException {
        // Create a temporary test file
        Path tempFile = Files.createTempFile("testFile", ".txt");
        Files.write(tempFile, "Mary had a little lamb. Michael found a mysterious map.".getBytes());

        // Process the file
        TextFileProcessor.Results results = TextFileProcessor.processFile(tempFile.toString());

        // Assertions
        assertEquals(2, results.getMStartCount());
        assertTrue(results.getLongWords().contains("mysterious"));
        assertTrue(results.getLongWords().size() == 1);

        // Clean up
        Files.delete(tempFile);
    }
}
