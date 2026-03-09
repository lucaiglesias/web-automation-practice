# Web Automation Practice - Heroku Login
This project demonstrates a professional automated testing suite for a web login flow, focusing on code reusability, clean architecture, and automated assertions.

## Technologies Used
* **Java** (JDK 17+)
* **Selenium WebDriver** (Browser automation)
* **JUnit 5** (Test framework and Lifecycle hooks)
* **Maven** (Dependency management)

## Project Features & Technical Highlights
* **Automated E2E Flow**: Covers both positive (successful login) and negative (invalid credentials) scenarios.
* **Test Lifecycle Management**: Uses @BeforeEach and @AfterEach hooks to manage browser sessions efficiently, ensuring test independence.
* **Refactored for Reusability**: Implemented Helper Methods (enterUser, clickLoginButton, etc.) to reduce code duplication and follow DRY (Don't Repeat Yourself) principles.
* **Dynamic Assertions**: Automated validation of success and error messages using JUnit Assertions.


# Version Française
Ce projet présente une suite de tests automatisés professionnels pour un flux de connexion web, en mettant l'accent sur la **réutilisabilité du code**, l'**architecture propre** et les **assertions automatisées**.

## Technologies Utilisées
* **Java** (JDK 17+)
* **Selenium WebDriver** (Automation du navigateur)
* **JUnit 5** (Cadre de test et hooks de cycle de vie)
* **Maven** (Gestion das dépendances)

## Points Saillants Techniques
* **Flux E2E Automatisé :** Couvre les scénarios positifs (connexion réussie) et négatifs (identifiants invalides).
* **Gestion du Cycle de Vie :** Utilisation des annotations `@BeforeEach` et `@AfterEach` pour gérer les sessions de navigation de manière isolée.
* **Refactorisation pour la Réutilisabilité :** Implémentation de méthodes auxiliaires (`enterUser`, `clickLoginButton`, etc.) suivant les principes **DRY (Don't Repeat Yourself)**.
