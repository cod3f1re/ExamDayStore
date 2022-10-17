package com.cod3f1re.examdaystore.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cod3f1re.examdaystore.R
import com.cod3f1re.examdaystore.databinding.ItemLocationsBinding
import com.cod3f1re.examdaystore.model.entities.LocationsItem

/**
 * @author Abraham Rivera Rojas
 * @since 15/10/2022
 */
class LocationAdapter (
    var locationList: MutableList<LocationsItem>):
    ListAdapter<LocationsItem, LocationAdapter.LocationsViewHolder>(ProgramDiffUtilCallback) {
    var onItemClick: ((LocationsItem) -> Unit)? = null

    object ProgramDiffUtilCallback : DiffUtil.ItemCallback<LocationsItem>() {
        override fun areItemsTheSame(
            oldItem: LocationsItem,
            newItem: LocationsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocationsItem,
            newItem: LocationsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class LocationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLocationsBinding.bind(itemView)

        init {
            binding.cardLocation.setOnClickListener {
                onItemClick?.invoke(locationList[adapterPosition])
            }
        }

        fun bind(location: LocationsItem) {
            // Se indica el nuevo texto
            binding.tvLatitude.text = location.latitude
            binding.tvLongitude.text = location.longitude
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locations, parent, false)
        return LocationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val product=getItem(position)
        holder.bind(product)
    }

}

