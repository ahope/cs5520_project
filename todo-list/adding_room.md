# Adding Room to the App for Persistence

## Add dependencies to the build.gradle file

```groovy
    // Room components
    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
    annotationProcessor "androidx.room:room-compiler:$rootProject.roomVersion"
    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"
```


## Checking Refactor

I've refactored the ToDoItemRepository. My goal was to ensure that the Repository could easily use
either an in-memory datasource or a db datasource. To do this, I created an interface, and then
a DbDataSource and a InMemoryDataSource.



## Change the data class to an Entity, by adding annotations

## Add the Database class

## 

## Adding a parameterized Query


## Adding a new query to the project 

* Add the query method to ```ToDoItemRepository```
   * ```GetNToDos(int n)```
* Add the query to the DataSource interface ```IToDoDataSource```
   * ```GetNToDos(int n)```
* Add the query to the DataSource implementations
   * For ToDoDbDataSource: 
       * ```ToDoDao.GetNToDos(int n)```
       * ``````
         

To actually use this 



# Working with dates, times, etc. 

Challenge: SQLite doesn't have a "DateTime" column type. 

Solution: Store as a String. Do this by creating a ```TypeConverter``` (in this case, ```TimestampConverter```). 

Challenge: Transforming from a null dateTime to a non-null DateTime. 

Recommendation: Use a LocalDateTime, and require a minimum Android API level of 26. (Makes things easier. ) If you HAVE to accommodate <26, use a Java Date object instead. 

SImple approach: Require user to enter date/time in specific format, and check the format before accepting the input. 
