# ExamDayStore


<!-- Pre requisitos -->
### :bangbang: Pre requisitos :bangbang:

Este proyecto fue creado con la version de Android Studio Dolphin | 2021.3.1.

Por lo que se recomienda enormemente que se compile este proyecto en la misma version del IDE.

Tambien se debe de contar con una APIKey de Google Maps, para obtenerla visite el siguiente link.
[Google Maps Api Key](https://developers.google.com/maps/documentation/android-sdk/get-api-key)

Tambien se debe de tener instalado Git, para su instalacion visite el siguiente link.
[Descargar Git](https://git-scm.com/downloads)


<!-- Instalación -->
### :key: Instalación :key:

El proyecto de debe de abrir como cualquier otro, con la excepcion de agregar la ApiKey de Google Maps en el proyecto.
La Api Key nunca debe de ser incluida en los archivos del proyecto por que puede ser utilizada por terceros para fines diferentes a los nuestros, por lo que es mejor
que cada persona agregue el suyo y la ejecucion del proyecto sea bajo su propia responsabilidad.

El archivo donde debemos de agregar la Api Key es el local.properties y se debe de agregar de la siguiente forma:

```bash

  MAPS_API_KEY= XXXXXXXXXXXXXXXX

```

<!-- Clonacion -->
### :computer: Clonar el proyecto :computer:

Para clonar el proyecto se debe de tener instalado una version de git actual, una vez instalada, se ejecuta el siguiente comando en su terminal para generar una copia del proyecto.

```bash

  git clone https://github.com/cod3f1re/ExamDayStore.git

```

<!-- Bibliotecas -->
### :mag: Bibliotecas utilizadas :mag:

La aplicacion utiliza varias bibliotecas que aseguran un desarrollo robusto y optimizable, las cuales son:

1.-Room

2.-Databinding

3.-Patron MVVM

4.-Lifeycle

5.-Material Design


<!-- Caracteristicas -->
### :calling: Caracteristicas de la app :calling:

La aplicación aunque no cuente con demasiados modulos, implementa varias funcionalidades que deberían de ser explicadas. 

  

1.-El login valida el formulario de inicio de sesión, colocando símbolos de error y una descripción exacta del por qué no se pudo iniciar sesión, una vez validados los campos si todo salio correctamente, manda 3 campos (email, pass y name que es mi nombre) al endpoint solicitado, retornando un código que posteriormente sera ocupado y guardado en las SharedPreferences 

  

2.-Una vez pulsado el boton de iniciar sesión, si se recibe exactamente el codigo 201 como respuesta, se tomará como valido el "login", mostrando asi una notificación en el celular, donde se incluye el código que recibe como respuesta del endpoint, en donde si pulsamos la notificación, nos redirige a una parte de la aplicación donde podemos enviar nuestra ubicación al endpoint, y si no pulsamos la notificación, la aplicación nos redirige automáticamente al menú principal. 

  

3.-El menú principal verifica que haya una sesion previamente iniciada, si es asi, muestra el correo con el que iniciaron sesión y muestra el código que obtuvo del endpoint por si se requiere verificarlo nuevamente, y lo IMPORTANTE es que una vez iniciada la sesión la aplicación puede cerrarse y al abrirla nuevamente redirige al menu, esto es por que lee los datos de los sharedpreferences, que son eliminados hasta que presiona el boton de cerrar sesion. 

  

4.-Si se pulsa el botón de enviar ubicación desde el menu principal la aplicación nos va a redirigir a una nueva ventana en donde vamos a poder ver un mapa de Google Maps , incluyendo nuevamente el correo electronico que indicamos, y un botón verde en el que, si pulsamos, vamos a poder enviar las coordenadas al endpoint y una vez recibido una respuesta, se va a guardar localmente la latitu y la longitud en una bd local con Room. 

  

5.-Si se presiona el botón de Ver historial desde la interfaz de enviar ubicación, se mostrará una nueva pantalla donde podemos ver el registro completo de las coordenadas enviadas al endpoint junto con el código que se obtuvo dentro de un recyclerview que lee los datos desde la bd con Room. 

  

6.-Si se presiona algún item de localización se abrira nuevamente el mapa, y pondra automáticamente el marcador que indica la ubicación que se guardó en la bd y que fue presionada anteriormente. 

<!-- Arquitectura -->
### :triangular_ruler: Arquitectura utilizada en el proyecto :triangular_ruler:

Se utilizo un patrón MVVM para tener una arquitectura mas limpia y modular, haciendo posible modificar funciones cruciales en el proyecto, sin afectar otros modulos de la aplicación, con un modulo extra llamado utils, que nos provee de clases adicionales para funciones especificas que facilitan el desarrollo del proyecto.

<div align="left"> 
  <img src="https://iili.io/tBngR4.png" alt="screenshot" />
</div>



<!-- Screenshots -->
### :triangular_ruler: Screenshots :triangular_ruler:


<div align="left"> 
  <img src="https://iili.io/txQiMl.jpg" alt="screenshot" height="400" />
  <img src="https://iili.io/txZJMQ.jpg" alt="screenshot" height="400" />
  <img src="https://iili.io/txZRSt.jpg" alt="screenshot" height="400" />
</div>
<div align="left"> 
  <img src="https://iili.io/txZwOu.jpg" alt="screenshot" height="400" />
  <img src="https://iili.io/tz6I1t.jpg" alt="screenshot" height="400" />
  <img src="https://iili.io/txbcDg.jpg" alt="screenshot" height="400" />
</div>
