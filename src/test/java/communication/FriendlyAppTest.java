package communication;

import org.junit.jupiter.api.Test;
import process.ProcessRequest;
import process.ProcessResult;
import process.ProcessUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FriendlyAppTest {
    @Test
    void simple_out() throws IOException, InterruptedException {
        ProcessRequest request = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Hello");
        ProcessResult result = ProcessUtil.run(request);
        assertEquals("Hello:)", result.getOutputs());
    }

    @Test
    void simple_err() throws IOException, InterruptedException {
        ProcessRequest request = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Bye");
        ProcessResult result = ProcessUtil.run(request);
        assertEquals("Are you leaving already?:(", result.getErrors());
    }

    @Test
    void difficult_out() throws IOException, InterruptedException {
        ProcessRequest request = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Count");
        ProcessResult result = ProcessUtil.run(request);
        assertEquals("One\nTwo\nThree", result.getOutputs());
    }

    @Test
    void several_processes() throws IOException, InterruptedException {
        ProcessRequest request1 = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Hello");
        ProcessRequest request2 = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Bye");
        ProcessResult result1 = ProcessUtil.run(request1);
        ProcessResult result2 = ProcessUtil.run(request2);
        assertEquals("Hello:)", result1.getOutputs());
        assertEquals("Are you leaving already?:(", result2.getErrors());
    }

    @Test
    void bad_argument() throws IOException, InterruptedException {
        ProcessRequest request = new ProcessRequest("java -jar target/simpleMavenApp-1.0-SNAPSHOT.jar Blabla", "Have a nice day!");
        ProcessResult result = ProcessUtil.run(request);
        assertEquals("I don't understand you:(", result.getErrors());
    }
}