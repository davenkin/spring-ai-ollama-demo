package davenkin.ollama.utils;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class RagDataInitializer {

  private final VectorStore vectorStore;

  public RagDataInitializer(VectorStore vectorStore) {
    this.vectorStore = vectorStore;
  }

  @PostConstruct
  public void init() {
    Document document1 = new Document("Alex's brother's is named Tom.");
    Document document2 = new Document("Tom's age is 16.");
    this.vectorStore.add(List.of(document1, document2));
  }
}
