package com.constevents.eventslist

import com.constevents.model.Event

interface EventsContract {
    interface Presenter {
        fun onResume()
        fun onDestroy()
        fun sortByName()
    }

    interface View {
        fun setEvents(events: List<Event>)
        fun showError()
    }
}
