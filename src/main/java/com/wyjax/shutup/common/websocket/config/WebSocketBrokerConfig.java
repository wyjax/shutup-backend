package com.wyjax.shutup.common.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // STOMP에서 사용하는 메시지 브로커를 설정
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("shut-up")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic"); // 내장 메시지 브로커 사용, queue 1대1, topic 1대n
        registry.setApplicationDestinationPrefixes("/app");
    }
}
