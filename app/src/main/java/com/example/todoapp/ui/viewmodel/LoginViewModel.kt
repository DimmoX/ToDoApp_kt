package com.example.todoapp.ui.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.models.UsuariosModel
import com.example.todoapp.data.repository.UsuariosRepository

class LoginViewModel: ViewModel() {

    private val _usuariosRepository = UsuariosRepository()

    private val _usuario = mutableStateOf<UsuariosModel?>(null)
    val usuario: State<UsuariosModel?> get() = _usuario

    private val _usuarios = mutableStateOf<List<UsuariosModel>>(emptyList())
    val usuarios: State<List<UsuariosModel>> get() = _usuarios

    fun agregarUsuario(usuarioModel: UsuariosModel) {
        _usuariosRepository.agregarUsuario(usuarioModel)
    }

    fun validarLogin(email: String, passwd: String): String{
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "El email ingresado no es válido"
        }
        if (passwd.length < 8) {
            return "La contraseña debe tener al menos 8 caracteres"
        }

        val usuarioEncontrado = _usuariosRepository.obtenerUsuario(email, passwd)
        _usuario.value = usuarioEncontrado

        Log.d("LoginViewModel-usuarioEncontrado", "Usuario: $usuarioEncontrado")

        return if (usuarioEncontrado != null) {
            "true"
        } else {
            "Credenciales incorrectas"
        }
    }

    fun obtenerUsuarios(): List<UsuariosModel> {
        return _usuariosRepository.obtenerUsuarios()
    }

    fun eliminarUsuario(email: String){
        val success = _usuariosRepository.eliminarUsuario(email)
        if (success) {
            _usuarios.value = _usuariosRepository.obtenerUsuarios()
        }
    }

//    fun usuarioActual(): UsuariosModel? {
//        return _usuario.value
//    }
}