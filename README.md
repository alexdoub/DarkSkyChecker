# Dark Sky Checker #

This app lets you check the weather forecast using the Dark Sky API

## Overview ##

The app is designed around Dagger, RxJava & MVP paradigms. 

#### Usage ####

I was planning on having it read from your devices actual GPS, but ended up just simulating GPS locations all around the world instead, as it's more reliable and it shows off the features of the app a bit better. 

First, select a location (default is Alaska), then click Refresh Forecast to get the forecast for your selected location. You can select different locations while maintaining the integrity of the ForecastModel.

The components are neatly separated to better facilitate expansion of the app. For example, we could add a new screen to select the location manually, a Google Maps activity to select a position off of a map, or even a screen that shows several different forecasts at once.


#### Components ####

Here is a high level overview of the project. It is centered around the main components.

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
│   │       └─────────────────────LocationManager─>─┐
│   └── NetworkModule                               │
│           └─────────────────────APIClient─>───────┤
│                                                   │
ForecastComponent                                   │
    └── ForecastModule                              │
            └──────────────────────────────────ForecastModel
```

* App Module - Mostly empty, contains app context
* Data Module - Stores user data (last selected location)
* Network Module - Does network calls over API. It has an API client to hold the API key. In theory we could add multiple clients with different API keys, or if there were separate endpoints with different URLs we could make one ApiClient to represent each one
* Location Module - Chooses new selected locations (currently simulates fake GPS coordinates)
* Forecast Module - Maintains model of a forecast for a location. Shared amongst ForecastActivity & ForecastDetailActivity


