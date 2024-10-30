package com.example.Model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.Model.Clientes
import com.example.Model.Productos
import com.example.Model.Ventas


data class VentasConClienteYProducto(
    @Embedded val venta: Ventas,
    @Relation(
        parentColumn = "clienteId",
        entityColumn = "clienteId"
    )
    val cliente: Clientes,
    @Relation(
        parentColumn = "productoId",
        entityColumn = "productoId"
    )
    val producto: Productos
)
