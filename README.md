My attempt to implement a chatbot system using Java, Quarkus, and Gradle. The chatbot interacts with users in a conversational manner, processing their requests and providing responses based on document embeddings stored in a vector database.

Tech Stack Used - 
- Java
- Build tool - Gradle
- Quarkus framework
- Apache Lucene Database
- Various quarkus libraries for API creation and LangChain integration

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Gradle 7.0 or higher
- Quarkus 3.13.2
- IntelliJ IDEA (or any preferred IDE)

### Installation

- **Clone the Repository:**
- **Open IntelliJ and build the project**
  ```bash 
   ./gradlew build
- **Start the Quarkus application in development mode**
  ```bash
   ./gradlew quarkusDev

## API Documentation

### Start Chat 
-Endpoint: `POST /api/chat/start`
-Sample Request : 
```json
{
  "assetId": "67f8e3e3-a11e-4930-943e-f03ab696d82d"
}
```
- Sample Response :
```json
{
  "chatThreadId": "123456",
  "message": "How can I help you?"
}
```

## Send Message 
- Endpoint : `POST /api/chat/message`
- Sample Request:
  ```json
  {
  "chatThreadId": "123456",
  "userMessage": "User question"
  }
  ```
- Sample Response:
  ```json
  {
  "agentResponse": "the chatbot's response string"
  }
  ```


## Potential Improvements
- Adding authentication methods e.g JWT/OAuth to secure the API endpoints
- Adding Unit Tests to improve error handling and code failure
- Storing the chat history for user's comfort
- Dockerisation of all things for smooth development across teams
- Better implementation of services in terms of Scalability

## Problems Faced
- Python should have been way easier , but my fimilarity with Java pushed me to deploy the project in Java , the second half (LangChain based chatbot) is a difficult thing to do in Java
- In Java also SpringBoot is more common and developer friendly in terms of community support , it was difficult to look for Quarkus doubts on internet



