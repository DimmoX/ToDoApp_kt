package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.models.UsuariosModel
import com.example.todoapp.data.repository.UsuariosRepository

class CreateAccountViewModel: ViewModel() {
    private val _usuariosRepository = UsuariosRepository()

    fun crearUsuario(nombre: String, apellido: String, email: String, passwd: String) {
        try{
            var cantidadUsuarios = _usuariosRepository.obtenerUsuarios().size
            Log.d("CreateAccountViewModel", "Cantidad de usuarios: $cantidadUsuarios")
            var id: Int = cantidadUsuarios + 1
            var usuario: UsuariosModel = (
                    UsuariosModel(
                        id,
                        nombre,
                        apellido,
                        email,
                        passwd
                    ))
            _usuariosRepository.agregarUsuario(usuario)

            Log.d("CreateAccountViewModel", "Usuario creado: $usuario")

        } catch (e: Exception) {
            Log.e("CreateAccountViewModel", "Error al crear usuario: ${e.message}")

        }

    }

}