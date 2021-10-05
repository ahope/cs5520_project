# Adding Room to the App for Persistence

## Add dependencies to the build.gradle file

```groovy
    // Room components
    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
    annotationProcessor "androidx.room:room-compiler:$rootProject.roomVersion"
    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"
```

## Change the data class to an Entity, by adding annotations

## 
