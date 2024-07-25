### Introduction
- Demo project using Spring AI and Ollama.

### How to run
- Install [Ollama](https://ollama.com/) and run `ollama run llama3`, make sure the Ollama server is running by visiting `http://localhost:11434/`
- Run `./mvnw spring-boot:run` or run `main()` in `SpringAiOllamaDemoApplication` in IDE directly
- Open `GET http://localhost:8080/hello-world` to verify if the application started normally
- Use `GET http://localhost:8080/chat/upload?data=xxxx` to upload context data
- Use `GET http://localhost:8080/chat/chat?question=xxx` to ask question

### Tech stack
- Spring Boot 3.3.2
- Ollama