package com.constevents.eventslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.constevents.extensions.inflate
import com.constevents.model.Event

class EventAdapter : RecyclerView.Adapter<EventViewHolder>() {

    private var events = listOf<Event>()

    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(parent.inflate(EventViewHolder.layout, false))

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setEvents(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Event = events[position]

}
