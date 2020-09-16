package com.constevents.eventslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.constevents.R
import com.constevents.di.RxSchedulerType
import com.constevents.model.Event
import kotlinx.android.synthetic.main.events_activity.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class EventsActivity : AppCompatActivity(R.layout.events_activity), EventsContract.View {

    private val presenter: EventsContract.Presenter by currentScope.inject()

    override var loading: Boolean
        get() = false
        set(value) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycler_events.adapter = EventAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun setEvents(events: List<Event>) {
        (recycler_events.adapter as EventAdapter).setEvents(events)
    }

    companion object {
        /**
         * DI module declaration
         */
        @JvmStatic
        val activityModule = module {
            scope(named<EventsActivity>()) {
                scoped<EventsContract.View> { getSource() }
                scoped<EventsContract.Presenter> {
                    EventsPresenter(
                        view = get(),
                        eventService = get(),
                        state = get(),
                        main = get(named(RxSchedulerType.MAIN))
                    )
                }
                viewModel { EventsSortState() }
            }
        }
    }
}
