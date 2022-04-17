package ir.omidrezabagherian.userapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String
)