package com.tryden.simplenfl.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.tryden.simplenfl.databinding.ModelScoresCarouselDateItemBinding
import com.tryden.simplenfl.domain.models.calendar.UiCalendar


class HorizontalWeekMenuAdapter(
    private val onWeekSelected: (String) -> Unit
): RecyclerView.Adapter<HorizontalWeekMenuAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val binding: ModelScoresCarouselDateItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<UiCalendar.UiWeek>() {
        override fun areItemsTheSame(oldItem: UiCalendar.UiWeek, newItem: UiCalendar.UiWeek) : Boolean {
            return oldItem.range == newItem.range
        }

        override fun areContentsTheSame(oldItem: UiCalendar.UiWeek, newItem: UiCalendar.UiWeek) : Boolean {
            return oldItem == newItem
        }
    } // end of diffCallback

    val differ = AsyncListDiffer(this, diffCallback)

    var weeks: MutableList<UiCalendar.UiWeek>
        get() = differ.currentList
        set(value) {differ.submitList(value)}


    var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ModelScoresCarouselDateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.apply {
            val week = weeks[position]
            labelTextView.text = week.shortLabel
            datesTextView.text = week.formattedDates
            root.setOnClickListener {
                onWeekSelected(weeks[position].range)
                selectedIndex = position
                notifyDataSetChanged()
            }

            if (selectedIndex == position) {
                holder.binding.labelTextView.setTextColor(Color.WHITE)
                holder.binding.datesTextView.setTextColor(Color.WHITE)
            } else {
                holder.binding.labelTextView.setTextColor(Color.GRAY)
                holder.binding.datesTextView.setTextColor(Color.GRAY)
            }

        }
    }

    override fun getItemCount(): Int {
        return weeks.size
    }
}