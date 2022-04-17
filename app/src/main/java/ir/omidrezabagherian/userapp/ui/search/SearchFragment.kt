package ir.omidrezabagherian.userapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.omidrezabagherian.userapp.R
import ir.omidrezabagherian.userapp.databinding.FragmentSearchBinding
import ir.omidrezabagherian.userapp.model.User

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var searchBinding: FragmentSearchBinding
    private val viewModelFactory = SearchViewModelFactory()
    private var viewModel: SearchViewModel? = null
    private lateinit var searchAdapter: SearchAdapter
    private var listUsers = mutableListOf<User>()

    val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding = FragmentSearchBinding.bind(view)

        viewModel!!.showUserList()

        viewModel!!.searchResponse.observe(viewLifecycleOwner, Observer {
            listUsers.clear()
            listUsers.addAll(it)
            searchAdapter.notifyDataSetChanged()
            viewModel!!.saveUserList(it)
        })

        searchAdapter = SearchAdapter(view.context, listUsers)
        searchBinding.recyclerViewSearch.layoutManager = LinearLayoutManager(view.context)
        searchBinding.recyclerViewSearch.adapter = searchAdapter

        searchAdapter.setItemUserClick(object : SearchAdapter.ItemClick {
            override fun viewClick(position: Int, v: View?) {
                navController.navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                        listUsers[position].nationalCode,
                        listUsers[position].firstName,
                        listUsers[position].lastName
                    )
                )
            }
        })

        searchBinding.searchViewSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                val words = query?.split(" ")

                when (words?.size) {
                    1 -> {
                        if (words?.get(0) != "") {
                            viewModel!!.searchUserList(
                                nationalCode = words?.get(0).toString()
                            )
                        } else if (words?.get(1) != "") {
                            viewModel!!.searchUserList(
                                firstName = words?.get(1).toString()
                            )
                        } else if (words?.get(2) != "") {
                            viewModel!!.searchUserList(
                                lastName = words?.get(2).toString()
                            )
                        }
                    }
                    2 -> {
                        if (words?.get(0) != "" && words?.get(1) != "") {
                            viewModel!!.searchUserList(
                                nationalCode = words?.get(0).toString(),
                                firstName = words?.get(1).toString()
                            )
                        } else if (words?.get(0) != "" && words?.get(2) != "") {
                            viewModel!!.searchUserList(
                                nationalCode = words?.get(0).toString(),
                                lastName = words?.get(2).toString()
                            )
                        } else if (words?.get(1) != "" && words?.get(2) != "") {
                            viewModel!!.searchUserList(
                                firstName = words?.get(1).toString(),
                                lastName = words?.get(2).toString()
                            )
                        }
                    }
                    3 -> {
                        if (words?.get(0) != "" && words?.get(1) != "" && words?.get(2) != "") {
                            viewModel!!.searchUserList(
                                nationalCode = words?.get(0).toString(),
                                firstName = words?.get(1).toString(),
                                lastName = words?.get(2).toString()
                            )
                        }
                    }

                }

                viewModel!!.searchResponse.observe(viewLifecycleOwner, Observer {
                    listUsers.clear()
                    listUsers.addAll(it)
                    searchAdapter.notifyDataSetChanged()
                })

                searchAdapter = SearchAdapter(view.context, listUsers)
                searchBinding.recyclerViewSearch.layoutManager = LinearLayoutManager(view.context)
                searchBinding.recyclerViewSearch.adapter = searchAdapter

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel!!.showUserList()

                viewModel!!.searchResponse.observe(viewLifecycleOwner, Observer {
                    listUsers.clear()
                    listUsers.addAll(it)
                    searchAdapter.notifyDataSetChanged()
                })

                searchAdapter = SearchAdapter(view.context, listUsers)
                searchBinding.recyclerViewSearch.layoutManager = LinearLayoutManager(view.context)
                searchBinding.recyclerViewSearch.adapter = searchAdapter

                return true
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(SearchViewModel::class.java)
    }

}