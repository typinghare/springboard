package us.jameschan.springboard.core;

import com.google.gson.Gson;
import org.apache.commons.text.CaseUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import us.jameschan.springboard.common.App;
import us.jameschan.springboard.core.performer.ActionPerformer;

import java.io.IOException;

@Component
public class ControlCenter {
    private final Gson gson;

    public ControlCenter(Gson gson) {
        this.gson = gson;
    }

    /**
     * Sends a forward action to a specified client.
     * @param client the client to send to
     * @param action the forward action to be sent
     */
    public void forward(Client client, Action action) {
        final ActionPackage actionPackage = new ActionPackage(action);
        final String message = gson.toJson(actionPackage);

        try {
            client.getSession().sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Receives a backward action from a specified client.
     * @param client the client from whom this action received
     * @param action the action received
     */
    public void receive(Client client, Action action) {
        final String actionName = action.getName();
        final String camelActionName = CaseUtils.toCamelCase(actionName, false, '-');
        final String performerClassName = camelActionName + ActionPerformer.class.getSimpleName();

        final ApplicationContext applicationContext = App.getApplicationContext();
        if (!applicationContext.containsBean(performerClassName)) {
            throw new RuntimeException("No performer found: [ " + performerClassName + " ].");
        }

        final ActionPerformer actionPerformer = applicationContext.getBean(performerClassName, ActionPerformer.class);
        if (actionPerformer.pretest(action)) {
            actionPerformer.perform(client, action);
        }
    }

    public void receive(Client client, ActionPackage actionPackage) {
        receive(client, actionPackage.getAction());
    }
}
