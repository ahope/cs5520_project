package edu.northeastern.cs5520.todo_adrienne.explore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityMainListViewBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityThemeExploreBinding;

public class ThemeExploreMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Use the ViewBinding instead of the layout directly
        ActivityThemeExploreBinding binding = ActivityThemeExploreBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}