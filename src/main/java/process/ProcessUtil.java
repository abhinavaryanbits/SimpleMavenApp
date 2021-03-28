package process;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProcessUtil {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProcessUtil.class);

    public static ProcessResult run(ProcessRequest request) throws IOException, InterruptedException {
        String outputs;
        String errors;
        boolean timeIsOver;
        log.info("Starting the process");
        Process process = Runtime.getRuntime()
                .exec(request.getRequest());
        if (request.getInputData() != null) writeInput(process, request.getInputData());
        timeIsOver = waitForExit(process, request.getTimeLimit());
        outputs = getAllProcessOutputs(process);
        errors = getAllProcessErrors(process);
        return new ProcessResult(outputs, errors, timeIsOver);
    }

    private static boolean waitForExit(Process process, Duration timeLimit) throws InterruptedException {
        if (!process.waitFor(timeLimit.getSeconds(), TimeUnit.SECONDS)) {
            log.info("Forced process termination");
            process.destroyForcibly();
            return true;
        }
        else return false;
    }

    private static String getAllProcessErrors(Process process) throws IOException {
        log.info("Getting process error data");
        BufferedReader errorsBuf = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errors = errorsBuf.readLine();
        String line;
        while ((line = errorsBuf.readLine()) != null) {
            errors += "\n" + line;
        }
        return errors;
    }

    private static String getAllProcessOutputs(Process process) throws IOException {
        log.info("Getting data from the standard output of a process");
        BufferedReader outputsBuf = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String outputs = outputsBuf.readLine();
        String line;
        while ((line = outputsBuf.readLine()) != null) {
            outputs += "\n" + line;
        }
        return outputs;
    }

    private static void writeInput(Process process, String inputData) throws IOException {
        log.info("Transferring data to standard process input");
        Writer w = new OutputStreamWriter(process.getOutputStream(), "UTF-8");
        w.write(inputData);
        w.close();
    }
}
