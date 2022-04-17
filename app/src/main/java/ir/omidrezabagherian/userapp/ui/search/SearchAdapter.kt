package ir.omidrezabagherian.userapp.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.omidrezabagherian.userapp.databinding.ItemRecyclerviewBinding
import ir.omidrezabagherian.userapp.model.User

class SearchAdapter(val context: Context, val userList: MutableList<User>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    lateinit var itemClick: ItemClick

    inner class SearchViewHolder(private var itemRecyclerViewBinding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(itemRecyclerViewBinding.root) {
        fun bind(pos: Int) {
            itemRecyclerViewBinding.textNationalCode.text = userList[pos].nationalCode
            itemRecyclerViewBinding.textFirstName.text = userList[pos].firstName
            itemRecyclerViewBinding.textLastName.text = userList[pos].lastName
        }
        init {
            itemRecyclerViewBinding.root.setOnClickListener {
                itemClick.viewClick(adapterPosition,itemRecyclerViewBinding.root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val viewHolder = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = userList.size

    interface ItemClick{
        fun viewClick(position: Int,v: View?)
    }

    fun setItemUserClick(itemClick:ItemClick){
        this.itemClick = itemClick
    }
}