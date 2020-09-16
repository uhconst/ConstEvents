package com.constevents.eventslist

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.constevents.model.Event
import com.constevents.service.EventService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class EventsPresenterTest {

    private val eventsList = listOf(
        Event(
            id = "2",
            name = "B",
            dates = "02/02/2222",
            venue = "Hyde Park 2"
        ),
        Event(
            id = "1",
            name = "A",
            dates = "01/01/1111",
            venue = "Hyde Park 2"
        )
    )

    private val mockView: EventsContract.View = mockk(relaxUnitFun = true)

    private val mockService: EventService = mockk(relaxed = true) {
        every { getEvents() } returns Single.just(eventsList)
    }

    private var mockState = EventsState()

    private lateinit var presenter: EventsPresenter

    @Before
    fun setUp() {
        initPresenter()
    }

    @Test
    fun `onResume loads data with success when not already loaded`() {

        presenter.onResume()

        verifySequence {
            mockService.getEvents()
            mockView.setEvents(eventsList)
        }
        assertThat(mockState.events).isEqualTo(eventsList)
        assertThat(mockState.isLoaded).isTrue()
    }

    @Test
    fun `onResume loads data with failure when not already loaded`() {
        every { mockService.getEvents() } returns Single.error(Throwable())

        presenter.onResume()

        verifySequence {
            mockService.getEvents()
            mockView.showError()
        }
        assertThat(mockState.events).isEmpty()
        assertThat(mockState.isLoaded).isFalse()
    }

    @Test
    fun `sort by name sets view events sorted`() {
        initPresenter(true)

        val eventsSorted = eventsList.sortedBy { it.name }

        presenter.sortByName()

        verifySequence {
            mockView.setEvents(eventsSorted)
        }
    }

    private fun initPresenter(isStateLoaded: Boolean = false) {
        if (isStateLoaded) {
            mockState.apply {
                isLoaded = true
                events = eventsList
            }
        }

        presenter = EventsPresenter(
            view = mockView,
            eventService = mockService,
            state = mockState,
            main = Schedulers.trampoline()
        )
    }
}
