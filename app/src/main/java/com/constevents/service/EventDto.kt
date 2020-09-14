package com.constevents.service

import com.constevents.model.Event
import com.google.gson.annotations.SerializedName

sealed class EventDto {
    /**
     * Represent a single Response.
     */
    data class Response(
        @SerializedName("_embedded") val embedded: EmbeddedResponse
    )

    /**
     * Represent a single EmbeddedResponse.
     */
    data class EmbeddedResponse(
        val events: List<EventResponse>
    )

    /**
     * Represent a single EventResponse.
     */
    data class EventResponse(
        val id: String,
        val name: String,
        val dates: DatesResponse,
        val images: List<ImageResponse>,
        val _embedded: EmbeddedVenuesResponse
    ) {
        /**
         * Convert from [Response] to [Event]
         */
        fun toEvent() = Event(
            id = id,
            name = name,
            imageUrl = images.first().url,
            dates = dates.start.localDate,
            venue = _embedded.venues.first().name
        )
    }

    /**
     * Represent a single DatesResponse.
     */
    data class DatesResponse(
        val start: StartResponse
    )

    /**
     * Represent a single StartResponse.
     */
    data class StartResponse(
        val localDate: String
    )

    /**
     * Represent a single ImageResponse.
     */
    data class ImageResponse(
        val url: String
    )

    /**
     * Represent a single EmbeddedVenuesResponse.
     */
    data class EmbeddedVenuesResponse(
        val venues: List<VenueResponse>
    )

    /**
     * Represent a single VenueResponse.
     */
    data class VenueResponse(
        val name: String
    )
}
