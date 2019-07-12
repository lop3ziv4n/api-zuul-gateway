package ar.org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().authenticated()
                .simpDestMatchers("/topic/**").authenticated()
                .simpSubscribeDestMatchers("/topic/**").authenticated()
                .simpTypeMatchers(SimpMessageType.MESSAGE, SimpMessageType.SUBSCRIBE).denyAll()
                .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
