# TP2 - Metodología de Sistemas II

Repositorio grupal del Trabajo Práctico 2. El proyecto utiliza Ant/NetBeans, compila el código Java desde `src/main/java` y tiene `Main` como punto de entrada para las demostraciones.

## Estructura por actividad

```text
src/main/java/
├── Main.java                              # Integra y ejecuta las demos
├── patrones/
│   ├── strategy/                          # Actividad 1: Strategy
│   ├── adapter/                           # Actividad 2: Adapter
│   ├── template/                          # Actividad 3: Template Method
│   ├── observer/                          # Actividad 4: Observer
│   └── gestionheroes/                     # Actividad 5: Factory Method, Singleton y Facade
└── refactoring/                           # Refactoring: habilidades y videojuego
```

Al ejecutar `Main`, las demostraciones se presentan por separado en la consola. La actividad Observer se ejecuta mediante `PruebaObserver`; también se puede abrir su interfaz gráfica (`patrones.observer.InterfazMonitoreo`) por separado. Las pruebas Observer están en `patrones.observer.PruebasObserver`.

## Requisitos

- JDK 21 (la configuración del proyecto utiliza Java 21).
- Git.
- NetBeans IDE recomendado, ya que el repositorio contiene un proyecto Ant de NetBeans.

## Descargar y ejecutar en NetBeans

1. Instala Git, JDK 21 y NetBeans IDE.
2. Clona el repositorio desde una terminal:

   ```bash
   git clone https://github.com/agustin-prog/TP2_Grupo4_Metodologia2.git
   ```

   También puedes usar **Team > Git > Clone** en NetBeans e ingresar esa URL.
3. En NetBeans, selecciona **File > Open Project** y abre la carpeta `TP2_Grupo4_Metodologia2` que se descargó. Selecciona la carpeta raíz, donde están `build.xml` y `nbproject/`.
4. Si NetBeans solicita una plataforma Java, selecciona o configura JDK 21.
5. En el árbol del proyecto, abre **Source Packages > `<default package>` > `Main.java`**.
6. Haz clic derecho en el proyecto y selecciona **Run**, o ejecuta `Main.java`. El proyecto ya configura `Main` como clase principal.

## Ejecutar otras demos Observer

- **Interfaz gráfica:** ejecuta `patrones.observer.InterfazMonitoreo` como clase principal para abrir la ventana de monitoreo. Requiere un entorno gráfico.
- **Pruebas de Observer:** ejecuta `patrones.observer.PruebasObserver` como clase principal para correr sus pruebas automáticas en consola.

Las demos Observer indicadas son opcionales; el `Main` grupal ejecuta la prueba de consola `PruebaObserver` como parte del recorrido integrado.
