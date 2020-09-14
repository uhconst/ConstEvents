package com.constevents.service

import io.reactivex.Single
import retrofit2.http.GET

interface EventApi {
    /**
     * Attempts to get [EventDto.Response].
     */
    @GET("discovery/v2/events")
    fun getEvents(): Single<EventDto.Response>
}
