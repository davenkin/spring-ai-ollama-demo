package davenkin.ollama.utils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RagPdfInitializer {
  private final VectorStore vectorStore;
  private final Resource pdfResource;

  public RagPdfInitializer(VectorStore vectorStore, @Value("classpath:/Intel vPro Guide.pdf") Resource pdfResource) {
    this.vectorStore = vectorStore;
    this.pdfResource = pdfResource;
  }

  @PostConstruct
  public void init() {
    PagePdfDocumentReader documentReader = new PagePdfDocumentReader(pdfResource);
    TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
    vectorStore.add(tokenTextSplitter.apply(documentReader.get()));
    log.info("PDF data initialized.");
  }
}
