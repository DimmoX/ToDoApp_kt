package com.example.todoapp.data.models

/**
 * Clase que representa un modelo de tareas
 * @property id - Identificador de la tarea
 * @property titulo - Título de la tarea
 * @property descripcion - Descripción de la tarea
 * @property fecha - Fecha de la tarea
 * @property hora - Hora de la tarea
 * @property status - Estado de la tarea (true = pendiente o false = finalizado)
 */
data class TareasModel(
    var id: String = "",
    val titulo: String = "",
    val descripcion: String = "",
    val fecha: String = "",
    val hora: String = "",
    var status: Boolean = true
)
