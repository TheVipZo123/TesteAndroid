package com.example.testeandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

@Database(entities = [Task::class, User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "todo-db")
                .addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Usuario Default
                        CoroutineScope(Dispatchers.IO).launch {
                            val instance = getInstance(context)
                            val uDao = instance.userDao()
                            val tDao = instance.taskDao()
                            val defaultHash = sha256("1234")
                            val fluxHash = sha256("flux")
                            val flux2Hash = sha256("flux123")

                            uDao.insert(User("admin", defaultHash))
                            uDao.insert(User("flux", fluxHash))
                            uDao.insert(User("flux2", flux2Hash))
                            tDao.insert(Task(description = "Exemplo de tarefa." ))
                        }
                    }
                })
                .build()
        }
        // Criptografando
        private fun sha256(input: String): String {
            val md = MessageDigest.getInstance("SHA-256")
            val bytes = md.digest(input.toByteArray(Charsets.UTF_8))
            return bytes.joinToString("") { "%02x".format(it) }
        }
    }
}
