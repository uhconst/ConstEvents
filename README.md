# Const Events
Android application using MVP to display a list of events from Ticketmaster.
Tapping sort events by name will order the events.

## Libraries used in this project
- [Gson](https://github.com/google/gson)
- [Koin](https://insert-koin.io/)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
- [Retrofit](https://square.github.io/retrofit/)
- [RxJava and RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Robolectric](https://github.com/robolectric/robolectric)
- [Constraint layout](https://developer.android.com/training/constraint-layout/index.html)
- [OkHttp](https://github.com/square/okhttp)
- [Assertk](https://github.com/willowtreeapps/assertk)

## What you should expect
- MVP architecture pattern
- DEBUG and RELEASE mode
- The events list is being fetched again every time on *Resume*
- Project hosted on [GitHub](https://github.com/uhconst/ConstEvents)

## Technical questions
- Spent 4 hours

## Tests
- EventsPresenterTest
- EventsActivityTest
- EventServiceImplTest

# Future improvements with more time
I would add more tests for the Activity. I couldn't test the Toast for example,
I would need to implement an injection for it. Also I would handle better dimens and strings, which most
are now hardcoded.
The app it self could have a better user experience and UX, but with 4 hours that's what I've achieved.

## Developed by
Uryel Constancio - [uryelhenrique.c@gmail.com](uryelhenrique.c@gmail.com)
