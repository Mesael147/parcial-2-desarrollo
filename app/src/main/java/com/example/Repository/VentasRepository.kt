package com.example.Repository

import com.example.Model.VentasConClienteYProducto
import com.example.DAO.VentasDao
import com.example.Model.Ventas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VentasRepository(private val ventaDao: VentasDao) {


    /*  suspend fun obtenerTodasLasVentas(): List<Ventas> = withContext(Dispatchers.IO) {
        ventaDao.obtenerTodasLasVentas()
    }*/
    suspend fun obtenerVentasConClienteYProducto(): List<VentasConClienteYProducto> {
        return ventaDao.obtenerVentasConClienteYProducto()
    }


    suspend fun insertarVenta(venta: Ventas) = withContext(Dispatchers.IO) {
        ventaDao.insertarVenta(venta)
    }


}
