package davenkin.ollama.controller;

import java.util.List;
import java.util.Map;

import davenkin.ollama.service.ChatService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

  private final ChatService chatService;
  private final VectorStore vectorStore;

  public ChatController(ChatService chatService, VectorStore vectorStore) {
    this.chatService = chatService;
    this.vectorStore = vectorStore;
  }

  @GetMapping(value = "/chat")
  public Map<String, String> chat(@RequestParam(name = "question") String question) {
    return Map.of("answer", this.chatService.answerFor(question));
  }

  @GetMapping(value = "/upload")
  public Map<String, String> upload(@RequestParam(name = "data") String data) {
    this.vectorStore.add(List.of(new Document(data)));
    return Map.of("result", "Upload success!");
  }
}
