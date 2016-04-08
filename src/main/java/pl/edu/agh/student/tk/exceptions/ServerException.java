package pl.edu.agh.student.tk.exceptions;

public class ServerException extends RuntimeException {
    public ServerException(Exception e) {
        super(e);
    }
}
