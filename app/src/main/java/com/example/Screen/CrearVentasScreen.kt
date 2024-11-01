package com.example.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.Model.Clientes
import com.example.Model.Productos
import com.example.Model.Ventas
import com.example.Repository.ClientesRepository
import com.example.Repository.ProductosRepository
import com.example.Repository.VentasRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CrearVentasScreen(
    navController: NavController,
    ventasRepository: VentasRepository,
    clientesRepository: ClientesRepository,
    productosRepository: ProductosRepository
) {
    val coroutineScope = rememberCoroutineScope()

    var selectedCliente by remember { mutableStateOf<Clientes?>(null) }
    var selectedProducto by remember { mutableStateOf<Productos?>(null) }
    var cantidad by remember { mutableStateOf("") }
    var fechaVenta by remember { mutableStateOf("") }
    var clientesList by remember { mutableStateOf<List<Clientes>>(emptyList()) }
    var productosList by remember { mutableStateOf<List<Productos>>(emptyList()) }

    LaunchedEffect(Unit) {
        clientesList = clientesRepository.obtenerTodosLosClientes()
        productosList = productosRepository.obtenerTodosLosProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text(
            text = "Registrar Nueva Venta",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF795548),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Seleccionar Cliente", color = Color(0xFF6D4C41), fontWeight = FontWeight.Bold)
                clientesList.forEach { cliente ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                if (selectedCliente == cliente) Color(0xFFFFCCBC) else Color.Transparent
                            )
                            .clickable { selectedCliente = cliente }
                            .padding(8.dp)
                    ) {
                        Text(cliente.nombre)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Text("Seleccionar Producto", color = Color(0xFF6D4C41), fontWeight = FontWeight.Bold)
                productosList.forEach { producto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                if (selectedProducto == producto) Color(0xFFFFCCBC) else Color.Transparent)
                            .clickable { selectedProducto = producto }
                            .padding(8.dp)
                    ) {
                        Text(producto.nombre)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = fechaVenta,
                    onValueChange = { fechaVenta = it },
                    label = { Text("Fecha de Venta (dd/MM/yyyy)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        if (selectedCliente != null && selectedProducto != null && cantidad.isNotBlank() && fechaVenta.isNotBlank()) {
                            coroutineScope.launch {
                                val parsedDate = try {
                                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(fechaVenta) ?: Date()
                                } catch (e: Exception) {
                                    Date()
                                }

                                val nuevaVenta = Ventas(
                                    clienteId = selectedCliente!!.clienteId,
                                    productoId = selectedProducto!!.productoId,
                                    cantidad = cantidad.toInt(),
                                    fecha = parsedDate
                                )
                                ventasRepository.insertarVenta(nuevaVenta)
                                navController.popBackStack()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Venta")
                }
            }
        }
    }
}



