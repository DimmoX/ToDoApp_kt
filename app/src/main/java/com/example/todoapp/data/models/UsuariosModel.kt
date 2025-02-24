package com.example.todoapp.data.models

/**
 * Modelo de datos de Usuarios
 * @param id Identificador del usuario
 * @param nombre Nombre del usuario
 * @param apellido Apellido del usuario
 * @param email Correo electrónico del usuario
 * @param passwd Contraseña del usuario
 */
data class UsuariosModel(
    var id: String = "",
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val passwd: String = ""
){}
