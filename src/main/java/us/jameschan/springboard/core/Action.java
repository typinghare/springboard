package us.jameschan.springboard.core;

import java.util.HashMap;
import java.util.Map;

public class Action {
    private final String name;

    private final Map<String, String> args = new HashMap<>();

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public String getArg(String key) {
        return args.get(key);
    }

    public void setArg(String key, String value) {
        args.put(key, value);
    }
}
