package com.constevents.eventslist

import androidx.lifecycle.ViewModel
import com.constevents.model.Event

class EventsState(
    var isLoaded: Boolean = false,
    var events: List<Event> = emptyList()
) : ViewModel()
