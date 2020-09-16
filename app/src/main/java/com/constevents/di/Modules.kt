package com.constevents.di

import com.constevents.BuildConfig
import com.constevents.eventslist.EventsActivity
import com.constevents.interceptor.RemoteRequestInterceptor
import com.constevents.interceptor.RxRemoteErrorInterceptor
import com.constevents.service.EventApi
import com.constevents.service.EventService
import com.constevents.service.EventServiceImpl
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class RxSchedulerType {
    IO,
    MAIN,
    COMPUTATION
}

/**
 * Object holder for DI modules
 */
object Modules {
    /**
     * List of scoped modules - scoped modules can only be used from a certain context
     */
    val featureModules = listOf(
        EventsActivity.activityModule
    )

    val servicesModules = listOf(
        module {
            factory { RxRemoteErrorInterceptor() }

            factory { RemoteRequestInterceptor(BuildConfig.API_KEY) }

            single {
                EventApi.createEventApi(
                    BuildConfig.API_URL,
                    get(),
                    get()
                )
            }
            factory<EventService> {
                EventServiceImpl(
                    api = get()
                )
            }
        }
    )

    val rxSchedulersModule = module {
        factory<Scheduler>(named(RxSchedulerType.MAIN)) { AndroidSchedulers.mainThread() }
        factory(named(RxSchedulerType.IO)) { Schedulers.io() }
        factory(named(RxSchedulerType.COMPUTATION)) { Schedulers.computation() }
    }
}
