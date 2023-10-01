package us.jameschan.springboard.core.performer;

import org.springframework.stereotype.Component;
import us.jameschan.springboard.core.Action;
import us.jameschan.springboard.core.Client;

import java.util.Set;

@Component
public class PublishActionPerformer extends ActionPerformer {
    @Override
    public void perform(Client client, Action action) {
        final Set<Client> subscriberSet = client.getSubscriberSet();
        final String message = action.getArg("message");

        // Create a forward action for displaying this message
        final Action forwardAction = new Action("display");
        forwardAction.setArg("message", message);

        // Send the forward action to all subscribers
        for (final Client subscriber : subscriberSet) {
            controlCenter.forward(subscriber, forwardAction);
        }
    }
}
