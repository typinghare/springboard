package us.jameschan.springboard.core;

public class Authentication {
    private boolean authenticated = false;

    private Long userId;

    public void signIn(Long userId) {
        this.userId = userId;
        this.authenticated = true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Long getUserId() {
        return userId;
    }
}
