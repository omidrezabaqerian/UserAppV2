package ir.omidrezabagherian.userapp.data

import ir.omidrezabagherian.userapp.data.local.LocalDataSource
import ir.omidrezabagherian.userapp.data.local.UserDatabase
import ir.omidrezabagherian.userapp.ui.BaseApplication

class ServiceLocator {
    companion object{
        private val database = UserDatabase.getDatabase(BaseApplication.getAppContext())
        private val localDatasource = LocalDataSource(database as UserDatabase)
        private val userRepository = UserRepository(localDatasource)

        fun getUserRepository(): UserRepository {
            return userRepository
        }
    }
}