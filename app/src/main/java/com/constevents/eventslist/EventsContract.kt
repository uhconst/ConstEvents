package com.constevents.eventslist

import com.constevents.model.Event

interface EventsContract {
    interface Presenter {
        fun onResume()
    }

    interface View {
        var loading: Boolean
        fun setEvents(events: List<Event>)
    }
}
