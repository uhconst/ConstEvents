package com.constevents.service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.constevents.model.Event
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.Single
import org.junit.Test

class EventServiceImplTest {

    private val mockApi: EventApi = mockk(relaxed = true)

    private val service = EventServiceImpl(
        mockApi
    )

    private val eventId = "1"

    @Test
    fun `get events calls api and  returns a list of events`() {
        every { mockApi.getEvents() } returns Single.just(getResponseMock())

        val events = service.getEvents().test().values()

        verifySequence {
            mockApi.getEvents()
        }

        assertThat(events.flatten()).isEqualTo(expectedEventsList())
    }

    private fun expectedEventsList(): List<Event> =
        listOf(
            Event(
                id = eventId,
                name = "name",
                dates = "05/02/1993",
                venue = "National Museum"
            )
        )

    private fun getResponseMock(): EventDto.Response =
        EventDto.Response(
            EventDto.EmbeddedResponse(
                listOf(
                    getEventResponseMock()
                )
            )
        )

    private fun getEventResponseMock(): EventDto.EventResponse =
        EventDto.EventResponse(
            eventId,
            "name",
            getDatesResponseMock(),
            getImageResponseMock(),
            getEmbeddedVenuesResponseMock()
        )

    private fun getDatesResponseMock(): EventDto.DatesResponse =
        EventDto.DatesResponse(
            getStartResponseMock()
        )

    private fun getStartResponseMock(): EventDto.StartResponse =
        EventDto.StartResponse(
            "05/02/1993"
        )


    private fun getImageResponseMock(): List<EventDto.ImageResponse> =
        listOf(
            EventDto.ImageResponse(
                "/image.jpg"
            )
        )

    private fun getEmbeddedVenuesResponseMock(): EventDto.EmbeddedVenuesResponse =
        EventDto.EmbeddedVenuesResponse(
            listOf(
                EventDto.VenueResponse(
                    "National Museum"
                )
            )
        )
}
