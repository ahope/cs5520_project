# Location Updates

Demonstrates how to use the [Fused Location Provider API](https://developers.google.com/location-context/fused-location-provider) 
to get updates about a
device's location. The Fused Location Provider is part of the [Google Play
services](https://developers.google.com/android/guides/overview) Location APIs.



# Introduction

This demo focuses on primarily 3 things: 

* Requesting location updates from the ```FusedLocationProvider```
* Getting permission to use location
* Getting an address from a lat/lng

There are a few side-issues that are relevant as well: 

* [Using the emulator for evaluating location apps](emulator_location.md)
* [Dangerous permissions on Android](permissions.md)



This sample builds on the BasicLocationSample sample included in this repo,
and allows the user to request periodic location updates. In response, the API
updates the app periodically with the best available location, based on the
currently-available location providers such as WiFi and GPS (Global
Positioning System). The accuracy of the location is also determined by the
location permissions you've requested (we use the ACCESS_FINE_LOCATION here)
and the options you set in the location request.


This sample uses the
[FusedLocationProviderClient](https://developer.android.com/reference/com/google/android/gms/location/LocationServices.html).

# Getting Permission

Before we do anything, we need to have permission. A short overview is in [this writeup](permissions.md). 


# Using ```FusedLocationProvider``` to get location updates

Some terminology to get us started: 

* ```Location```: A basic object containing a lat/lng.
* ```LocationRequest```: An object that includes details about what we care about when asking for a location. 
* ```FusedLocationProviderClient```: We pass the LocationRequest to the FusedLocationProviderClient to ask for a location. 
* ```LocationResult```: An object that holds a location, that is the result of requesting a location lookup. 
* ```LocationCallback```: When the FusedLocationProviderClient has determined a location and is ready to return it 
   to us, it gives a LocationResult to a specified LocationCallback. The LocationCallback does something with the result. 
  
You'll see in ```MainActivity.onCreate()```, we start off by getting an instance of the ```FusedLocationProviderClient```, 
then creating a ```LocationRequest``` and a ```LocationCallback```. When the user clicks the "Start Updates"
button, we check permissions (always!), and pass the ```mLocationRequest``` to the provider. Later, 
when the provider has a location, it bundles the information (the location) into a LocationResult, 
which is passed to the LocationCallback. 

## Creating the ```LocationRequest```

As discussed in class, there are tradeoffs between how accurate a location is, how frequently the 
location is updated, and how much power is used (that is, how fast the battery drains). The LocationRequest 
is where we specify our priorities-- accuracy/frequency versus saving the battery.  


## Creating the ```LocationCallback```

Since getting a location can be a time consuming process, it MUST be asynchronous: The callback is 
what handles the location result once it's been determined. In our case, all we're doing is pulling 
out the location, when the location was taken, and updating the UI to reflect it. (You see this in 
```MainActivity.createLocationCallback()``). 



# Getting an Address (Reverse geo-coding)

Lat/lngs are nice and easy for thinking about a location on the globe, but as humans we aren't 
very good at making meaning of lat/lng, unless they are visualized on a map. One way we can make 
lat/lng locations more useful is to use a Geocoding service to translate the lat/lng into an 
address. From that address, we might use a street addres, or maybe even parts of the address, such 
as the city or state. 

We show the use of the geocoding service in MainActivity and ```FetchAddressIntentService```.

To start the service, we bundle up the location into the intent, and start the service. 

> NOTE: In class, we had the trouble that the service just didn't seem to be running. Oops-- 
> when creating the service, I forgot to declare it in the Manifest. 
> Learn from my mistake :) 

Recall: A Service in Android is an app component that runs without a UI. 

In this case, the service starts up, grabs the desired location from the intent, 
tries to get an address from the location, and sends the information back to the Activity (where 
we can update the UI) via the call back that was also passed in via the intent. 


# Cleaning Up

Since tracking location can drain the battery, it's important that we STOP requesting location 
updates when we don't need them anymore. In particular, when our app stops running, we should NOT 
be requesting location updates anymore. 

You'll see in ```MainActivity.onPause()``` we stop location updates. However, depending on your app 
and what you're doing with location, there might be other moments to stop requesting location updates. 




# License

This demo was adapted from [Google's Location API](https://github.com/googlesamples/android-play-location/issues) 
demos. 


