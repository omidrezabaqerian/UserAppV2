package ir.omidrezabagherian.userapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ir.omidrezabagherian.userapp.data.UserRepository
import ir.omidrezabagherian.userapp.data.network.NetworkManager
import ir.omidrezabagherian.userapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class SearchViewModel(var userRepository: UserRepository) : ViewModel() {
    private val _searchResponse = MediatorLiveData<List<User>>()
    val searchResponse: LiveData<List<User>> = _searchResponse
    private val _searchThrowable = MediatorLiveData<String>()
    val searchThrowable: LiveData<String> = _searchThrowable

    fun showUserList() {
        NetworkManager.userService.getUser().enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _searchResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _searchThrowable.postValue(t.message)
            }
        })
    }

    fun searchUserList(nationalCode: String = "", firstName: String = "", lastName: String = "") {
        if (nationalCode != "" && firstName != "") {
            NetworkManager.userService.getUserByNationalCodeAndFirstName(nationalCode, firstName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (nationalCode != "" && firstName != "" && lastName != "") {
            NetworkManager.userService.getUserByNationalCodeAndFirstNameAndLastName(nationalCode, firstName, lastName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (nationalCode != "" && lastName != "") {
            NetworkManager.userService.getUserByNationalCodeAndLastName(nationalCode, lastName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (firstName != "" && lastName != "") {
            NetworkManager.userService.getUserByFirstNameAndLastName(firstName, lastName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (nationalCode != "") {
            NetworkManager.userService.getUserByNationalCode(nationalCode)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (firstName != "") {
            NetworkManager.userService.getUserByFirstName(firstName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        } else if (lastName != "") {
            NetworkManager.userService.getUserByLastName(lastName)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        _searchResponse.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        _searchThrowable.postValue(t.message)
                    }

                })
        }
    }

    fun saveUserList(users:List<User>){
        thread {
            userRepository.insertUserList(users)
        }
    }

}