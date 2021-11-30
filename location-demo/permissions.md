# Dangerous Permissions

We've touched on various Android (permissions)[https://developer.android.com/guide/topics/permissions/overview]. 
Permissions are how Android prevents apps from taking over your device and doing things it shouldn't 
be doing. They are also there to make it easy for you (as the user) to be aware of what an app might 
be doing on your phone. For example: is it able to use the network? Can it access data you might not
want it to? Can it prevent your phone from going to sleep? 


So far, we've really just encountered install-time/"normal" permissions. We saw this when we wanted to 
use the network to send/receive data. To use this normal permission, we needed to ***declare*** the 
permission in the ```AndroidManifest.xml```, and the user needed to approve the permission when they 
*installed* the app. 

Android distinguishes between "normal" permissions (e.g., using the network) and "dangerous" permissions. 
***Dangerous permissions*** restrict access to particularly private user data. 
Due to greater potential to take advantage of sensitive data, dangerous permissions must be 
requested during *runtime* (rather than on installation). In addition to asking the user for permission 
during runtime, the app/developer needs to explain *why* your app needs this permission. 

Location is one of those dangerous permissions. Therefore, we need to ensure that when our app tries 
to use location, we HAVE to check whether the user has given us permission. Also: we need to check 
EVERY TIME we want to use location, because it's possible for the user to go into the Settings of the 
device and revoke permissions. 

Because this is something that everyone using the FusedLocationProvider needs to do, the API provides 
an easy way to check the permissions and subsequently request permissions if needed. You'll see the 
requesting of the location permissions  
in ```MainActivity.buildLocationSettingsRequest```, using the ```LocationSettingsRequest.Builder```. 
In ```MainActivity.startLocationUpdates```, we use the ```SettingsClient``` to check if we have all 
the appropriate permissions. 

In this example, when the user clicks "Start Updates", we use ```SettingsClient``` to check the permissions. 
If everything is granted, great! We start requesting location updates. However, if we don't have 
permission, we either try to ask the user for permission, or tell the user to go to the Settings app 
and give permission. 

> NOTE: If the app DOES NOT have permission, but it goes ahead and tries to use the dangerous permission, 
Android silently refuses to let the app use it. 

For example: In this location app, if we don't check that we have permission to use location, and the 
user has refused permission, but we go ahead and request location updates, we won't get location updates. 
But, we also won't be told that we're not getting location updates, or WHY we're not getting location 
updates. Therefore, it's really important that our flow and logic for checking permissions is clear 
and correct. And, that the app gracefully handles not having permission. 


