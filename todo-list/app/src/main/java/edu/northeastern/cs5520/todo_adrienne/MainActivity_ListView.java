package edu.northeastern.cs5520.todo_adrienne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRecyclerViewAdapter;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityMainListViewBinding;
import edu.northeastern.cs5520.todo_adrienne.reminders.NotificationManager;
import edu.northeastern.cs5520.todo_adrienne.reminders.ToDoReminderManager;

public class MainActivity_ListView extends AppCompatActivity {

    private ToDoListViewModel toDoListViewModel;
    private ToDoItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This is what we would do if we weren't using the ViewBinding (as we see below)
//        setContentView(R.layout.activity_main_list_view);
        // Get a new or existing ViewModel from the ViewModelProvider.
        toDoListViewModel = new ViewModelProvider(this).get(ToDoListViewModel.class);

        // Use the ViewBinding instead of the layout directly
        ActivityMainListViewBinding binding = ActivityMainListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMain.scrollToPosition(0);

        adapter =
                new ToDoItemRecyclerViewAdapter(new ToDoItemRecyclerViewAdapter.TodoDiff());

        toDoListViewModel.getAllToDos().observe(this, todos -> {
           adapter.submitList(todos);
        });

        binding.recyclerViewMain.setAdapter(adapter);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_ListView.this, NewToDoActivity.class);
                startActivity(intent);
            }
        });

        // Make sure we can surface notifications
        NotificationManager.createNotificationChannel(getApplicationContext());

        // Make sure
//        if (!ToDoReminderManager.isReminderSchedulerSet(getApplicationContext())) {
//            ToDoReminderManager.scheduleReminderScheduler(getApplicationContext());
//        }
        ToDoReminderManager.scheduleReminderWorker(getApplicationContext());

        setSupportActionBar(binding.myToolbar);
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            return true;
        }

        switch (id) {
            case R.id.action_sort_ascending:
            case R.id.action_sort_descending:
            case R.id.action_sort_by_completed:
            case R.id.action_sort_by_deadline:
            case R.id.action_sort_by_title:
                item.setChecked(!item.isChecked());
                adapter.sortByDeadlineAscending();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }

}