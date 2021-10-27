package edu.northeastern.cs5520.todo_adrienne.reminders;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.time.LocalDateTime;
import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ScheduleReminderNotificationsWorker extends Worker {

    private static final String TAG = ScheduleReminderNotificationsReceiver.class.toString();

    private Context mContext;


    public ScheduleReminderNotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "Running ScheduleReminderNotificationWorker");
        // Get all the ToDos to be done in the next 3 hours
        ToDoItemRepository repository = ToDoItemRepository.getSingleton(mContext);
        List<ToDo> todos = repository.getTodosToBeReminded(LocalDateTime.now(), LocalDateTime.now().plusHours(3));

        Log.i(TAG, "Got some todos!   " + todos.size());

        // Set up a reminder for each
        for (ToDo todo : todos) {
            Log.i(TAG, "scheduling notification worker for todo " + todo.getId());
            ToDoReminderManager.scheduleNotificationForTodo(getApplicationContext(), todo);
        }
        return Result.success();
    }
}
