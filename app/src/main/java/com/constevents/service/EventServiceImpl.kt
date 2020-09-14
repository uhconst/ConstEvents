package com.constevents.service

import com.constevents.model.Event
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class EventServiceImpl(
    private val api: EventApi
) : EventService {

    override fun getEvents(): Single<List<Event>> =
        api.getEvents()
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it.embedded.events }
            .map { eventResponse ->
                eventResponse.toEvent()
            }
            .toList()
}
