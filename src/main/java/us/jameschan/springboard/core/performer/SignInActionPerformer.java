package us.jameschan.springboard.core.performer;

import org.springframework.stereotype.Component;
import us.jameschan.springboard.core.Action;
import us.jameschan.springboard.core.Client;

import java.util.Collection;
import java.util.Objects;

@Component
public class SignInActionPerformer extends ActionPerformer {
    @Override
    public void perform(Client client, Action action) {
        final String username = action.getArgs().get("username");
        final String password = action.getArgs().get("password");

        // Set authentication
        final Long userId = 1L;
        client.getAuthentication().signIn(userId);

        // Make this client subscribe to clients having the same user id
        // Likewise, make clients having the same user id subscribe to this client
        for (final Client otherClient: sessionManager.getAllClient()) {
            if (!Objects.equals(otherClient.getAuthentication().getUserId(), userId)) {
                continue;
            }

            // Two-way subscription
            client.addSubscriber(otherClient);
            otherClient.addSubscriber(client);
        }

        controlCenter.forward(client, new Action("sign-in-success"));
    }
}
