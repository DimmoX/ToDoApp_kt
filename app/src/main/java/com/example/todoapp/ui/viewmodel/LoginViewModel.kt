package devmovil.duocuc.todoapp.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devmovil.duocuc.todoapp.data.models.UsuariosModel
import devmovil.duocuc.todoapp.data.repository.UsuariosRepository
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de login
 */
open class LoginViewModel: ViewModel() {

    private val _usuario = MutableLiveData<UsuariosModel?>()
    val usuario: LiveData<UsuariosModel?> get() = _usuario

    private val _agregarUsuarioStatus = MutableLiveData<Boolean>()
    val agregarUsuarioStatus: LiveData<Boolean> get() = _agregarUsuarioStatus

    private val _eliminacionStatus = MutableLiveData<Boolean>()
    val eliminacionStatus: LiveData<Boolean> get() = _eliminacionStatus


    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _loginStatus

    /**
     * Método para agregar un usuario
     * @param usuario Usuario a agregar
     * @param passwd Contraseña del usuario
     */
    fun validarLogin(email: String, passwd: String){
        if (email.isBlank() || passwd.isBlank()) {
            _loginStatus.postValue("Campos vacios")
            _errorMessage.postValue("Por favor, ingrese tanto el email como la contraseña")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _loginStatus.postValue("Email incorrecto")
            _errorMessage.postValue("Por favor, ingrese un email correcto")
            _usuario.postValue(null)
            return
        }
        if (passwd.length < 8) {
            _loginStatus.postValue("Contraseña incorrecta")
            _errorMessage.postValue("Por favor, ingrese una contraseña de al menos 8 caracteres")
            _usuario.postValue(null)
            return
        }

        viewModelScope.launch {
            try {
                val usuarioLiveData = UsuariosRepository.obtenerUsuario(email, passwd)

                usuarioLiveData.observeForever { usuario ->
                    if (usuario != null) {
                        _usuario.postValue(usuario)
                        _loginStatus.postValue("Login exitoso")
                    } else {
                        _usuario.postValue(null)
                        _loginStatus.postValue("Credenciales incorrectas")
                        _errorMessage.postValue("Credenciales incorrectas. Por favor verifique su email o contraseña.")
                    }
                }
            } catch (e: Exception) {
                _usuario.postValue(null)
                _loginStatus.postValue("Error en la conexión o en la consulta")
                _errorMessage.postValue("Hubo un error al intentar conectar. Intente más tarde.")
            }
        }
    }

    /**
     * Método para obtener un usuario
     */
    fun obtenerUsuarios(): List<UsuariosModel> {
        return UsuariosRepository.obtenerUsuarios()
    }

    /**
     * Método para eliminar un usuario
     */
    fun eliminarUsuario(email: String) {
        val deletionLiveData = UsuariosRepository.eliminarUsuario(email)

        deletionLiveData.observeForever { isSuccess ->
            _eliminacionStatus.postValue(isSuccess)
        }
    }
}