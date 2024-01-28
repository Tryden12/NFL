# NFL
NFL is a Kotlin app utilizing the [ESPN API](https://gist.github.com/nntrn/ee26cb2a0716de0947a0a4e9a157bc1c#apisv2scoreboardheader) 
to provide league news, scores, and rosters. The user can check the latest news and scores from around the league
and select favorite teams for customized news preferences.

The purpose of this app is to showcase my understanding of properly utilizing a reliable, scalable
pattern of building an app.

## Architecture
The architecture of this project is MVVM (Model View ViewModel) Clean Architecture. The app is built
in a way for easy readability by other developers. I have followed the [recommended app architecture](https://developer.android.com/topic/architecture#recommended-app-arch)
from Android.

The architecture is divided into three layers:
* UI Layer (Presentation Layer)
* Domain Layer (Mapping & Use Case)
* Data Layer (Data Source & Repository)

### UI Layer
The responsibility of the UI layer (or presentation layer) is to display the application data on the screen.
In this project, the UI layer includes UI elements such as Jetpack Compose functions, State Holders, and the UI States.
All the UI element designing is done using declarative UI (Jetpack Compose) instead of XML.

I've created UI states to ensure [Unidirectional Data Flow](https://developer.android.com/topic/architecture/ui-layer#udf).

<p align="center" width="100%">
    <img width="75%" src="https://user-images.githubusercontent.com/9715067/197088633-488dbb42-a099-42e9-a788-bcfe5ba64eef.png" alt="Unidirectional Data Flow"/>
</p>

### Domain Layer
The [domain layer](https://developer.android.com/topic/architecture/domain-layer) site between the UI and data layers.
The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels. It includes all the Use Cases of the app, for example, PlanetsListUseCase.
This is also the layer where we map response, Dto models to domain, UI models. This helps fitler out what we do not
need in the UI layer from the response.

<p align="center" width="100%">
    <img width="75%" src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-domain-overview.png" alt="Domain Layer"/>
</p>

### Data Layer
The [data layer](https://developer.android.com/topic/architecture/data-layer) contains business logic and repositories containing
data sources. In this app, DataRepository contains two data sources such as RemoteDataSource and LocalDataSource.
The RemoteDataSource fetches data from API, whereas, LocalDataSource gets data from the [Room](https://developer.android.com/training/data-storage/room) database.

I am using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) dependency injection library to provide all the dependencies I have to pass among different layers.

The image below provides context to the architecture pattern used in this app:
<p align="center" width="100%">
    <img width="75%" src="https://user-images.githubusercontent.com/9715067/197084654-a826b6b7-5069-4ba7-98cb-fed1d67b2c27.png" alt="Pattern"/>
</p>


## Libraries & Dependencies

* [AirBnB Epoxy library](https://github.com/airbnb/epoxy) - Defines UI utilizing the Epoxy RecyclerView library. 
* [Jetpack Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
* [View Binding](https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that makes it easier to write code that interacts with views.
* [Modern App Architecture](https://developer.android.com/topic/architecture) - This guide encompasses best practices and recommended architecture for building robust, high-quality apps.
* [Retrofit](https://square.github.io/retrofit/) - Interacts with the API and send network requests with OkHttp.
* [Room](https://developer.android.com/training/data-storage/room) - Create, store, and manage persistent data backed by an SQLite database.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection plays a central role in the architectural pattern used.
* [Kotlinx Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines. I used this for asynchronous programming to obtain data from the network.
* [Moshi](https://github.com/square/moshi) - JSON Parser, used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters.
* [Picasso](https://github.com/square/picasso) - A powerful image downloading and caching library for Android.
* [Flows](https://developer.android.com/kotlin/flow) - A flow is conceptually a stream of data that can be computed asynchronously. The emitted values must be of the same type.

## Screenshots:
<p align="center" width="100%">
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-teams_list.png" alt="Teams"/>
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-article.png" alt="Article"/>
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-scores_week_1.png" alt="Scores"/>
</p>

<p align="center" width="100%">
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-team_chiefs.png" alt="Team Scores"/>
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-team_chiefs_roster.png" alt="Team Roster"/>
    <img width="32%" src="https://tylerryden.com/images/simple-nfl-team_chiefs_news.png" alt="Team News"/>
</p>







  
