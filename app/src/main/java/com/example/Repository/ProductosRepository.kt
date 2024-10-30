package com.example.Repository

import com.example.DAO.ProductosDao
import com.example.Model.Productos

class ProductosRepository(private val productosDao: ProductosDao) {


    suspend fun insertarProducto(producto: Productos): Long {
        return productosDao.insertarProducto(producto)
    }


    suspend fun actualizarProducto(producto: Productos) {
        productosDao.actualizarProducto(producto)
    }


    suspend fun eliminarProducto(producto: Productos) {
        productosDao.eliminarProducto(producto)
    }


    suspend fun obtenerProductoPorId(productoId: Int): Productos? {
        return productosDao.obtenerProductoPorId(productoId)
    }


    suspend fun obtenerTodosLosProductos(): List<Productos> {
        return productosDao.obtenerTodosLosProductos()
    }


    suspend fun actualizarStock(productoId: Int, cantidad: Int) {
        productosDao.actualizarStock(productoId, cantidad)
    }

}

