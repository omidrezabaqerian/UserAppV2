package ir.omidrezabagherian.userapp.data

import ir.omidrezabagherian.userapp.data.local.LocalDataSource
import ir.omidrezabagherian.userapp.model.User

class UserRepository(private val localDataSource: LocalDataSource) {
    fun insertUserList(users: List<User>) {
        localDataSource.insertUserList(users)
    }
}