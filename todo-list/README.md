# Adrienne's ToDo App 

This is my version of the ToDo app we've been working on. Please feel free to use it as a reference 
for what we've talked about. 

I'll continue to document and update this README. 

## Remember to add view and data binding to your project by adding the following to your app build.gradle file: 

```groovy
    buildFeatures {
        viewBinding true
    }
    dataBinding {
        enabled true
    }
```

## Look at ```MainActivity_ListView```

This is the Activity-only (no fragments!) that is using a RecyclerView to display the todos. 

# Order

1. Mockup ToDo Repository
1. ```ToDoListFragment```
   * ViewBinding
   * Custom UI Component
1. ```NewToDoActivity```
   * ViewModel
   * LiveData
   * Mutable LiveData
   * Create a ```ToDoFragment``` that displays a ```ToDo```. Do so in such a way as to be re-usable.  
1. Update ```ToDoList``` Fragment to listen to ViewModel collection
1. ```EditToDoActivity```
1. Create a new MainActivity (starting point) that uses a ListView (RecyclerView) and an adapter to display the list. 
1. Persist data using Room



## TaskRepository

* Create a singleton and a "ToDo" class

## ```ToDoListFragment``` 

## Adding Reminders and Notifications

The goal, to begin with, is to add a notification that reminders the user when 
a task is almost done. For now, it will just be a notification 1 hour before the 
due time. 

In a todo-app, we'd probably like (eventually) to be able to specify reminders, a time to remind, 
repeat reminders, etc. I want to think about how to do this: I could either register a reminder for 
every todo/task we have, at the time that it needs to be reminded. If we have a lot of tasks, this could 
be a lot of overhead on the OS. Alternatively, we could check every so often, and schedule a reminder/notification 
for the things coming up in the next period of time. I like this approach, because if the 
remind date/time changes, or the task gets deleted or completed before the remind deadline, then I don't 
have to worry about cancelling a reminder. 

So the current plan: 

* Set a reminder time for a task 1 hour before the deadline. 
   * Every time we change the date/time of the task, change the reminder time. 
* Every 3 hours, run a task that checks for upcoming deadlines, and create a notification for each. 
   * If a task is created with a deadline of < 3 hours, create a notification.
* If a task gets completed or deleted, check the deadline. If there is an active reminder/notification 
in the queue, cancel it. 
  
The simple approach: 

* BroadcastReceiver to be run periodically: ```ScheduleReminderNotifications``` class. 
* BroadcastReceiver to be run when an task is due: ```TodoReminderNotification``` class. 

Okay, got this set up. THE PROBLEM: We need to access the database. We run into the problem that 
BroadcastReceiver needs to do things off the main thread; We can have the DB use LiveData and 
respond to the asynchronous loading, but BroadcastReceiver doesn't have a Lifecycle, so can't observe
the LiveData and do something after the data is loaded. 

Options for this: 
(https://stackoverflow.com/questions/56448871/how-to-observe-live-data-inside-broadcast-receiver): 
Basically, use WorkManager. 









