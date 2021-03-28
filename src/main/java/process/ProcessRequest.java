package process;

import java.time.Duration;

public class ProcessRequest {
    private String request;
    private String inputData;
    private Duration timeLimit = Duration.ofSeconds(3);

    public ProcessRequest(String request) {
        this.request = request;
    }

    public ProcessRequest(String request, String inputData) {
        this.request = request;
        this.inputData = inputData;
    }

    public String getRequest() {
        return request;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public String getInputData() {
        return inputData;
    }
}
