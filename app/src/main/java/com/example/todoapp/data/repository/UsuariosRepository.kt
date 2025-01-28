package com.example.todoapp.data.repository

import com.example.todoapp.data.models.UsuariosModel

class UsuariosRepository {

    // Lista en memoria que almacena los usuarios
    private val usuarios: MutableList<UsuariosModel> = mutableListOf()

    init {
        // Asegúrate de agregar los usuarios predeterminados si la lista está vacía
        if (usuarios.isEmpty()) {
            agregarUsuariosPredeterminados()
        }
    }

    private fun agregarUsuariosPredeterminados() {
        val usuariosPredeterminados = listOf(
            UsuariosModel(1, "Juan", "Perez", "jperez@test.cl", "ABCD1234"),
            UsuariosModel(2, "Ana", "Gonzalez", "agonzalez@test.cl", "1234ABCD"),
            UsuariosModel(3, "Carlos", "Martinez", "cmartinez@test.cl", "1234CDAB"),
            UsuariosModel(4, "Maria", "Lopez", "mlopez@test.cl", "AB12CD34"),
            UsuariosModel(5, "Luis", "Fernandez", "lfernandez@test.cl", "XYZ98765")
        )
        usuarios.addAll(usuariosPredeterminados)
    }

    fun agregarUsuario(usuarioModel: UsuariosModel) {
        usuarios.add(usuarioModel)
    }

    fun obtenerUsuarios(): List<UsuariosModel> {
        return usuarios
    }

    fun obtenerUsuario(email: String, passwd: String): UsuariosModel? {
        return usuarios.find { it.email == email && it.passwd == passwd }
    }

    fun eliminarUsuario(email: String): Boolean {
        val usuario = usuarios.find { it.email == email }
        return if (usuario != null) {
            usuarios.remove(usuario)
            true
        } else {
            false
        }
    }
}
