package process;

public class ProcessResult {
    private String errors;
    private String outputs;
    private boolean timeIsOver;

    public ProcessResult(String outputs, String errors, boolean timeIsOver) {
        this.outputs = outputs;
        this.errors = errors;
        this.timeIsOver = timeIsOver;
    }

    public boolean getTimeIsOver() {
        return timeIsOver;
    }

    public String getErrors() {
        return errors;
    }

    public String getOutputs() {
        return outputs;
    }
}
