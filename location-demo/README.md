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

* (Using the emulator for evaluating location apps)[emulator_location.md]
* (Dangerous permissions on Android)[permissions.md]



This sample builds on the BasicLocationSample sample included in this repo,
and allows the user to request periodic location updates. In response, the API
updates the app periodically with the best available location, based on the
currently-available location providers such as WiFi and GPS (Global
Positioning System). The accuracy of the location is also determined by the
location permissions you've requested (we use the ACCESS_FINE_LOCATION here)
and the options you set in the location request.


This sample uses the
[FusedLocationProviderClient] (https://developer.android.com/reference/com/google/android/gms/location/LocationServices.html).

To run this sample, **location must be enabled**.

# 





# License

This demo was adapted from [Google's Location API](https://github.com/googlesamples/android-play-location/issues) 
demos. 


