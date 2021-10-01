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

