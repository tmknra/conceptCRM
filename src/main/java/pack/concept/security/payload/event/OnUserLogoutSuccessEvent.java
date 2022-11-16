package pack.concept.security.payload.event;

import pack.concept.security.payload.request.LogOutRequest;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.Date;

public class OnUserLogoutSuccessEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private String userName;
    private final String token;
    // private final transient LogOutRequest logOutRequest;
    private final Date eventTime;

    public OnUserLogoutSuccessEvent(String userName, String token) {
        super(userName);
        this.userName = userName;
        this.token = token;
        // this.logOutRequest = logOutRequest;
        this.eventTime = Date.from(Instant.now());
    }

    public String getUserName() {
        return this.userName;
    }

    public String getToken() {
        return this.token;
    }

    // public LogOutRequest getLogOutRequest() {
    //     return this.logOutRequest;
    // }

    public Date getEventTime() {
        return this.eventTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
