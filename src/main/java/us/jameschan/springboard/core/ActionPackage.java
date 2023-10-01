package us.jameschan.springboard.core;

public class ActionPackage {
    private String id;

    private Action action;

    public ActionPackage() {
    }

    public ActionPackage(Action action) {
        this.id = "OkOK";
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public Action getAction() {
        return action;
    }
}
