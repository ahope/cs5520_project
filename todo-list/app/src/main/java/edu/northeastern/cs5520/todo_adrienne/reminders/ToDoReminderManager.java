package edu.northeastern.cs5520.todo_adrienne.reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.ZoneOffset;

import edu.northeastern.cs5520.todo_adrienne.NewToDoActivity;
import edu.northeastern.cs5520.todo_adrienne.data.ToDo;

/**
 * This class encapsulates all the tasks needed to keep track of the AlarmManager
 * and reminders and all that jazz.
 */
public class ToDoReminderManager {

    private static final int SCHEDULE_REMINDER_ALARM_ID = 0;
    private static final int DO_REMINDER_NOTIFICATION_ID = 1;

    private static final String TAG = ToDoReminderManager.class.toString();

    public static boolean isReminderSchedulerSet(Context context) {
        return (PendingIntent.getBroadcast(context,
                SCHEDULE_REMINDER_ALARM_ID,
                new Intent(context, ScheduleReminderNotificationsReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);
    }

    public static void scheduleReminderScheduler(Context appContext) {
        Log.i(TAG, "Scheduling reminder scheduler");
        Intent schedulerIntent = new Intent(appContext, ScheduleReminderNotificationsReceiver.class);
        PendingIntent schedulerPendingIntent = PendingIntent.getBroadcast
                (appContext,
                        SCHEDULE_REMINDER_ALARM_ID,
                        schedulerIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

//        long repeatInterval = AlarmManager.INTERVAL_HOUR * 3;
        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES * 3;
        long triggerTime = SystemClock.elapsedRealtime()
                + repeatInterval;

        AlarmManager alarmManager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating
                (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime(),//triggerTime,
                        repeatInterval,
                        schedulerPendingIntent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void scheduleNotificationForTodo(Context appContext, AlarmManager alarmManager, ToDo todo) {
        Log.i(TAG, "Scheduling notification for tod ");
        Intent doReminderNotificationIntent = new Intent(appContext, DoReminderNotificationReceiver.class);
        doReminderNotificationIntent.putExtra(DoReminderNotificationReceiver.EXTRA_KEY_TODO_ID, todo.getId());
        PendingIntent schedulerPendingIntent = PendingIntent.getBroadcast
                (appContext,
                        DO_REMINDER_NOTIFICATION_ID,
                        doReminderNotificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerTime = todo.getReminderDateTime().toEpochSecond(ZoneOffset.UTC);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                        triggerTime,
                        schedulerPendingIntent);
    }

}
