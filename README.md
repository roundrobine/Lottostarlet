# Lottostarlet
Rock Paper Scissors multiplayer game in Spring Boot. Two or more players can play the game, default is three players. On startup 100 rounds of the game are played.

To change the number of players alter only the constant NUMBER_OF_PLAYERS placed into BackendService class. No other code changes are required.   


## Run Instructions

Import the project as Maven project according to the specifications from the Intellij on the following [link](https://www.jetbrains.com/help/idea/maven.html).
Choose **lottostarlet-backendtest** as a working directory.

Once the project is imported, navigate to **lottobackendtest** and execute **mvn spring-boot:run**

## REST API
Play new 100 rounds: `POST http://localhost:8080/api/result` 

All game results API endpoint: `GET http://localhost:8080/api/result`

Specific game result: `GET http://localhost:8080/api/result/{:gameResultId}`

## Testing

Unit and integration tests are created for this project. 
The overall test coverage is:
* Classes - 100%
* Methods - 89%
* Lines - 92%