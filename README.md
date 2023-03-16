# SneakerShip


## Dependencies

The following dependencies have been used to build this app:
- Kotlin Flow + Coroutines for background processing
- Retrofit for network calls
- Lifecycle dependencies for ViewModel and LiveData
- Jetpack Navigation Components for easy fragment navigation
- Glide for image loading
- Dagger Hilt for dependency injection
- com.github.Kwasow:BottomNavigationCircles-Android:1.2 for bottom navigation bar
- Room for offline persistence
- JUnit and mockk for unit testing


## Quick Walkthrough

The app has three main feature screens:
- Home 
- Cart
- Details

Each feature is divided into four parts:
- Data
- DI
- Domain
- Presentation

### Data

This section is further divided into 'local', 'remote', and 'repository' packages. These packages handle fetching data from the web server and persisting it in the local database. The 'repository' package holds the implementation of the repository.

### Domain

This section houses the API models and the repository interface.

### DI

The DI section contains Hilt modules, which declare dependencies needed in the specific feature.

### Presentation

The Presentation section contains files related to UI.

The app fetches data from a dummy JSON data hosted on Firebase Cloud Storage due to the unavailability of a proper API.

## Possible Improvements

Due to time constraints, certain improvements could not be implemented in the app. These improvements include:
- Using NetworkBoundResource as a one-stop solution to handling network requests and caching them in the local database. The current implementation uses a simpler approach that calls the network API safely.
- Creating a custom implementation of the Bottom Nav Bar instead of using a third-party library.
