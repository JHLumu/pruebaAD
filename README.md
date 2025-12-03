# Introducción

El objetivo de este proyecto es desarrollar una aplicación que facilite la compraventa de productos de segunda mano. La plataforma, `SegundUM`, permitirá a los usuarios publicar productos para su venta y a otros usuarios adquirirlos de manera sencilla. Los productos estarán organizados en categorías, y los usuarios podrán recibir valoraciones tanto como vendedores como compradores, promoviendo la confianza dentro de la comunidad.

El primer entregable de la asignatura se centra en la implementación de la funcionalidad relacionada con la gestión de usuarios, productos y categorías: `ServicioUsuarios`, `ServicioProductos` y `ServicioCategorias`

---

## Entrega 1

### Historias de usuario

1.  Como usuario, quiero registrarme en la aplicación para poder acceder a sus funcionalidades.
2.  Como usuario, quiero modificar mis datos personales para mantener mi información actualizada.
3.  Como usuario, quiero dar de alta un producto para ponerlo a la venta.
4.  Como usuario, quiero modificar mis productos a la venta para cambiar su precio y/o descripción para que sus datos estén actualizados.
5.  Como usuario, quiero asociar un lugar de recogida a un producto que he puesto a la venta para facilitar su entrega.
6.  Como usuario, quiero obtener un resumen mensual de mis productos en venta y sus visualizaciones.
7.  Como usuario, quiero consultar los productos a la venta filtrando por descripción, categoría, estado y precio para localizar productos que me interesan.
8.  Como administrador, quiero cargar nuevas categorías para clasificar productos.
9.  Como administrador, quiero modificar las categorías existentes para que tengan una descripción.

---

### Modelo del dominio

| Usuario |
| :--- |
| id: texto |
| email: texto |
| nombre: texto |
| apellidos: texto |
| clave: texto |
| fecha de nacimiento: fecha |
| telefono: texto |
| administrador: booleano |

| Categoría |
| :--- |
| id: texto |
| nombre: texto |
| descripción: texto |
| ruta: texto |
| subcategorias: [Categoría] |

| Producto |
| :--- |
| id: texto |
| título: texto |
| descripción: texto |
| precio: número |
| estado: enum{nuevo, como nuevo, buen estado, aceptable, para piezas o reparar} |
| fecha publicacion: fecha y hora |
| categoria: Categoría |
| visualizaciones: número |
| envio disponible: booleano |
| recogida: Lugar de recogida |
| vendedor: Usuario |

| Lugar de recogida |
| :--- |
| descripción: texto |
| longitud: número |
| latitud: número |

---

### Funcionalidad

#### servicioCategorias
El servicio `servicioCategorias` define la siguiente funcionalidad:
* **Cargar jerarquía de categorías**: Recibe la ruta de un fichero `xml` con la jerarquía de categorías. Procesa y almacena la jerarquía, pero no debe cargar categorías principales que ya existan.
* **Modificar una categoría**: Añade o cambia la descripción de una categoría a partir de su identificador.
* **Recuperar categorías raíz**: Devuelve un listado con las categorías que no son subcategoría de ninguna otra.
* **Recuperar descendientes de una categoría**: Recibe un identificador de categoría y devuelve un listado de todas sus subcategorías.

#### servicioUsuarios
El servicio `servicioUsuarios` ofrece la siguiente funcionalidad:
* **Alta de un usuario**: Registra un usuario con su nombre, apellidos, email, clave, fecha de nacimiento y teléfono (opcional). Se le asigna un ID único y no será administrador.
* **Modificar un usuario**: Permite modificar el nombre, apellidos, clave, fecha de nacimiento y teléfono de un usuario a partir de su identificador.

#### servicioProductos
El servicio `servicioProductos` ofrece la siguiente funcionalidad:
* **Alta de un producto**: Registra un producto con su título, descripción, precio, estado, ID de categoría, disponibilidad de envío e ID del vendedor. La aplicación le asigna un ID único y la fecha de publicación actual.
* **Asignar lugar de recogida**: Establece el lugar de recogida de un producto a partir de su identificador, longitud, latitud y descripción.
* **Modificar datos de un producto**: Permite cambiar el precio y/o la descripción de un producto.
* **Añadir visualización**: Incrementa en uno el contador de visualizaciones de un producto.
* **Historial del mes**: Devuelve un resumen (título, precio, fecha, categoría y visualizaciones) de los productos en venta para un mes y año concretos, ordenados por número de visualizaciones.
* **Buscar productos a la venta**: Filtra productos por categoría (incluyendo descendientes), texto en la descripción, estado (o mejor) y precio máximo. Todos los parámetros son opcionales. El orden de los estados es: `nuevo > como nuevo > buen estado > aceptable > para piezas o reparar`.

---

### Requisitos de diseño
La implementación debe seguir los siguientes principios de diseño:
* Inversión de dependencias.
* Patrón Repositorio y Repositorio AdHoc.
* Patrón Servicio.

---

### Requisitos de implementación
* Uso del API `JAXB` de XML para la carga de jerarquías de categorías. Los ficheros se encuentran en el archivo `categoriasXML.zip`.
* Todos los repositorios deben usar `JPA` para la persistencia de entidades en `MySQL`.

---

### Pruebas
Se debe incluir un programa para probar toda la funcionalidad de los servicios (`ServicioCategorias`, `ServicioUsuarios` y `ServicioProductos`). El programa debe mostrar por consola mensajes sobre el resultado de las acciones.

---

### Entrega del trabajo
* **Grupo**: El trabajo será realizado en grupos de dos estudiantes.
* **Entrega**: Se entregará a través de un repositorio Git en GitHub y en el Aula Virtual.
* **Historial Git**: Es obligatorio el uso de Git desde el inicio del desarrollo; no se aceptará un repositorio solo con el código final.
* **Gestión con Maven**:
    * El nombre del proyecto debe ser `segundumApellido1-Apellido2`.
    * Debe incluir todas las dependencias necesarias.
    * Se debe usar Java `8` u `11` y codificación `UTF-8`.


## Entrega 2

La segunda parte del proyecto consiste en la implementación de la capa de presentación haciendo uso del framework **JSF**, vistas **Facelets** y la biblioteca de componentes **PrimeFaces**. Las interfaces desarrolladas deben permitir a un usuario realizar las siguientes acciones:

- **Acceso a la aplicación:** el usuario debe poder iniciar sesión usando sus credenciales (email y clave). Esta interfaz requiere crear una nueva funcionalidad en `ServicioUsuarios` que reciba el email y la clave, y retorne los datos del usuario si existe.
- **Cerrar sesión:** si un usuario ha iniciado sesión, debe poder cerrarla.
- **Crear un producto:** un usuario autenticado debe poder crear un producto para ponerlo a la venta. La interfaz le debe permitir introducir todos los datos necesarios para crear el producto.
- **Ver productos creados:** un usuario autenticado debe poder consultar un listado de los productos que ha puesto a la venta. Este listado deberá mostrar al menos título, precio, estado, fecha de publicación, categoría y número de visualizaciones. Esta interfaz requiere crear una nueva funcionalidad en `ServicioProductos` que reciba el identificador de un vendedor y devuelva sus productos puestos a la venta.
- **Editar producto:** un usuario autenticado debe poder modificar precio y/o descripción de sus productos a la venta.
- **Buscador de productos:** un usuario debe poder buscar productos disponibles filtrando por categoría, descripción, estado y precio máximo. El listado resultante debe mostrar, al menos, el título, precio, estado y categoría de cada producto.
- **Ver detalle de un producto:** un usuario autenticado debe poder consultar el detalle de un producto a la venta. En el detalle podrá ver la ficha del producto donde aparecerá título, descripción, precio, estado, fecha de publicación, categoría, disponibilidad de envío, descripción del lugar de recogida (si existe) y nombre del vendedor. Acceder a esta vista incrementará en uno el número de visualizaciones del producto.

> El número de vistas `.xhtml` no necesariamente debe coincidir con el número de funcionalidades aquí descritas. Algunas pueden implementarse conjuntamente en una misma vista o integrarse en menús o cabeceras compartidas.

---

### Requisitos de diseño
En todo el trabajo se deberán seguir los principios de diseño vistos en la asignatura.

---

### Requisitos de implementación
Todas las vistas harán uso de la librería de componentes PrimeFaces y de las librerías de componentes de JSF.  
Se valorará la amigabilidad de la interfaz, el aprovechamiento de los componentes y funcionalidades de JSF y PrimeFaces, y las posibilidades de navegación implementadas.  
La capa de presentación desarrollada en este entregable debe apoyarse en los servicios implementados en el entregable anterior.

---

### Documentación
El alumno deberá entregar un breve manual de usuario que describa el uso de las vistas implementadas. Este manual debe ser lo más breve posible y puede ir acompañado de capturas de pantalla. De forma adicional, el alumno puede usar este documento para comentar cualquier decisión de diseño tomada o aspecto que considere relevante de su trabajo. Es importante que este documento sea breve, claro y preciso, además de en perfecto castellano.

---

### Entrega del trabajo
- Se trabajará sobre el mismo proyecto utilizado en la entrega anterior.
- La entrega del trabajo será realizada en un repositorio Git en GitHub y en una tarea de Aula Virtual. En el caso de la documentación, es suficiente con subirla a la tarea del Aula Virtual.
- El repositorio Git debe ser utilizado desde el comienzo del desarrollo del proyecto y con la participación de los integrantes del grupo. No será aceptada una entrega que simplemente aloje el código del proyecto sin el historial de trabajo (commits) previo.
- Los proyectos Java Maven deben estar configurados con todas las dependencias necesarias.
- Fecha de entrega: 10 de diciembre de 2025


