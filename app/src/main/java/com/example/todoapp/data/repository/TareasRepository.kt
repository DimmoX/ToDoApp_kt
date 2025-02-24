package devmovil.duocuc.todoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import devmovil.duocuc.todoapp.data.models.TareasModel

object TareasRepository {

    private val _tareasList: MutableLiveData<List<TareasModel>> = MutableLiveData()
    val tareasList: LiveData<List<TareasModel>> get() = _tareasList

    private val _tarea: MutableLiveData<TareasModel?> = MutableLiveData()
    val tarea: LiveData<TareasModel?> get() = _tarea

    val database = Firebase.database
    val refTareas = database.getReference("tareas")

    fun agregarTarea(tarea: TareasModel): LiveData<Boolean> {
        val addTarea = MutableLiveData<Boolean>()
        val tareaId = refTareas.push().key
        tarea.id = tareaId ?: ""

        refTareas.child(tareaId!!).setValue(tarea)
            .addOnSuccessListener {
                addTarea.postValue(true)
                Log.d("TareasRepository", "Tarea ${tarea.id} agregada a Firebase")
            }
            .addOnFailureListener { exception ->
                addTarea.postValue(false)
                Log.e("TareasRepository", "Error al agregar tarea ${tarea.id}: ${exception.message}")
            }

        return addTarea
    }

    fun obtenerTareas() {
        val tareas = mutableListOf<TareasModel>()

        refTareas.get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach { child ->
                val tarea = child.getValue(TareasModel::class.java)
                if (tarea != null) {
                    tareas.add(tarea)
                }
            }
            _tareasList.postValue(tareas)
            Log.d("TareasRepository", "Tareas obtenidas: ${tareas.size}")
        }.addOnFailureListener { exception ->
            Log.e("TareasRepository", "Error al obtener tareas: ${exception.message}")
        }
    }

    fun obtenerTarea(id: String) {
        refTareas.child(id).get().addOnSuccessListener { snapshot ->
            val tarea = snapshot.getValue(TareasModel::class.java)
            _tarea.postValue(tarea)
            Log.d("TareasRepository", "Tarea ${id} obtenida correctamente")
        }.addOnFailureListener { exception ->
            Log.e("TareasRepository", "Error al obtener tarea ${id}: ${exception.message}")
        }
    }

    fun actualizarStatusTarea(id: String, status: Boolean) {
        refTareas.child(id).child("status").setValue(status)
            .addOnSuccessListener {
                Log.d("TareasRepository", "Status de tarea ${id} actualizado a ${status}")
            }
            .addOnFailureListener { exception ->
                Log.e("TareasRepository", "Error al actualizar status de tarea ${id}: ${exception.message}")
            }
    }
}
