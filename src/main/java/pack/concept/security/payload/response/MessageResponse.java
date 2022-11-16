package pack.concept.security.payload.response;

public class MessageResponse {
  private String type;
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }

  public MessageResponse(String type, String message) {
    this.type = type;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
