package davenkin.ollama.controller;

import java.util.Map;

import davenkin.ollama.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {this.chatService = chatService;}

  @GetMapping
  public Map<String, String> chat(@RequestParam(name = "question") String question) {
    return Map.of("result", this.chatService.answerFor(question));
  }
}
