## Desafío de código Kotlin + Jetpack - SODEP
### Objetivo:

El objetivo de este desafío es evaluar tus habilidades como desarrollador Android utilizando Kotlin y herramientas modernas de Jetpack.
Los criterios de evaluación se mencionan más adelante en esta guía.

### Descripción del desafío:

Para este desafío deberás crear una aplicación móvil sencilla para gestionar tareas y eventos personales.

> Por favor lee atentamente toda la documentación del desafío para asegurarte de no pasar por alto ningún detalle.

### Aplicación base:
El código fuente proporcionado sirve como punto de partida para tu aplicación. Incluye únicamente un Activity 
con un mensaje de bienvenida, el cual podés modificar libremente para comenzar con el desarrollo.

### Especificaciones:

#### Interfaz de Usuario:

La aplicación debe tener una pantalla principal con una lista de tareas/eventos con las siguiente características:
- Cada elemento de la lista debe mostrar:
  - **Título de la tarea/evento**
  - **Fecha y hora**
  - **Prioridad (alta, media, baja)**
- La pantalla principal debe tener un botón flotante **(+)** para agregar nuevas tareas/eventos.
- Al tocar un elemento de la lista, se debe abrir una pantalla de detalles con la información completa de la tarea/evento.

#### Funcionalidades:

- **Agregar**: Permitir al usuario agregar nuevas tareas/eventos, incluyendo:
  - **Título (obligatorio)**
  - **Descripción (opcional)**
  - **Fecha y hora (obligatorio)**
  - **Prioridad (obligatorio)**
- **Editar**: Permitir al usuario editar tareas/eventos existentes.
- **Eliminar**: Permitir al usuario eliminar tareas/eventos.
- **Filtrar**: Permitir al usuario filtrar tareas/eventos por prioridad.
- **Persistencia**: Los datos deben guardarse localmente para que estén disponibles al volver a abrir la aplicación.

### Habilidades a Evaluar:

- **UI moderna**: Se debe usar Jetpack Compose. Se valorará la creación de componentes personalizables y reutilizables para la interfaz.
- **Navegación**: Uso de **Navigation Component** para la navegación entre pantallas.
- **Gestión de estados**: Se espera el uso de StateFlow/LiveData junto con ViewModel para la gestión de estados de la aplicación.
- **Inyección de dependencias**: Se recomienda usar **Hilt** para DI.
- **Almacenamiento local**: Se espera el uso de **DataStore** u otra solución de almacenamiento local para persistir los datos. 
- **Diseño responsive**: Se valorará la adaptación del diseño a diferentes tamaños de pantalla.
- **Principios SOLID**: Se valorará el cumplimiento de principios SOLID en el diseño del código.

#### Adicionales:
- **Consumo de APIs (opcional)**: Si deseas un desafío extra, puedes integrar una API REST para obtener datos relacionados con tareas o eventos. Se evaluará el uso de librerias como Retrofit o Ktor Client, el manejo de respuestas JSON, la autenticación y la gestión de errores. 
  
  Por ejemplo, podrías invocar una API para obtener imágenes al ver el detalle de un evento, utilizando servicios gratuitos como [Pexels](https://www.pexels.com/api/documentation/) u opciones similares. **Esta es solo una sugerencia; puedes explorar otras ideas según tu criterio.**
- **Animaciones (opcional)**: Se valorará la inclusión de animaciones suaves (por ejemplo, al agregar/eliminar tareas).

### Criterios de Evaluación:

- **Funcionalidad**: La aplicación cumple con todas las especificaciones o requisitos funcionales.
- **UI/UX**: La interfaz es intuitiva, atractiva y fácil de usar.
- **Código**: El código es limpio, organizado, reutilizable y bien documentado.
- **Arquitectura**: La aplicación utiliza una arquitectura escalable y modular. Se evaluará la utilización 
  de principios como **Separación de problemas** (Separation of concerns) o patrones como **UDF.**
- **Rendimiento**: La aplicación tiene un buen rendimiento y responde de manera fluida.

> Si no logras completar todos los requisito de funcionalidad, puedes subir tu código hasta donde
hayas logrado avanzar. Evaluaremos la implementación de forma gradual teniendo en cuenta los requisitos
individuales logrados.

### Como subo mi código?:
* Debes crear una rama con la siguiente nomenclatura:
  `feature/nombre_candidato`
* Debes subir la rama al repositorio dentro del margen de tiempo definido para el desafío.

### Recursos adicionales:

- [Documentación oficial de Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
- [Navigation Component (Jetpack Navigation)](https://developer.android.com/guide/navigation)
- [StateFlow vs LiveData](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Guía oficial de arquitectura Android](https://developer.android.com/jetpack/guide)
- [Hilt para inyección de dependencias](https://developer.android.com/training/dependency-injection/hilt-android)
- [DataStore (reemplazo moderno de SharedPreferences)](https://developer.android.com/topic/libraries/architecture/datastore)

### Consejos para completar el desafío en 48 horas:

* **Planifica tu tiempo**: Divide el desafío en tareas más pequeñas y establece plazos para cada una de ellas.
* **Comienza con una implementación básica**: No te preocupes por agregar todas las funciones de inmediato.
  Concéntrate en crear una versión funcional básica de la aplicación y luego agrega las funciones
  adicionales una a una.
* **Escribe código reutilizable**: Evita repetir el mismo código varias veces. Crea funciones y widgets
  reutilizables para simplificar tu código.
* **Prueba tu código con frecuencia**: Ejecuta tu aplicación con frecuencia para asegurar de que funciona correctamente.


No dudes en consultar cualquier duda que tengas sobre el desafío, estaremos contentos de responderte.

!Esperamos que disfrutes del desafío, te animamos a dar lo mejor de tí y demostrar tu talento con Koltin! :smiley:

