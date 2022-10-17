# ExamDayStore

<div align="center"> 
  <img src="https://iili.io/tBxv9f.jpg" alt="screenshot" />
</div>

<!-- Pre requisitos -->
### :bangbang: Prerequisites :bangbang:

Este proyecto fue creado con la version de Android Studio Dolphin | 2021.3.1.

Por lo que se recomienda enormemente que se compile este proyecto en la misma version del IDE.

Tambien se debe de contar con una APIKey de Google Maps, para obtenerla visite el siguiente link.
[Google Maps Api Key](https://developers.google.com/maps/documentation/android-sdk/get-api-key)

Tambien se debe de tener instalado Git, para su instalacion visite el siguiente link.
[Descargar Git](https://git-scm.com/downloads)


<!-- Instalación -->
### :gear: Instalación

El proyecto de debe de abrir como cualquier otro, con la excepcion de agregar la ApiKey de Google Maps en el proyecto.
La Api Key nunca debe de ser incluida en los archivos del proyecto por que puede ser utilizada por terceros para fines diferentes a los nuestros, por lo que es mejor
que cada persona agregue el suyo y la ejecucion del proyecto sea bajo su propia responsabilidad.

El archivo donde debemos de agregar la Api Key es el local.properties y se debe de agregar de la siguiente forma:

```bash

  MAPS_API_KEY= XXXXXXXXXXXXXXXX

```

Clonar el proyecto.

Para clonar el proyecto se debe de tener instalado una version de git actual, una vez instalada, se ejecuta el siguiente comando en su terminal para generar una copia del proyecto.

```bash

  git clone https://github.com/cod3f1re/ExamDayStore.git

```

<!-- Arquitectura -->
### :camera: Arquitectura utilizada en el proyecto

Se utilizo un patrón MVVM para tener una arquitectura mas limpia y modular, haciendo posible modificar funciones cruciales en el proyecto, sin afectar otros modulos de la aplicación, con un modulo extra llamado utils, que nos provee de clases adicionales para funciones especificas que facilitan el desarrollo del proyecto.

<div align="left"> 
  <img src="https://iili.io/tBngR4.png" alt="screenshot" />
</div>



