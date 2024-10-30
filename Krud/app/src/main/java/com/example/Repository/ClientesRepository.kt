package com.example.Repository

import com.example.DAO.ClientesDao
import com.example.Model.Clientes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientesRepository(private val clienteDao: ClientesDao) {


    suspend fun obtenerTodosLosClientes(): List<Clientes> = withContext(Dispatchers.IO) {
        clienteDao.obtenerTodosLosClientes()
    }


    suspend fun insertarCliente(cliente: Clientes) = withContext(Dispatchers.IO) {
        clienteDao.insertarCliente(cliente)
    }


    suspend fun actualizarCliente(cliente: Clientes) = withContext(Dispatchers.IO) {
        clienteDao.actualizarCliente(cliente)
    }



    suspend fun eliminarCliente(cliente: Clientes) = withContext(Dispatchers.IO) {
        clienteDao.eliminarCliente(cliente)
    }
    suspend fun obtenerClientePorId(clienteId: Int): Clientes? {
        return clienteDao.obtenerClientePorId(clienteId)
    }
}
