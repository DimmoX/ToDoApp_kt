package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.models.UsuariosModel
import com.example.todoapp.data.repository.UsuariosRepository

/**
 * ViewModel para la creación de un nuevo usuario
 */
class CreateAccountViewModel: ViewModel() {
    private val _agregarUsuarioStatus = MutableLiveData<Boolean>()
    val agregarUsuarioStatus: LiveData<Boolean> get() = _agregarUsuarioStatus

    fun crearUsuario(nombre: String, apellido: String, email: String, passwd: String) {
        try{
            val usuario = UsuariosModel(
                nombre = nombre,
                apellido = apellido,
                email = email,
                passwd = passwd
            )
            val agregarUsuarioLiveData = UsuariosRepository.agregarUsuario(usuario)


            agregarUsuarioLiveData.observeForever{ isSuccess ->
                Log.d("CreateAccountViewModel", "Usuario ${email} creado")

                _agregarUsuarioStatus.postValue(isSuccess)
            }

        } catch (e: Exception) {
            Log.e("CreateAccountViewModel", "Error al crear usuario: ${e.message}")

        }
    }
}