Informe de Implementación: Aplicación de Lista de Tareas con ViewModel, LiveData y Room en Android
Resumen:
La tarea consistió en desarrollar una aplicación Android de lista de tareas utilizando arquitecturas y tecnologías específicas como ViewModel, LiveData y Room Database. La aplicación permite a los usuarios agregar, editar, eliminar y marcar tareas como completadas, además de persistir estos datos para que sean accesibles en futuras sesiones.

Desafíos y Soluciones:

Implementación de Room Database:
Desafío: Configurar y utilizar una base de datos Room para gestionar las tareas.
Solución: Se definió una entidad Task, un DAO TaskDao, y una base de datos AppDatabase para configurar y manejar las operaciones de la base de datos.

Utilización de ViewModel y LiveData:
Desafío: Mantener los datos de la aplicación consistentes y observables en la UI.
Solución: Se utilizó ViewModel para gestionar los datos de la lista de tareas y LiveData para actualizar la UI automáticamente con los cambios en los datos.

Creación y Gestión de Tareas:
Desafío: Permitir a los usuarios agregar, editar y eliminar tareas de manera efectiva.
Solución: Se implementaron diálogos y actividades para agregar y editar tareas y se configuraron los eventos de clic y de checkbox para gestionar las tareas de manera efectiva.

Visualización y Gestión de la Lista de Tareas:
Desafío: Mostrar las tareas en un RecyclerView y gestionar la selección de tareas.
Solución: Se configuró un RecyclerView y un Adapter para mostrar las tareas y se implementaron listeners para gestionar las interacciones del usuario.

Gestión de Estado de Tareas:
Desafío: Permitir a los usuarios marcar tareas como completadas y reflejar esto visualmente.
Solución: Se agregó un CheckBox en la vista de cada tarea y se gestionaron sus eventos para actualizar el estado de las tareas en la base de datos y en la UI, tachando las tareas que se seleccionen.
