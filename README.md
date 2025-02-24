# 📌 Organizador de tareas - ToDoApp

_ToDoApp_ es una aplicación diseñada para organizar y mantener el control de las actividades diarias. Permite a los usuarios crear tareas, cambiar su estado y visualizarlas de manera organizada. Además, incorpora **reconocimiento de voz**, lo que facilita la creación de tareas mediante comandos de voz, permitiendo ingresar tanto el título como la descripción de una tarea.

Los datos de las tareas se almacenan en **Firebase Realtime Database**, lo que permite una sincronización en tiempo real y acceso desde cualquier dispositivo.

## 🛠️ Tecnologías utilizadas

- **Lenguaje:** Kotlin
- **Framework:** Jetpack Compose
- **Base de datos:** Firebase Realtime Database
- **Reconocimiento de voz:** API de reconocimiento de voz de Android

## ✨ Características principales

✅ **Gestión de tareas:**  
   - Crear nuevas tareas.  
   - Actualizar el estado de las tareas entre "Pendiente" y "Completada".  
✅ **Almacenamiento en la nube:**  
   - Las tareas se almacenan en **Firebase Realtime Database**, permitiendo acceso en tiempo real.  
✅ **Reconocimiento de voz:**  
   - Permite la transcripción de voz a texto para ingresar el título y la descripción de una tarea.  
✅ **Vistas diferenciadas:**  
   - **Tareas pendientes:** Muestra todas las tareas que aún no han sido completadas.  
   - **Tareas completadas:** Muestra las tareas que han sido finalizadas.  

## 🚀 Cómo ejecutar el proyecto en Android Studio

Sigue estos pasos para ejecutar la aplicación en **Android Studio**:

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/ToDoApp.git
   ```
## 🚀 Cómo ejecutar el proyecto en Android Studio

### 2. Abrir el proyecto en Android Studio

- Inicia **Android Studio**.
- Selecciona **File > Open** y elige la carpeta donde clonaste el repositorio.
- Espera a que Android Studio sincronice el proyecto y descargue todas las dependencias.

### 3. Ejecutar la aplicación en dispositivo android

- Activar opciones de desarrollador.
- Depuración por USB.
- Conecta un dispositivo Android o inicia un emulador desde Android Studio.
- Presiona el botón **Run** o usa el atajo de teclado `Shift + F10`.

---

## ⚠️ Notas adicionales

- **Permisos de micrófono:** Para que el reconocimiento de voz funcione correctamente, asegúrate de que la aplicación tenga acceso al micrófono en el dispositivo.
- **Soporte de reconocimiento de voz:** La función de transcripción de voz a texto utiliza la API de reconocimiento de voz de Android, por lo que requiere conexión a internet para funcionar correctamente.
- **Almacenamiento en la nube:**
  - Las tareas se almacenan en **Firebase Realtime Database**.
  - Asegúrate de configurar correctamente las reglas de seguridad en Firebase para permitir la lectura y escritura de datos de forma segura.


