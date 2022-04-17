package ir.omidrezabagherian.userapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.omidrezabagherian.userapp.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase {
             return INSTANCE ?: synchronized(this){
                val db = Room.databaseBuilder(context,
                UserDatabase::class.java,
                "user-database").build()
                INSTANCE = db
                 return db as UserDatabase
             }
        }
    }
}