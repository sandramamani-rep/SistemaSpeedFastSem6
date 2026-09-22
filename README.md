![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🚚 SpeedFast – Gestión de pedidos y entregas

## 👤 Autor del proyecto

- **Nombre completo:** Sandra Mamani Mamani
- **Carrera:** Analista Programador Computacional
- **Sede:** Online

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la actividad de la semana 6 de la asignatura *Desarrollo Orientado a Objetos II*. Consiste en una aplicación de escritorio desarrollada en Java para apoyar la gestión de pedidos y entregas de la empresa SpeedFast.

El sistema permite registrar pedidos de comida, encomienda y compra express; visualizar los pedidos almacenados en memoria; validar los datos ingresados; y simular la asignación y entrega concurrente de pedidos mediante diferentes repartidores.

La solución utiliza programación orientada a objetos, herencia, polimorfismo, interfaces, colecciones, manejo de hilos e interfaces gráficas construidas con Java Swing.

---

## ✅ Funcionalidades principales

- Registro de pedidos de comida, encomienda y compra express.
- Validación de campos obligatorios y valores numéricos positivos.
- Validación de identificadores de pedido repetidos.
- Habilitación dinámica de campos según el tipo de pedido seleccionado.
- Almacenamiento temporal de pedidos en una lista en memoria.
- Visualización de pedidos mediante un `JTable` y `DefaultTableModel`.
- Simulación de entregas realizadas por varios repartidores.
- Procesamiento concurrente mediante `ExecutorService` e implementación de `Runnable`.
- Actualización del estado del pedido desde `PENDIENTE` hasta `ENTREGADO`.
- Navegación entre las ventanas de registro, listado y simulación.

---

## 🧱 Estructura general del proyecto

```plaintext
📁 SpeedFastSem6/
├── 📁 src/
│   ├── 📁 controlador/
│   │   └── ControladorPedido.java
│   ├── 📁 interfaces/
│   │   ├── Cancelable.java
│   │   ├── Despachable.java
│   │   └── Rastreable.java
│   ├── 📁 main/
│   │   └── Main.java
│   ├── 📁 modelo/
│   │   ├── Direccion.java
│   │   ├── EstadoPedido.java
│   │   ├── Pedido.java
│   │   ├── PedidoComida.java
│   │   ├── PedidoEncomienda.java
│   │   ├── PedidoExpress.java
│   │   ├── Repartidor.java
│   │   └── ZonaDeCarga.java
│   └── 📁 vista/
│       ├── VentanaPrincipal.java
│       ├── VentanaRegistrarPedido.java
│       ├── VentanaListarPedidos.java
│       └── VentanaSimulacion.java
├── .gitignore
└── SpeedFastSem6.iml
```

### Responsabilidad de los paquetes

- **controlador:** administra la lista compartida de pedidos y las operaciones asociadas.
- **interfaces:** contiene los contratos implementados por los distintos tipos de pedido.
- **main:** contiene el punto de entrada de la aplicación.
- **modelo:** contiene las clases de dominio, estados, pedidos, repartidores y lógica concurrente.
- **vista:** contiene las ventanas y formularios desarrollados con Java Swing.

---

## 🧩 Modelo de pedidos

La clase abstracta `Pedido` concentra los datos y comportamientos comunes. A partir de ella se implementan tres tipos de pedidos:

- `PedidoComida`: almacena el restaurante asociado.
- `PedidoEncomienda`: almacena el peso y el tipo de embalaje.
- `PedidoExpress`: almacena el comercio asociado.

Cada pedido comienza con estado `PENDIENTE`. Durante la simulación cambia a `EN_REPARTO` y finalmente a `ENTREGADO`.

---

## 🛠️ Tecnologías utilizadas

- Java 17.
- Java Swing.
- IntelliJ IDEA.
- Programación orientada a objetos.
- `ArrayList` para el almacenamiento en memoria.
- `PriorityBlockingQueue` para administrar pedidos concurrentes.
- `ExecutorService` para ejecutar repartidores en paralelo.
- Git y GitHub para control de versiones.

---

## ⚙️ Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/sandramamani-rep/SistemaSpeedFastSem6.git
```

2. Abre IntelliJ IDEA.

3. Selecciona **Open** y abre la carpeta `SistemaSpeedFastSem6`.

4. Verifica que el proyecto utilice JDK 17:

```plaintext
File > Project Structure > Project SDK > Java 17
```

5. Espera a que IntelliJ reconozca la estructura y compile el proyecto.

6. Ejecuta la clase:

```plaintext
src/main/Main.java
```

7. Desde la ventana principal podrás registrar pedidos, consultar el listado e iniciar la simulación de entregas.

---

## 🖥️ Uso de la aplicación

1. Selecciona **Registrar pedido**.
2. Ingresa el número del pedido, la dirección y la distancia.
3. Selecciona el tipo de pedido.
4. Completa los datos particulares solicitados.
5. Presiona **Agregar** para guardar el pedido en memoria.
6. Selecciona **Listar pedidos** para consultar los pedidos registrados.
7. Selecciona **Asignar repartidor / Iniciar entrega** para ejecutar la simulación concurrente.

---

## 📌 Consideraciones

- Los datos se almacenan únicamente en memoria y se eliminan al cerrar la aplicación.
- No se requiere conexión a una base de datos para esta actividad.
- El número de cada pedido debe ser único y mayor que cero.
- La distancia, el número de la dirección y el peso deben ser valores positivos.
- La simulación procesa solamente los pedidos que se encuentran en estado `PENDIENTE`.

---

## 🔗 Repositorio y entrega

- **Repositorio GitHub:** https://github.com/sandramamani-rep/SistemaSpeedFastSem6.git
- **Fecha de entrega:** [21/09/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Actividad Semana 6
