package devmovil.duocuc.todoapp.ui.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devmovil.duocuc.todoapp.data.models.TareasModel
import devmovil.duocuc.todoapp.data.repository.TareasRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TareasViewModel : ViewModel() {

    private val _agregarTarea = MutableLiveData<Boolean>()
    private val _tareasRepository = TareasRepository
    private val _tareas = MutableLiveData<List<TareasModel>>(emptyList())
    val tareas: LiveData<List<TareasModel>> = _tareas

    private var tareasCargadas = false

    val tareasList: LiveData<List<TareasModel>> = _tareasRepository.tareasList

    /**
     * Agregar tarea
     * @param titulo Titulo de la tarea
     * @param descripcion Descripcion de la tarea
     */
    fun agregarTarea(titulo: String, descripcion: String) {

        try {
            val fechaHoraActual = LocalDateTime.now()

            val fechaActual = fechaHoraActual.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val horaActual = fechaHoraActual.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            val tarea = TareasModel(
                titulo = titulo,
                descripcion = descripcion,
                fecha = fechaActual,
                hora = horaActual
            )

            val tareaCreada = _tareasRepository.agregarTarea(tarea)

            tareaCreada.observeForever { isSuccess ->
                if (isSuccess) {
                    obtenerTareas()
                }
            }
        } catch (e: Exception) {
            Log.e("agregarTarea", "Error al agregar tarea: ${e.message}")
        }
    }

    /**
     * Obtener tareas del repositorio
     */
    fun obtenerTareas() {
        if (!tareasCargadas) {
            _tareasRepository.obtenerTareas()

            viewModelScope.launch {
                tareasList.observeForever { tareasList ->
                    if (tareasList != null && tareasList.isNotEmpty()) {
                        _tareas.value = tareasList
                        tareasCargadas = true
                    }
                }
            }
        }
    }

    /**
     * Actualizar status de la tarea
     * @param id Id de la tarea
     * @param status Status de la tarea
     */
    fun actualizarStatusTarea(id: String, status: Boolean) {
        _tareasRepository.actualizarStatusTarea(id, status)
    }

    /**
     * Obtener tarea
     * @param id Id de la tarea
     */
    fun obtenerTarea(id: String) {
        _tareasRepository.obtenerTarea(id)
    }
}
