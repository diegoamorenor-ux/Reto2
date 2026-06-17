# Documento de Evidencias - Reto 2

Este documento recopila el diseño de los escenarios BDD (Gherkin), las evidencias de ejecución locales de ambos frameworks de automatización y la evidencia de ejecución en Jenkins CI/CD.

---

## 1. Escenarios BDD (Gherkin)

A continuación se listan los 4 escenarios de prueba automatizados distribuidos en las dos herramientas:

### Serenity BDD (Java) - Ubicación: `serenity-bdd-java/src/test/resources/features/purchase_and_login.feature`
```gherkin
Feature: Purchase and Login Flows on SauceDemo

  Scenario: Successful purchase of products
    Given that the user is logged into the SauceDemo store
    When they add "Sauce Labs Backpack" and "Sauce Labs Bolt T-Shirt" to the cart
    And they complete the checkout process with details: "John", "Doe", "12345"
    Then they should see the confirmation message "Thank you for your order!"

  Scenario: Login attempt by a locked out user
    Given that the user is on the SauceDemo login page
    When they attempt to login with username "locked_out_user" and password "secret_sauce"
    Then they should see the error message "Epic sadface: Sorry, this user has been locked out."
```

### Playwright (Python) - Ubicación: `playwright-python/features/sorting_and_prices.feature`
```gherkin
Feature: Product Sorting and Price Consistency on SauceDemo

  Scenario: Sort products by price from low to high
    Given the user is logged into the SauceDemo catalog page
    When they sort the products by "Price (low to high)"
    Then the products should be ordered correctly from the lowest price to the highest price

  Scenario: Validate product price consistency in the shopping cart
    Given the user is logged into the SauceDemo catalog page
    When they add the product "Sauce Labs Fleece Jacket" to the cart
    And they navigate to the shopping cart
    Then the price of the product in the cart should match the catalog price of "$49.99"
```

---

## 2. Evidencias de Ejecución Local

### A. Serenity BDD (Java)

**Comando de ejecución:**
```bash
mvn clean verify
```

**Resultado:**
- Serenity genera reportes HTML automatizados detallando cada paso del patrón Screenplay.
- Reporte principal ubicado en: `serenity-bdd-java/target/site/serenity/index.html`.

*(Espacio reservado para screenshots del reporte HTML de Serenity en tu máquina)*
> **Instrucciones:** Abre el archivo `index.html` en un navegador web, toma una captura del Dashboard de resultados y colócala aquí.
> Ejemplo de ruta de imagen: `![Reporte Serenity Local](evidencias/serenity_dashboard.png)`

---

### B. Playwright (Python)

**Comando de ejecución:**
```bash
behave
```

**Resultado:**
- Behave muestra en consola el éxito de la ejecución con salida detallada paso a paso.
- En caso de fallas, se genera un screenshot automático bajo la carpeta: `playwright-python/reports/screenshots/`.

*(Espacio reservado para screenshots de consola o de fallo en Playwright)*
> **Instrucciones:** Toma una captura de pantalla del resultado de la consola al ejecutar `behave` y colócala en este apartado.
> Ejemplo de ruta de imagen: `![Ejecución Behave Playwright](evidencias/behave_console.png)`

---

## 3. Evidencias de Ejecución en Jenkins CI/CD

El pipeline ejecuta secuencialmente ambas herramientas de prueba dentro de un entorno Dockerizado o local controlado por Jenkins.

### A. Flujo de Ejecución del Pipeline (Stage View)
*(Espacio reservado para la captura del Pipeline Stage View en Jenkins)*
> **Instrucciones:** Toma una captura de pantalla de la interfaz de Jenkins mostrando el éxito de las etapas: "Stage 1: Ejecución Serenity" y "Stage 2: Ejecución Playwright".

### B. Consola de Logs de Jenkins (Console Output)
*(Espacio reservado para captura o logs del Console Output de Jenkins)*
> **Instrucciones:** Agrega extractos de la salida de consola de Jenkins que demuestre la ejecución correcta de las pruebas.

### C. Artefactos del Build
*(Espacio reservado para captura de pantalla de los artefactos archivados)*
> **Instrucciones:** Muestra los archivos guardados en Jenkins (ej. reporte HTML y capturas de pantalla de Playwright).
