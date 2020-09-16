package com.constevents.eventslist

import android.util.Log
import com.constevents.model.Event
import com.constevents.service.EventService
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

class EventsPresenter(
    private val view: EventsContract.View,
    private val eventService: EventService,
    private val state: EventsState,
    private val main: Scheduler
) : EventsContract.Presenter {

    private var disposable: Disposable? = null

    /**
     * Loads the data from the API if necessary.
     * then binds the data to use view.
     */
    override fun onResume() {
        if (state.isLoaded) {
            view.setEvents(state.events)
        } else {
            loadEvents()
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
    }

    override fun sortByName() {
        state.events
            .sortedBy { it.name }
            .let { view.setEvents(it) }
    }

    private fun success(eventsList: List<Event>) {
        state.apply {
            events = eventsList
            isLoaded = true
        }
        view.setEvents(eventsList)
    }

    private fun error(throwable: Throwable) {
        throwable.message?.let {
            Log.e("Error fetching data: ", it)
        }
        view.showError()
    }

    private fun loadEvents() {
        disposable?.dispose()
        disposable = eventService.getEvents()
            .observeOn(main)
            .subscribe(::success, ::error)
    }
}
