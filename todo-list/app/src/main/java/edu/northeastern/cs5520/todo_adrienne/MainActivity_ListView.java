package edu.northeastern.cs5520.todo_adrienne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRecyclerViewAdapter;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityMainListViewBinding;

public class MainActivity_ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_list_view);
        // Use the ViewBinding instead of the layout directly
        ActivityMainListViewBinding binding = ActivityMainListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMain.scrollToPosition(0);
        binding.recyclerViewMain.setAdapter(new ToDoItemRecyclerViewAdapter());

    }
}