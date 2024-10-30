package com.example.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.DAO.ClientesDao
import com.example.DAO.ProductosDao
import com.example.DAO.VentasDao
import com.example.Model.Clientes
import com.example.Model.DateConverter
import com.example.Model.Productos
import com.example.Model.Ventas

@Database(entities = [Productos::class, Clientes::class, Ventas::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductosDao
    abstract fun clientesDao(): ClientesDao
    abstract fun ventasDao(): VentasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


