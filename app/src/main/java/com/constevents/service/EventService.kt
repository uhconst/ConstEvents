package com.constevents.service

import com.constevents.model.Event
import io.reactivex.Single

interface EventService {
    fun getEvents(): Single<List<Event>>
}
