package edu.northeastern.cs5520.todo_adrienne;

import android.app.Application;


public class ToDoApplication extends Application {

        private static ToDoApplication sInstance;

        @Override
        public void onCreate() {
            super.onCreate();
            sInstance = this;
        }

        public static ToDoApplication getInstance() {
            return ToDoApplication.sInstance;
        }
}
