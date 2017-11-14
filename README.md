# Dark Sky Checker #

This app lets you check the weather forecast using the Dark Sky API.

The project is designed with Dagger 2, RxJava & MVP paradigms. 

## Overview & Notes ##

I was planning on having it read from your devices actual GPS, but ended up just simulating GPS locations all around the world instead, as it's more reliable and it shows off the features of the app a bit better. 

The components are neatly separated to better facilitate expansion of the app. For example, we could add a new screen to select the location manually, a Google Maps activity to select a position off of a map, or even a screen that shows several different forecasts at once.

Just a note, I think I could further separate the LocationModule into 2 parts, one that manages the simulated GPS and another that manages location selection (and put that into the DataModule), however I feel the project is already getting unnecessarily complex for what it's supposed to be. If I did that, the forecast model could be further streamlined so it automatically fetches the forecast once the ForecastModels' location is set.

## Usage ##

First, select a location (default is Alaska), then click Refresh Forecast to get the forecast for your selected location. You can select different locations while maintaining the integrity of the ForecastModel.

## Components ##

Here is a high level overview of the project. It is centered around the main dagger components.

```
Dagger Components
    │
    ├──AppComponent
    │   ├── AppModule 
    │   │       └──App Context─>┐
    │   │                       │
    │   ├── DataModule          │
    │   │       └───────SharedPreferences─>┐
    │   ├── LocationModule                 │
    │   │       └──────────────────LocationManager─>───┐
    │   └── NetworkModule                              │
    │           └───────APIClient─>────────────────────┤
    │                                                  │
    └── ForecastComponent                              │
            └── ForecastModule                         │
                    └─────────────────────────────ForecastModel
```

* App Module - Contains app context
* Data Module - Persists user data by saving last selected location
* Network Module - Does network calls over API. It has an API client to hold the API key. In theory we could add multiple clients with different API keys, or if there were separate endpoints with different URLs we could make one ApiClient to represent each one
* Location Module - Chooses new selected locations (currently simulates fake GPS coordinates)
* Forecast Module - Maintains model of a forecast for a location. Shared among ForecastActivity & ForecastDetailActivity


## Activities & Flow ##

The activities follow a MVP paradigm where both activities share the same Forecast Model

```
   ...
    ↓
ForecastModel 
(Injects into)
    ↓
    │
    ├──ForecastActivity
    │       ├──ForecastView─┐
    │       │               ↓
    │       └────────ForecastPresenter
    │
    │
    └──ForecastDetailActivity
            ├──ForecastDetailView─┐
            │                   　　↓
            └────────ForecastDetailPresenter
```

The view binds to API model data (provided by the ForecastModel)
The presenter hooks up the forecast model to the view via Rx subscriptions
The presenter responds to view events via Rx subscriptions and triggers model events
The model fetches forecast data relating to its current location

Example data flow for setting location:
1) User taps update location
2) Model subscribes to single GPS update
3) Location manager simulates GPS update
4) Model updates selected location
5) View binds with new selected location


Example data flow for getting forecast for selected location:
1) User taps refresh forecast
2) Presenter propagates event to model
3) Model fetches forecast for selected location
4) View listens to model update via presenter hookup & binds new forecast data


