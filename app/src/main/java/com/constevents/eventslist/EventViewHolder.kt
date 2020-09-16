package com.constevents.eventslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.constevents.R
import com.constevents.model.Event
import kotlinx.android.synthetic.main.viewholder_event.view.*

class EventViewHolder constructor(
    rootView: View
) : RecyclerView.ViewHolder(rootView) {

    companion object {
        const val layout = R.layout.viewholder_event
    }

    fun bind(event: Event) {
        itemView.apply {
            viewholder_event_label.text = event.name
            viewholder_venue_label.text = event.venue
            viewholder_date_label.text = event.dates
        }
    }
}
