*Paula Soriano Sánchez (843710)*
# Lab 1 Git Race -- Project Report

## 1. Description of Changes
The several changes which have been made in this proyect are described in this section.
### 1.1. Multi-Language Support
This functionality allows users to select their preferred language (English, Spanish or French) using **internationalization (i18n)**.  

To implement this feature, a file named `WebMessagesConfiguration.kt` has been created, which customizes the Spring MVC configuration to support multiple languages. The configuration includes:

- A **LocaleResolver** to determine the default locale for the session. In this project, English is the default locale.
- A **LocaleChangeInterceptor**, which allows to change the current locale by passing a parameter named `lang` in the request.
- Registration of the interceptor with the Spring MVC registry.

This configuration allows the web application to dynamically switch languages by appending`?lang=es`,`?lang=en`, or `?lang=fr` to the URL.

The text translated into the different languages is find in its corresponding file: `message.properties` for the default language (english), `message_en.properties` for english, `message_es.properties` for spanish and `message_fr.properties` for french.

The file `welcome.html` has been modified in order to use Thymeleaf message expressions (for example, `th:text="#{app.title}"`) to display localized text according to the selected language.

The language selection links are displayed in the navigation bar, allowing users to seamlessly switch between supported languages.

All labels, messages, and buttons in the templates are mapped to properties in each corresponding file for each language.

### 1.2. User Authentication
Using Spring Boot’s documentation as a reference, a basic user authentication has been implemented in this project. Authentication is based on a username and password, where the username corresponds to the user’s email.  

To set up authentication, a file named `SecurityConfiguration.kt` has been created. Currently, there is only one registered user with the following credentials:

- **Username:** paula@gmail.com  
- **Password:** pauli  

A login screen has been implemented using **Bootstrap** to allow users to enter their email and password. The screen is accessible via a controller that handles a GET request to `/login` and returns the login page.

The authentication system has been configured to:

- Permit access to public resources such as `/`, `/login`, `/api/hello`, `/actuator/health`, CSS, JS, images, and webjars.
- Require authentication for any other request.
- Provide form login with custom parameters (`email` and `password`).
- Redirect successful logins to the home page (`/`) and failed logins to `/login?error=true`.
- Support logout, redirecting users to the main page after signing out.

### 1.3. Login Integration Tests
To verify that the User Authentication mechanism works correctly in the application, two integration tests have been created. These tests are located in the file named `LoginIntegrationTest.kt`.

The first test uses a registered user account (see credentials in section *1.2, User Authentication*) to ensure that a valid login is successful and that the user is redirected to the home page (`/`).

The second test uses a user account that is not registered in the system, with the following credentials:

- **Username:** pepe@gmail.com
- **Password:** contrasegna

This test ensures that invalid login attempts fail gracefully, and the user is redirected back to the login page with an appropriate error message (`/login?error=true`).

Both tests have been executed and confirmed to work as expected, verifying that the login mechanism correctly differentiates between valid and invalid users, handles authentication properly, and triggers the appropriate redirects.

These integration tests use Spring Boot’s MockMvc framework to simulate HTTP requests in a fully controlled test environment. By performing form login requests, the tests validate both the authentication process and the resulting HTTP responses.

## 2. Technical Decisions
In this section are included several technical decisions that have been made.
- **Internationalization (i18n) Implementation**
It has been used `LocaleChangeInterceptor` and `SessionLocaleResolver` to manage language selection.
This approach keeps the code modular and makes it easy to add new languages in the future.
As well as the decision to place message files under `resources/messages/`, which allows a clear separation of concerns

- **Spring Security for Authentication**
It has been chosen for its robust, well-documented, and secure authentication mechanism.
The form login has been configured in order to use custom parameters (email instead of the default username).

- **MockMvc for Integration Testing**
This type of testing has been chosen because it ensures that HTTP requests, redirects, and responses are validated correctly.

## 3. Learning Outcomes
This project has provided me with several key learning experiences:

- **Spring Boot Configuration and Security**
I learned to configure Spring Security for authentication, including CSRF protection.

- **Internationalization (i18n)**
I gained experience in setting up multi-language support with `LocaleResolver`, `LocaleChangeInterceptor`, and message bundles. I also learned how Thymeleaf integrates with Spring messages to render dynamic content in different languages.

- **Integration Testing**
I learned to write integration tests using MockMvc to simulate login attempts and validate HTTP responses.

- **Debugging and Problem Solving**
I developed skills in reading Spring Security logs, understanding them and looking for solutions.

## 4. AI Disclosure
### 4.1. AI Tools Used
The only AI tool used in this proyect has been ChatGPT, which was leveraged to assist with documentation, explanations, and debugging guidance.

### 4.2. AI-Assisted Work
This section describes the specific parts of the project where AI assistance was used.
#### 4.2.1. Documentation
ChatGPT was used to rephrase and refine documentation to make it more clear, structured, and professional. The AI helped ensure that descriptions of functionalities, configurations, and test cases are comprehensive and easily understandable by external reviewers.
#### 4.2.2. Bug Fixes and Guidance
In addition to the previous point, ChatGPT has been used in order to provide help with several bugs that have appeared during development.

- **Multi-Language Support not working**
The problem was that the files `messages.properties`, `messages_en.properties`, `messages_es.properties` and `messages_fr.properties` were not being read. The solution was to include in the `application.properties` file the following line: `spring.messages.basename=messages/messages`. The reason is that Spring Boot looks for `messages.properties` files in `src/main/resources/` by default. As the files were in a subfolder (`src/main/resources/messages/`), it had to be set the basename in `application.properties`.

- **Login not working**
The problem was that Spring Security expected a `username` field by default, but in the proyect it was used the `email`. To fix this, it has been configured `.usernameParameter("email")` in formLogin, making sure the login form input had `name="email"`.

- **Login CSRF issue**
The problem was that the login did not work.
The solution was to include the CSRF token in the form using: `<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />`, which Spring Security requires by default for POST requests.

- **Logout implementation**
The problem was that the proyect was using a button for the logout functionality and `location.href='/logout'` to redirect the user when a person clicked on it. This caused issues because Spring Security requires POST for logout.
The solution was to replace it with a `<form method="post" action="/logout">` with the button inside. The reason is that Spring Security enforces POST logout to prevent CSRF attacks.

- **Tests failing due to escaped characters**
The problem was that the tests called `should display client-side HTTP debug interface` and `should return home page with modern title and client-side HTTP debug` were checking for the raw string *"Interactive HTTP Testing & Debug"*, but Thymeleaf escapes special characters when rendering HTML. As a result, the page source contained *"Interactive HTTP Testing `&amp;` Debug"*, causing the tests to fail.
The solution was to update the tests to expect the escaped string `&amp;`.

- **Tests failing due to login**
The problem was that, once the authentication functionality worked, the tests included in the file `HelloControllerMVCTests.kt` started failing.
The solution was to add the following line above each test in order to simulate in each of them an authenticated user: `@WithMockUser(username = "paula@gmail.com", roles = ["USER"])`.
#### 4.2.3. Percentage of AI-assisted vs. original work
Approximately 20–25% of the documentation and debugging guidance was assisted by ChatGPT.
The majority of the implementation, testing, and original code logic was developed independently.
#### 4.2.4. Modifications made to AI-generated code
AI suggestions have been adapted to match project-specific configurations.
Moreover, AI-generated documentation was refined to align with report style and structure requirements, carefully reviewing every part to ensure it accurately reflects the original intentions of the developer and says exactly what she decided to convey from the beginning.
### 4.3. Original Work
All core project functionalities have been developed by the developer: multi-language support, user authentication, login/logout, and integration tests.
She has also verified and tested all features to ensure that the application behaves as expected in both success and failure scenarios.
Moreover, she has debugged most issues manually, consulting AI for those which were difficult to understand (see them in the section *4.2.2. Bug Fixes and Guidance*).
Finally, she has documented the project thoroughly, reviewing the AI suggestions and integrating them when necessary, always maintaining a clear understanding of the entire document.