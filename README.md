# La tarea asignada consistía en desarrollar una aplicación simple en Flutter que incorporase varios widgets y un diseño de interfaz de usuario básico. La aplicación debía incluir funcionalidades básicas como cambiar un texto y mostrar/ocultar una imagen mediante la interacción con botones. Además, debía tener un diseño visual agradable.

## Desafíos y Soluciones:

Diseño de la Interfaz de Usuario:

Desafío: Crear una interfaz de usuario que sea intuitiva y visualmente atractiva, utilizando varios widgets y personalizando su apariencia.
Solución: Se utilizó una combinación de widgets estructurales y de diseño, como Column, Row, Container, Text, Image, Button, e Icon. Se personalizó la apariencia utilizando diferentes propiedades como colores, márgenes y fuentes.
Funcionalidad de Cambio de Texto:

Desafío: Permitir que el texto cambie al presionar un botón.
Solución: Se implementó un método _cambiarTexto() que se activa al presionar un botón, modificando el estado del texto en la aplicación.
Funcionalidad de Mostrar/Ocultar Imagen:

Desafío: Permitir mostrar y ocultar una imagen al presionar un botón.
Solución: Se implementó un método _toggleImagen() que se activa al presionar otro botón, modificando el estado de la visibilidad de la imagen en la aplicación.
Ajuste del Tamaño y Posición de la Imagen:

Desafío: La imagen inicialmente ocupaba mucho espacio en la pantalla.
Solución: Se utilizó un Container para definir las dimensiones de la imagen y se aplicó la propiedad fit: BoxFit.cover para mantener el aspecto de la imagen mientras se ajusta al espacio disponible.
Adición de Estrellas Interactivas:

Desafío: Añadir cinco estrellas interactivas que respondan a la interacción del usuario.
Solución: Se utilizó List.generate junto con IconButton para crear cinco estrellas interactivas.
Problemas de Desbordamiento:

Desafío: Se encontraron algunos problemas de desbordamiento durante el desarrollo.
Solución: Se introdujeron widgets como SingleChildScrollView y Expanded para manejar el desbordamiento y asegurar que todos los elementos de la interfaz de usuario se ajusten correctamente en la pantalla.
