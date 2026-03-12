# Web Automation Practice
This project demonstrates a professional automated testing suite for a web login flow, focusing on **code reusability**, **clean architecture**, and **automated assertions**.

## Technologies Used
* **Java** (JDK 17+)
* **Selenium WebDriver** (Browser automation)
* **JUnit 5** (Test framework and Lifecycle hooks)
* **Maven** (Dependency management)
* **RestAssured** (Test and validation)

## Project 1: Web Login (Heroku Login) - Features & Technical Highlights
* **Automated E2E Flow**: Covers both positive (successful login) and negative (invalid credentials) scenarios.
* **Test Lifecycle Management**: Uses `@BeforeEach` and `@AfterEach` hooks to manage browser sessions efficiently, ensuring test independence.
* **Refactored for Reusability**: Implemented Helper Methods (`enterUser`, `clickLoginButton`, etc.) to reduce code duplication and follow **DRY (Don't Repeat Yourself)** principles.
* **Dynamic Assertions**: Automated validation of success and error messages using JUnit Assertions.

## Project 2: E-Commerce End-to-End Flow (SauceDemo)
* **Full Checkout Cycle**: Automated the entire journey from login to "Thank You" confirmation page.
* **Dynamic Data Handling**: Implemented List<WebElement> and for-each loops to interact with a dynamic product catalog, ensuring tests remain robust even if product order changes.
* **Advanced Browser Configuration**: Utilized ChromeOptions and HashMap to disable browser-level alerts (leak detection, password saving) that can obstruct automation.
* **Defensive Programming**: Added logic to verify cart contents and presence of elements before interaction to prevent "flaky" tests.

## Project 3: API Test (JSONPlaceholder)
* **API Automation**: Implemented CRUD testing using **RestAssured**.
* **Data Serialization**: Integrated **Jackson Databind** to automatically convert Java Maps into JSON payloads.
* **Validation**: Applied BDD (Given/When/Then) structure to validate HTTP status codes and response bodies.

## Project 4: Complex Elements (Heroku Dropdown menus; JavaScript Alerts; iFrames)
* **Complex Elements**: Mastery of non-standard web components like **iFrames** (nested navigation), **JavaScript Alerts** (system pop-ups), and **Dropdowns** using the Selenium `Select` class.
* **Context Switching**: Demonstrated ability to manage WebDriver focus between different DOM layers and system alerts.


# Version Française
Ce projet présente une suite de tests automatisés professionnels pour un flux de connexion web, en mettant l'accent sur la **réutilisabilité du code**, l'**architecture propre** et les **assertions automatisées**.

## Technologies Utilisées
* **Java** (JDK 17+)
* **Selenium WebDriver** (Automation du navigateur)
* **JUnit 5** (Cadre de test et hooks de cycle de vie)
* **Maven** (Gestion das dépendances)
* **RestAssured** (Test et validation)

## Projet 1 : Connexion Web (Heroku Login) - Points Saillants Techniques
* **Flux E2E Automatisé :** Couvre les scénarios positifs (connexion réussie) et négatifs (identifiants invalides).
* **Gestion du Cycle de Vie :** Utilisation des annotations `@BeforeEach` et `@AfterEach` pour gérer les sessions de navigation de manière isolée.
* **Refactorisation pour la Réutilisabilité :** Implémentation de méthodes auxiliaires (`enterUser`, `clickLoginButton`, etc.) suivant les principes **DRY (Don't Repeat Yourself)**.

## Projet 2 : Flux E2E de Commerce Électronique (SauceDemo)
* **Cycle d'achat complet** : Automatisation du parcours complet, de la connexion à la page de confirmation de commande.
* **Gestion dynamique des données** : Utilisation de List<WebElement> et de boucles for-each pour interagir avec un catalogue de produits dynamique.
* **Configuration avancée du navigateur** : Utilisation de ChromeOptions pour désactiver les alertes de sécurité du navigateur qui pourraient bloquer l'automatisation.
* **Assertions robustes** : Validation de la correspondance entre les articles sélectionnés et les articles présents dans le panier.

## Projet 3: Test API (JSONPlaceholder)
* **Automatisation d'API** : Mise en œuvre de tests CRUD avec **RestAssured**.
* **Sérialisation** : Utilisation de **Jackson** pour la conversion automatique des données Java en JSON.
* **Validation**: Structure BDD pour valider les codes d'état et le contenu des réponses.

## Projet 4: Éléments Complexes (Heroku Dropdown menus; JavaScript Alerts; iFrames)
* **Éléments Complexes** : Maîtrise des composants web avancés tels que les **iFrames** (navigation imbriquée), les alertes **JavaScript** et les menus **Dropdown**.
* **Changement de Contexte** : Capacité démontrée à gérer le focus du WebDriver entre les différentes couches du DOM.
