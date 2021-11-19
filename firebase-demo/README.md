# Firebase Demo

There are 2 demos here: Using Firebase *cloud messaging* and *realtime database*. 

Cloud messaging is what enables ***push notifications***: this is how we can send a message to a 
specific device. I gave an example of when we might want to do this in class: When my Nest camera 
at home detects a person in the video, send a push notification to my mobile device to notify me. 
When the push notification gets to my device, the app generates a "regular" notification, to be 
displayed to me just as any other notification. The significance here is that a push notification 
gives us the ability to "push" information to the device, rather than depending on our app to "pull" 
data from a cloud/network/server somewhere to figure out if something important happened. 

The Realtime Database is a NoSQL database that exists in the cloud, and we can sync with it to ensure 
that all clients that are interested in a given piece of data/information are updated with a change 
in that data, as soon as it changes. 

# Resources/Links/References

https://firebase.google.com/docs/cloud-messaging/android/client


## Using Firebase for Cloud Messaging

Firebase is a tool that can be used to provide "backend" features to a mobile app very easily. It's 
grown in scope over the years, adding tons of functionality. We're just going to demo a couple of features 
here. 

It's important to think of Firebase as a ***backend***.  This gives us a few things to think about: 

* Firebase: the "backend"
    * A Firebase account (for you, the developer)
    * A Firebase project
* A mobile app (you can use Firebase with web applications and other kinds of applications, but we're focusing on using it with mobile for now)
* An *instance* of a mobile app
    * This translates to a mobile app running on a specific device. We are going to refer to it as 
       a "user", but the reality is that you can have one user (person) using the app on different 
       mobile devices, in which case one user would map to multiple ***instances***.
      
To set up your project for using Firebase, you can use the setup tool in Android Studio: go to 
the Tools menu; choose Firebase. Choose the relevant product in the panel, and it will walk you 
through the process of setting stuff up. 



## Push Notifications with Cloud Messaging

In the big picture, we're going to use Firebase to send some information to the device so our app can 
do something in response. The key to making this happen is the ```instanceId```. Every instance of the 
app needs to register for an instanceId; Firebase assigns an instanceId to that app/device combination. 
From there, it's the developer's job to keep track of that ID. 

There are few different ways we can send notifications to devices: 

* From the Firebase Console: 
    * Send a notification to ALL devices/app instances that have been registered
    * Send a notification to a SPECIFIC device/app instance
    * Send a notification to a GROUP of device/app instances
    
We can effectively send a notification from a specific device/instance to another device/instance 
by telling Firebase to send a notification to a specified device/instance.

To make Cloud Messaging work, we need to do a few things: 

* REQUIRED: Manage the instanceID. 
    * We create a Service that listens to Firebase for an instanceId assignment.
    * The instanceID sometimes gets re-created; we need to listen for that too.
* REQUIRED: Listen for Messages from Firebase. 
    * Again, we create a Service that handles incoming messages. Then, elsewhere in our app we do 
      something with those messages. 
* Send messages to Firebase to be sent to everyone else. 
    * This can be done with either a "plain jane" API call (using HTTP) or a Firebase library.


## The Demo Code

The relevant code is in the firebasedemo/fcm package. 

### ```DemoMessagingService```

This class is the Service that manages connection with the Firebase cloud service. All the Firebase 
methods are overridden to allow us to see the Firebase communication. 

### Sending a message from the Firebase Console to the Device/App

The simplest use of Firebase Cloud Messaging is to send a message from the Firebase Console 
to the device, by specifying the instance ID/token. 

NOTE: It used to be easier to do this. Now, you can only send a message to a specific device/app instance 
by creating a "Test" notification. 

1. **Find the instance ID of the device**. 
    * Run the app
    * Click the "Firebase Messaging" button to open the ```FCMActivity```
    * Click the "Show Token" button to print the token to the log output.
1. **Send a test message from the Firebase Console**
    * Go to the Firebase Console. (Select your project and such)
    * Go to the "Cloud Messaging" section (choose menu on the left-hand side)
    * Click "New Notification"
    * Enter some details for the notification
    * Click "Send Test Notification". 
    * Copy/Paste the token from logcat to the dialog that pops up. 
    * Click "Test" button. 
    
At this point, the device should receive a notification conforming to the detials that you 
entered in the UI. 

NOTE: Sending a notification from the Firebase Console is a special thing. It doesn't actually go 
through YOUR notification code. So, you may have seen some notification stuff in the ```DemoMessagingService```, 
but this sample notification doesn't go through that path. 


### Sending A Message to another Device/"user" (Device -> Device)

You can send a message from one device to another if you have the instanceID/token of the destination 
app. 

To demo this: 

* Open another emulator and run your app. 
* Get the instance ID associated with the second emulator by clicking the "Show Token" button. 
* Paste the ID in the EditText box for "Token"
* Enter a message in the "Message" EditText box
* Click "Send". The message should be delivered to the other emulator. 

How does this work? 

This functionality is utilizing a "plain HTTP" request. It's actually not a great idea to do it this 
way anymore, but it's in here for legacy purposes. The way Firebase *wants* us to send messages is 
that our app sends a signal to our backend, and the backend asks Firebase to send the message to 
the device. In the code here, what you see is the code that should be executed on the backend to 
ask Firebase to send a message to another device. 

There are some downsides to us doing it this way: specifically, we have to include our credentials 
in the client app in order to submit the request to Firebase. This isn't a great idea. 

The upside is that it's simple, and it demonstrates what's possible, so you as students can get the idea. 



### Send a message from a device to a group

There are a few different ways to send messages to groups of devices/instances using Firebase: "device
groups" and "topic groups". ***Device groups*** are ways that you, as a developer/administrator of the 
Firebase account, can group devices or app instances together. This is really more for handling groups 
in a server environment, or as an administrator. ***Topic groups*** are more lightweight. You can 
define "topics", and then each app instance/device can subscribe or unsubscribe to those topics.

We're going to focus on ***topic groups***. Here's an example of how we might use topics: 

Let's say we have an app that publishes news headlines, and we want to let our users choose 
which kinds of headlines they get notified about. Let's say the choices are "news", "weather" and 
"sports". We write our app to provide a UI to allow users to "subscribe" to those topics. When a new 
headline for, say, "weather" comes in, our backend/server sends a message to the "weather" topic group. 
Every device that is registered gets notified with the same message. 

Now, topics are pretty lightweight. As a developer, we (probably) want to ensure that we protect the topics 
to ensure not too many are created outside of our control. 


To subscribe to a topic: 

1. 





