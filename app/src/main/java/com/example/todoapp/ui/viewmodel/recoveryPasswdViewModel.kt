package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repository.UsuariosRepository

class recoveryPasswdViewModel : ViewModel() {

    private val _recoveryResult = MutableLiveData<RecoveryResult>()
    val recoveryResult: LiveData<RecoveryResult> get() = _recoveryResult

    fun recoveryPassword(email: String) {
        val listUsuarios = UsuariosRepository.obtenerUsuarios()
        val usuario = listUsuarios.find { it.email == email }

        if (usuario != null) {
            _recoveryResult.value = RecoveryResult(
                success = true,
                message = "Se ha enviado un correo con las instrucciones para recuperar tu contraseña a ${usuario.email}",
                password = usuario.passwd
            )
        } else {
            _recoveryResult.value = RecoveryResult(
                success = false,
                message = "No se encontró un usuario con ese correo electrónico.",
                password = ""
            )
        }
    }

    data class RecoveryResult(
        val success: Boolean,
        val message: String,
        val password: String
    )
}
