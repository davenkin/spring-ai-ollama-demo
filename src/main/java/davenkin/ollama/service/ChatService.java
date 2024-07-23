package davenkin.ollama.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatClient chatClient;
  protected final VectorStore vectorStore;

  public ChatService(ChatModel chatModel, VectorStore vectorStore) {
    this.chatClient = ChatClient.create(chatModel);
    this.vectorStore = vectorStore;
  }

  public String answerFor(String question) {
    List<Document> similarDocuments = this.vectorStore.similaritySearch(question);

    String documentContents = similarDocuments.stream()
        .map(Document::getContent)
        .collect(Collectors.joining("\n"));
    SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(
        """
            You are a question/answer bot.
            You should provide answers based on the provided information in the DOCUMENTS section.
            
            DOCUMENTS:
            {documents}
            """
    );
    Message systemMessage = systemPromptTemplate.createMessage(Map.of("documents", documentContents));
    UserMessage userMessage = new UserMessage(question);
    Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

    return this.chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getContent();
  }
}
