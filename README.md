# Modern Web Application

A modern Spring Boot application built with Kotlin, featuring a responsive web interface and REST API endpoints.

## 🚀 Features

- **Modern Tech Stack**: Spring Boot 3.5.3, Kotlin 2.2.10, Java 21 LTS
- **Responsive UI**: Bootstrap 5.3.3 with modern design
- **REST API**: JSON endpoints with timestamp support
- **Health Monitoring**: Spring Boot Actuator for application health
- **Live Development**: Spring Boot DevTools for automatic reload
- **Interactive HTTP Debugging**: Client-side HTTP request/response visualization
- **Containerization**: Docker support with multi-stage builds
- **Comprehensive Testing**: Unit, integration, and MVC tests
- **Modern Kotlin**: Constructor injection, data classes, and modern syntax

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.5.3
- **Language**: Kotlin 2.2.10
- **Java Version**: 21 LTS
- **Frontend**: Bootstrap 5.3.3, Thymeleaf
- **Build Tool**: Gradle 9.0.0
- **Testing**: JUnit 5, AssertJ, MockMvc
- **Containerization**: Docker

## 📋 Prerequisites

- Java 21 or higher
- Gradle 9.0.0 or higher
- Docker (optional)

## 🏃‍♂️ Quick Start

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd template-lab1-git-race
   ```

2. **Build the application**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

4. **Access the application**
   - Web Interface: http://localhost:8080
   - API Endpoint: http://localhost:8080/api/hello
   - Health Check: http://localhost:8080/actuator/health

### Using Docker

1. **Build the Docker image**
   ```bash
   docker build -t modern-web-app .
   ```

2. **Run the container**
   ```bash
   docker run -p 8080:8080 modern-web-app
   ```

## 🧪 Testing

Run all tests:
```bash
./gradlew test
```

Run specific test classes:
```bash
./gradlew test --tests "HelloControllerUnitTests"
./gradlew test --tests "IntegrationTest"
```

## 📡 API Endpoints

### Web Endpoints
- `GET /` - Main web page with interactive HTTP debugging tools
- `GET /?name={name}` - Personalized greeting page

### REST API Endpoints
- `GET /api/hello` - Returns JSON greeting with timestamp
- `GET /api/hello?name={name}` - Returns personalized JSON greeting

### Monitoring Endpoints
- `GET /actuator/health` - Application health status
- `GET /actuator/info` - Application information
- `GET /actuator/metrics` - Application metrics

### Interactive HTTP Debugging
- **Web Page Testing**: Test the main page with personalized greetings
- **API Testing**: Test REST endpoints with real-time request/response display
- **Health Check Testing**: Monitor application health status
- **Live Reload**: Spring Boot DevTools automatically reloads on file changes

## 🏗️ Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   ├── controller/
│   │   │   └── HelloController.kt      # Web and API controllers
│   │   └── HelloWorld.kt               # Main application class
│   └── resources/
│       ├── application.properties      # Application configuration
│       ├── templates/
│       │   └── welcome.html           # Thymeleaf template
│       └── public/
│           └── assets/
│               └── logo.svg           # Application logo
└── test/
    └── kotlin/
        ├── controller/
        │   ├── HelloControllerUnitTests.kt    # Unit tests
        │   └── HelloControllerMVCTests.kt     # MVC tests
        └── IntegrationTest.kt                 # Integration tests
```

## ⚙️ Configuration

Key configuration options in `application.properties`:

```properties
# Application settings
spring.application.name=modern-web-app
server.port=8080

# Custom message
app.message=Welcome to the Modern Web App!

# Actuator endpoints
management.endpoints.web.exposure.include=health,info,metrics
```

## 🐳 Docker Details

The application includes a multi-stage Dockerfile optimized for production:

- **Builder stage**: Uses JDK 21 to compile the application
- **Runtime stage**: Uses JRE 21 for smaller image size
- **Security**: Runs as non-root user
- **Health checks**: Built-in health monitoring
- **Optimization**: Cached dependency layers for faster builds

## 🔧 Development

### Adding New Features

1. **Controllers**: Add new endpoints in the controller package
2. **Templates**: Add new Thymeleaf templates in `src/main/resources/templates/`
3. **Tests**: Add corresponding tests in the test package
4. **Configuration**: Update `application.properties` for new settings

### Code Style

- Use modern Kotlin features (constructor injection, data classes)
- Follow Spring Boot best practices
- Write comprehensive tests for all functionality
- Use descriptive test method names with backticks

## 📊 Monitoring

The application includes Spring Boot Actuator for monitoring:

- **Health**: Application and dependency health status
- **Info**: Application metadata and build information
- **Metrics**: JVM and application metrics

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆕 What's New in This Modern Version

- ✅ Upgraded to Java 21 LTS for better performance
- ✅ Modern Kotlin syntax with constructor injection
- ✅ Separated web and API controllers for better organization
- ✅ Added comprehensive test coverage
- ✅ Implemented Spring Boot Actuator for monitoring
- ✅ Created responsive Bootstrap 5.3.3 UI
- ✅ Added Docker support with multi-stage builds
- ✅ Fixed Bootstrap version inconsistencies
- ✅ Enhanced error handling and validation
- ✅ Added interactive features and API endpoints