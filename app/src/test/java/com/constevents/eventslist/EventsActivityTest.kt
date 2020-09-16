package com.constevents.eventslist

import androidx.test.core.app.ActivityScenario.launch
import com.constevents.ConstEventsApplication
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ConstEventsApplication::class)
class EventsActivityTest : KoinTest {

    @MockK
    lateinit var mockPresenter: EventsContract.Presenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        val activityModule = module {
            scope(named<EventsActivity>()) {
                scoped<EventsContract.View> { getSource() }
                scoped { mockPresenter }
            }
        }
        startKoin { modules(activityModule) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `onResume calls presenter`() {
        launch<EventsActivity>(EventsActivity::class.java)

        verify { mockPresenter.onResume() }
    }
}
