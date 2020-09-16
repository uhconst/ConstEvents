package com.constevents.eventslist

import com.constevents.model.Event
import com.constevents.service.EventService
import io.reactivex.Scheduler

class EventsPresenter (
    private val view: EventsContract.View,
    private val eventService: EventService,
    private val state: EventsSortState,
    private val main: Scheduler
): EventsContract.Presenter {

    override fun onResume() {
        view.loading = true

        eventService.getEvents()
            .observeOn(main)
            .doFinally { view.loading = false }
            .subscribe(::success, ::error)

//        state.events = events.event
//        setEventsList()
    }

    private fun success(events: List<Event>) {
        view.setEvents(events)
    }

    private fun error(throwable: Throwable) {
        //todo show toast
    }
}