package com.example.ctrlbee.presentation.fragment.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ctrlbee.R
import com.example.ctrlbee.databinding.ItemRoomsBinding
import com.example.ctrlbee.domain.model.Room
import com.example.ctrlbee.domain.model.ToDoListItem

class RoomsAdapter(private val roomList: List<Room>) : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_rooms, parent, false)
        return RoomsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        val currentItem = roomList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = roomList.size

    class RoomsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemRoomsBinding.bind(item)
        fun bind(room: Room) = with(binding) {
            room1Name.text = room.name
        }
    }
}