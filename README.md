# Conversor de Monedas CLI

Una aplicación robusta de línea de comandos (CLI) en Java que proporciona conversión de monedas en tiempo real utilizando la **ExchangeRate-API**. Construida como parte de un desafío de backend en Java.

## Características Principales

- **Datos en Tiempo Real:** Obtiene tasas de cambio en vivo para más de 160 monedas.
- **Manejo Seguro de Entrada:** Implementa lógica de validación para evitar que la aplicación falle con entradas inválidas del usuario.
- **Configuración Segura:** Utiliza archivos `.env` y la librería `dotenv-java` para mantener las claves de API privadas.
- **Interfaz Limpia:** Menús de consola claros.
- **Pruebas:** Incluye pruebas unitarias con JUnit 5 y Mockito.

## Stack

- **Lenguaje:** Java 21+
- **Herramienta de Build:** Maven 3.x
- **Librerías:**
  - `Gson` 2.13.1: Para parsing y serialización de JSON.
  - `Dotenv-java` 5.2.2: Para gestionar variables de entorno.
  - `JUnit 5` 11.0: Para pruebas unitarias.
  - `Mockito` 5.21.0: Para mocking en pruebas.
  - `OkHttp MockWebServer` 5.3.2: Para pruebas HTTP.

## Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener:

1. **Java JDK 21** o superior instalado.
2. **Maven** instalado.
3. Una API Key de [ExchangeRate-API](https://www.exchangerate-api.com/).

## ⚙️ Instalación y Configuración

### 1. **Clonar el repositorio:**

```bash
git clone https://github.com/KDBatistaFiguereo/conversor-monedas.git
cd conversor-monedas
```

### 2. **Configurar Variables de Entorno:**

Crea un archivo `.env` en `src/main/resources/` y añade tu API Key:

```env
LLAVE_API=tu_api_key_aqui
URL_BASE=https://v6.exchangerate-api.com/v6/
```

> **Nota:** Puedes usar el archivo `.env.ejemplo` como plantilla y renombrarlo a `.env`.

### 3. **Compilar el proyecto:**

```bash
mvn clean install
```

## Uso

Para ejecutar la aplicación, simplemente usa el comando Maven exec:

```bash
mvn exec:java
```

### Ejemplo de Flujo de Uso

1. Selecciona la **Opción 3** del menú para convertir monedas.
2. Ingresa la cantidad (ej: `100`).
3. Ingresa la moneda de origen (ej: `USD`).
4. Ingresa la moneda de destino (ej: `EUR`).
5. Visualiza el resultado y presiona **ENTER** para volver al menú.

#### Menú Principal

```
Programa de conversion de monedas. Elija una opcion:
  1 - Mostrar codigo monedas sugeridas.
  2 - Mostrar todos los codigos de monedas.
  3 - Convertir una cantidad de una moneda a otra(necesita codigo)
  4 - salir
```

#### Monedas Sugeridas (Opción 1)

```
ARS - Peso argentino
BOB - Boliviano boliviano
BRL - Real brasileño
CLP - Peso chileno
COP - Peso colombiano
USD - Dólar estadounidense
```

## Estructura del Proyecto

```
src/main/java/com/kdbf/app/
├── App.java                    # Punto de entrada y lógica del menú principal
├── config/
│   ├── ApiConfig.java          # Record de configuración de API
│   └── CargarConfiguracion.java # Cargador de configuración desde .env
├── modelo/
│   ├── Moneda.java             # Modelo de datos de moneda
│   └── dto/
│       └── MonedaDto.java      # DTO para respuestas de API
├── mapper/
│   └── MonedaMapper.java       # Mapeo entre DTO y modelo
├── servicio/
│   ├── ConversorServicio.java  # Lógica de conversión y peticiones API
│   ├── helper/
│   │   └── CrearUrl.java       # Helper para construir URLs
│   └── fabrica/
│       └── Peticiones.java     # Fábrica de peticiones HTTP
```

## Pruebas

El proyecto incluye pruebas unitarias:

### **Ejecutar todas las pruebas:**

```bash
mvn test
```

### **Estructura de Pruebas:**

```
src/test/java/com/kdbf/app/
├── AppTest.java
├── servicio/
│   ├── ConvertirCantidadTest.java      # Pruebas de conversión
│   ├── PedirDatosMonedaTest.java       # Pruebas de obtención de datos
│   ├── mostrarMonedasTest.java         # Pruebas de visualización
│   └── helper/
│       └── generarUrlTest.java         # Pruebas de construcción de URLs
└── mapper/
    └── DesdeDtoTest.java               # Pruebas de mapeo DTO
```

Las pruebas cubren:

- Conversión de cantidades entre monedas
- Validación de códigos de moneda
- Construcción de URLs de API
- Mapeo de respuestas JSON
- Manejo de errores HTTP

## 📄 Licencia

Este proyecto es para fines educativos y de demostración técnica.

