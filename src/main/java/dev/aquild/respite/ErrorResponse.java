package dev.aquild.respite;

public class ErrorResponse {
    public final String type;
    public final StackTraceElement[] trace;

    public ErrorResponse(Exception e) {
        this.type = e.toString();
        this.trace = e.getStackTrace();
    }
}
