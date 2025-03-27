# Mini Casino Backend

A simplified casino backend implemented in Java with Spring Boot.

## 🚀 How to Run

1. Make sure you have **Java 17+** and **Maven** installed.

2. Clone the project and navigate to the root folder:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Application will start at:
   ```
   http://localhost:8080
   ```

4. Visit Swagger UI for live API docs:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 🔗 REST API Endpoints

### 🎮 Games
- `GET /games`
    - List all games

- `POST /games/upload`
    - Upload an XML file to add games (key: `file`, type: file)
    - Example XML format:
```xml
<games>
    <game>
        <id>game3</id>
        <name>Treasure Wheel</name>
        <chanceOfWinning>0.5</chanceOfWinning>
        <winningMultiplier>2.0</winningMultiplier>
        <maxBet>150</maxBet>
        <minBet>10</minBet>
    </game>
</games>
```

### 👤 Players
- `POST /players/register`
    - JSON body:
```json
{
  "name": "Alice",
  "username": "alice123",
  "birthdate": "2000-01-01"
}
```

- `GET /players/balance/{username}`
    - Check player balance

- `POST /players/deposit/{username}?amount=50`
    - Deposit funds

### 🃏 Bets
- `POST /bets/place?username=alice123&gameId=game1&amount=10`
    - Place a bet

- `GET /bets/summary/{username}`
    - Returns JSON summary:
```json
{
  "numberOfBets": 2,
  "totalBetValue": 30.0,
  "totalWinnings": 45.0
}
```

---

## 🧪 Running Tests

Run all tests with:
```bash
mvn test
```

### Included Test Coverage
- `GameControllerTest`: game listing
- `PlayerControllerTest`: registration, balance, deposit
- `BetControllerTest`: bet placement, summary

---

## 📁 Persistence
- All data is stored in-memory (no database)
- Data is lost when the app stops

## 📝 Notes
- Clean, testable Spring Boot design
- Modular services and controllers
- Ready to extend with persistence, security, etc.
