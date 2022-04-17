package ir.omidrezabagherian.userapp.data.local

import ir.omidrezabagherian.userapp.model.User

class LocalDataSource (private var database: UserDatabase){
    private val userDao = database.userDao()

    fun insertUserList(users: List<User>){
        userDao.insertUserList(*users.toTypedArray())
    }

}