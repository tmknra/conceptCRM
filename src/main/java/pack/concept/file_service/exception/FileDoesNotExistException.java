package pack.concept.file_service.exception;

public class FileDoesNotExistException extends Throwable{
    public FileDoesNotExistException(String fileName) {
        super("File name invalid or no such file exists with name: " + fileName);
    }
}
