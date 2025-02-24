package com.example.todoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.data.models.UsuariosModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object UsuariosRepository {

    // Lista mutable de usuarios
    private val usuarios: MutableList<UsuariosModel> = mutableListOf()

    // Se invoca a la base de datos de Firebase utilizando extensión KTX
    val database = Firebase.database
    // Se referencia a la colección de usuarios
    val refUsers = database.getReference("usuarios")

    /**
     * Agrega un usuario a la base de datos de Firebase
     * @param usuario - Usuario a agregar
     * @return LiveData<Boolean> - indica si la operación fue exitosa
     */
    fun agregarUsuario(usuario: UsuariosModel): LiveData<Boolean> {
        val addUser = MutableLiveData<Boolean>()

        val usuarioId = refUsers.push().key
        usuario.id = usuarioId ?: ""

        val emailValidado = usuario.email.replace(".", "_")

        refUsers.child(emailValidado).setValue(usuario)
            .addOnSuccessListener {
                addUser.postValue(true)
                Log.d("UsuariosRepository", "Usuario ${emailValidado} agregado a Firebase")
            }
            .addOnFailureListener { exception ->
                addUser.postValue(false)
                Log.e("UsuariosRepository", "Error al agregar usuario ${usuario.email}: ${exception.message}")
            }
        return addUser
    }

    /**
     * Obtiene la lista de usuarios de la base de datos de Firebase
     * @return List<UsuariosModel> - Lista de usuarios
     */
    fun obtenerUsuarios(): List<UsuariosModel> {

        refUsers.get().addOnSuccessListener { snapshot ->
            snapshot.children.forEach { child ->
                val usuario = child.getValue(UsuariosModel::class.java)
                if (usuario != null) {
                    usuarios.add(usuario)
                }
            }
            Log.d("UsuariosRepository", "Usuarios obtenidos exitosamente")
        }.addOnFailureListener { exception ->
            Log.e("UsuariosRepository", "Error al obtener usuarios: ${exception.message}")
        }
        Log.d("UsuariosRepository", "Usuarios obtenidos: ${usuarios.size}")
        return usuarios
    }

    /**
     * Obtiene un usuario de la base de datos de Firebase
     * @param email - Email del usuario
     * @param passwd - Contraseña del usuario
     * @return LiveData<UsuariosModel?> - Usuario obtenido
     */
    fun obtenerUsuario(email: String, passwd: String): LiveData<UsuariosModel?> {
        val usuarioLiveData = MutableLiveData<UsuariosModel?>()

        val emailValidado = email.replace(".", "_")

        refUsers.child(emailValidado).get()
            .addOnSuccessListener { dataSnapshot ->
                val usuario = dataSnapshot.getValue(UsuariosModel::class.java)

                if (usuario != null && usuario.passwd == passwd) {
                    usuarioLiveData.postValue(usuario)
                } else {
                    usuarioLiveData.postValue(null)
                }
            }
            .addOnFailureListener { exception ->
                usuarioLiveData.postValue(null)
            }

        return usuarioLiveData
    }

    /**
     * Elimina un usuario de la base de datos de Firebase
     * @param email - Email del usuario a eliminar
     * @return LiveData<Boolean> - indica si la operación fue exitosa
     */
    fun eliminarUsuario(email: String): LiveData<Boolean> {
        val deletionLiveData = MutableLiveData<Boolean>()

        val emailValidado = email.replace(".", "_")

        refUsers.child(emailValidado).removeValue()
            .addOnSuccessListener {
                deletionLiveData.postValue(true)
            }
            .addOnFailureListener { exception ->
                deletionLiveData.postValue(false)
            }

        return deletionLiveData
    }
}
