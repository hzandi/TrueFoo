Truecaller Android Technical Assignment

## MVVM, Clean Architecture 
An Android Clean Architecture app written in Kotlin.

## Modules:
* **app** - It uses data module and related components to Android Framework. MVVM with ViewModels exposing LiveData that the UI consume.
* **data** - The data layer implements the repository interface. This layer provide a single source of truth for data.
* **buildSrc** - Kotlin DSL, Dependency management for better reusability and easy maintenance.

## Design Patterns
* repository pattern in data module
* adapter pattern in retrofit converter to fetch html content 
* facade pattern in Html content parser

## Tech Stack:
* [Kotlin coroutines][1] A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* [ViewModel][12] - Stores UI-related data that isn't destroyed on UI changes. 
* [LiveData][4] for reactive style programming (from VM to UI).
* [Hilt][5] Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
* [Retrofit][6] for REST api communication.
* [OkHttp][14] HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
* [Gson][13] used to convert Java Objects into their JSON representation and vice versa.
* [Timber][7] for logging.
* [Mockito-Kotlin][9] for mocking in tests.
* [MockWebServer][10] for Instrumentation tests.
* [AndroidX Test Library][11] for providing JUnit4

[1]:  https://kotlinlang.org/docs/coroutines-overview.html
[4]:  https://developer.android.com/topic/libraries/architecture/livedata
[5]:  https://developer.android.com/training/dependency-injection/hilt-android
[6]:  https://github.com/square/retrofit
[7]:  https://github.com/JakeWharton/timber
[9]:  https://github.com/nhaarman/mockito-kotlin
[10]: https://github.com/square/okhttp/tree/master/mockwebserver
[11]: https://github.com/android/android-test
[12]: https://developer.android.com/topic/libraries/architecture/viewmodel
[13]: https://github.com/google/gson
[14]: http://square.github.io/okhttp/

## Testing
App module covered with moderate test coverage 

## TODO
1. Instrumentation test cases.
2. More unit test cases
