package ir.omidrezabagherian.userapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import ir.omidrezabagherian.userapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = IGNORE)
    fun insertUserList(vararg userList: User)

    @Insert
    fun insertUser(user: User)
}