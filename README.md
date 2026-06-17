# Reto 2: Estrategia de Automatización End-to-End (E2E)

Este repositorio contiene la solución al Reto 2, que consiste en la implementación de una estrategia de automatización End-to-End (E2E) sobre la aplicación de práctica [SauceDemo](https://www.saucedemo.com/) utilizando dos herramientas y lenguajes diferentes:
1. **Serenity BDD (Java)** con el patrón **Screenplay**.
2. **Playwright (Python)** con el framework de BDD **Behave** y una estructura adaptada de **Screenplay**.

Ambos proyectos están integrados en un pipeline de integración continua (CI/CD) configurado mediante un archivo `Jenkinsfile`.

---

## Estructura del Proyecto

```text
Reto2/
├── Jenkinsfile                  # Archivo de configuración del pipeline de Jenkins
├── README.md                    # Este archivo con instrucciones de ejecución
├── Documento_de_Evidencias.md   # Documento detallado de evidencias y escenarios BDD
├── serenity-bdd-java/           # Proyecto Serenity BDD (Java)
│   ├── pom.xml                  # Configuración de Maven
│   ├── serenity.conf            # Configuración de Serenity y Webdriver
│   └── src/
│       └── test/
│           ├── java/com/saucedemo/
│           │   ├── CucumberTestSuite.java   # Ejecutor JUnit 5 de Cucumber
│           │   ├── steps/                   # Step definitions de Gherkin
│           │   ├── tasks/                   # Tareas de Screenplay (Login, AddProducts, Checkout)
│           │   ├── ui/                      # Mapeo de localizadores (LoginPage, etc.)
│           │   └── questions/               # Preguntas de validación (TheErrorMessage, etc.)
│           └── resources/features/
│               └── purchase_and_login.feature  # Escenarios en BDD
└── playwright-python/           # Proyecto Playwright (Python)
    ├── requirements.txt         # Dependencias de Python
    ├── behave.ini               # Configuración de Behave
    ├── features/
    │   ├── environment.py       # Configuración del navegador y capturas en fallo
    │   ├── steps/               # Step definitions de Gherkin
    │   └── sorting_and_prices.feature # Escenarios en BDD
    └── screenplay/              # Adaptación de Screenplay en Python
        ├── actor.py             # Clase Actor para manejar el contexto del browser
        ├── tasks.py             # Acciones reutilizables (Login, Sort, AddProduct, etc.)
        ├── ui.py                # Localizadores robustos basados en data-test
        └── questions.py         # Preguntas para extraer datos y hacer aserciones
```

---

## Requisitos del Entorno

Asegúrate de contar con lo siguiente instalado localmente:
- **Java JDK 17 o superior** (Java 17/21 recomendado).
- **Apache Maven 3.8+**.
- **Python 3.8 o superior** (junto con `pip3`).
- **Navegador Google Chrome** instalado en el sistema (Serenity se configurará por defecto para usar Chrome local).

---

## 1. Proyecto Serenity BDD (Java)

Este framework utiliza Cucumber para BDD, JUnit 5 como motor de ejecución y el patrón Screenplay para mantener las interacciones desacopladas, limpias y legibles.

### Instrucciones de Instalación y Ejecución

1. Navega al directorio del proyecto:
   ```bash
   cd serenity-bdd-java
   ```
2. Ejecuta las pruebas mediante Maven:
   ```bash
   mvn clean verify
   ```
   *Nota: Este comando descarga las dependencias necesarias, inicializa WebDriver en modo headless por defecto y ejecuta los 2 escenarios del archivo `.feature`.*

### Ver Reportes de Ejecución
Al finalizar la ejecución, Serenity genera un completo reporte HTML interactivo. Puedes abrirlo desde tu navegador web preferido en la siguiente ruta:
```text
serenity-bdd-java/target/site/serenity/index.html
```

---

## 2. Proyecto Playwright (Python)

Este framework utiliza Behave para interpretar escenarios Gherkin y Playwright para interactuar de forma rápida y robusta con el navegador web, implementado bajo una estructura Screenplay (Actor, Tareas, Localizadores y Preguntas).

### Instrucciones de Instalación y Ejecución

1. Navega al directorio del proyecto:
   ```bash
   cd playwright-python
   ```
2. Crea un entorno virtual de Python (Recomendado):
   ```bash
   python3 -m venv venv
   source venv/bin/activate  # En macOS/Linux
   # En Windows usa: venv\Scripts\activate
   ```
3. Instala las dependencias y los binarios del navegador de Playwright:
   ```bash
   pip install --upgrade pip
   pip install -r requirements.txt
   playwright install chromium
   ```
4. Ejecuta los escenarios BDD con Behave:
   ```bash
   behave
   ```

### Captura de Evidencias en Fallos
El archivo `features/environment.py` posee un hook `after_scenario` que detecta si alguna prueba falló y, en tal caso, almacena automáticamente una captura de pantalla en formato PNG dentro del directorio:
```text
playwright-python/reports/screenshots/
```

---

## 3. Integración Continua con Jenkins

El archivo `Jenkinsfile` en la raíz define una infraestructura de entrega continua con las siguientes etapas:
1. **Stage 0: Environment Diagnostic**: Imprime las versiones instaladas de las herramientas (Java, Maven, Python, Pip) para validación del entorno.
2. **Stage 1: Ejecución Serenity**: Entra a la carpeta del proyecto en Java y corre `mvn clean verify`, generando y archivando el reporte de Serenity completo.
3. **Stage 2: Ejecución Playwright**: Crea el entorno virtual de Python, instala Playwright con Chromium, ejecuta los casos de prueba e integra y guarda evidencias en caso de falla.

### Configuración sugerida en Jenkins
1. Crea un nuevo Job de tipo **Pipeline** en Jenkins.
2. En la sección **Pipeline**, selecciona **Pipeline script from SCM**.
3. Elige **Git**, ingresa la URL de este repositorio y define la rama (ej. `*/main`).
4. Indica que el archivo de script es `Jenkinsfile`.
5. Ejecuta el Job (**Build Now**).
   *Nota: Asegúrate de que el agente ejecutor de Jenkins (o contenedor Docker de Jenkins) tenga preinstalados Java, Maven y Python, o utiliza un Jenkins Agent basado en imágenes Docker.*
