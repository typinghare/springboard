package us.jameschan.springboard.core.performer;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import us.jameschan.springboard.common.App;
import us.jameschan.springboard.core.Action;
import us.jameschan.springboard.core.Client;
import us.jameschan.springboard.core.ControlCenter;
import us.jameschan.springboard.core.SessionManager;

public abstract class ActionPerformer {
    protected ControlCenter controlCenter;

    protected SessionManager sessionManager;

    public boolean pretest(Action action) {
        return true;
    }

    public abstract void perform(Client client, Action action);

    @PostConstruct
    public void initialize() {
        final ApplicationContext applicationContext = App.getApplicationContext();
        this.controlCenter = applicationContext.getBean(ControlCenter.class);
        this.sessionManager = applicationContext.getBean(SessionManager.class);
    }
}
