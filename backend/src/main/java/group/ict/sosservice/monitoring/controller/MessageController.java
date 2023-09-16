package group.ict.sosservice.monitoring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import group.ict.sosservice.monitoring.model.ChatMessage;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/monitoring/message")
    public void enter(final ChatMessage message) {
        sendingOperations.convertAndSend(
            "/sub/monitoring/room/" + message.getRoomId(),
            message
        );
    }
}
