package fr.uphf.technoweb.chatoon;

import fr.uphf.technoweb.chatoon.chat.resource.ChatResource;
import fr.uphf.technoweb.chatoon.commentaire.resource.CommentaireResource;
import fr.uphf.technoweb.chatoon.personne.resource.PersonneResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api/v1")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(PersonneResource.class);
        register(ChatResource.class);
        register(CommentaireResource.class);
        register(CORSResponseFilter.class);

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
