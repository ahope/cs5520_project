# Doing things OFF the UI/Main Thread

2 main rules of Android programming: 

* Don't do long-running tasks on the main/UI thread
  * This comes up a LOT: Getting something from the network, loading something from a database. 
* Threads that are NOT the main/UI thread can't directly access and update the UI
  

We have a few ways to do something on another thread:
* It used to be that an ```ASyncTask``` is the best way to do something to not overwhelm the UI thread. 
  * You'll see an example in ```MainActivity.Timer```
* Now, there are many alternatives!
  * See ```MainActivity``` for an example of using ```CountdownTimer```
  * ```CountdownTimer``` 
  * ```Executor``` to manage running stuff on other threads; ```Handler``` to post UI updates
  
## Notifications

I also added an Activity to show how to create a notification. 

In ```SendNotificationActivity```: 

* First, create a Notification Channel -- this is what I missed in class! 
* Then, look at the ```sendNotification``` method. This should surface a simple notification in the tray. 

## 

## Service, Broadcast Receiver

The BroadcastReceiver (```MyCameraReceiver``) is an example of a **manifest-declared** receiver. This is 
not allowed after API 26 (:(). We need to rather register in Context (https://developer.android.com/guide/components/broadcasts#context-registered-recievers). 


To send an event to the emulator to test a BroadcastReceiver: 

```
./adb shell  
am broadcast -a android.intent.action.CAMERA_BUTTON
```

List of actions (all actions you can recieve): 

```
~/Library/Android/sdk/platforms/android-22/data/broadcast_actions.txt
```

(change the "android-22" to whatever version of android you're working with).
