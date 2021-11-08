package edu.northeastern.cs5520.todo_adrienne.events;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import androidx.core.util.Pair;

import edu.northeastern.cs5520.todo_adrienne.NewToDoActivity;

public class ToDoListItemListener {

    public void onToDoItemClickListener(View view, int taskId) {
        Intent intent = new Intent(view.getContext(), NewToDoActivity.class);
        intent.putExtra(NewToDoActivity.EXTRA_KEY_TODO_ID, taskId);
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(),
                        view,
                        new Pair<>(view.findViewById(R.id.imageview_item),
                                DetailActivity.VIEW_NAME_HEADER_IMAGE),
                        new Pair<>(view.findViewById(R.id.textview_name),
                                DetailActivity.VIEW_NAME_HEADER_TITLE)););
        view.getContext().startActivity(intent, options.toBundle()); //, options.toBundle());


//        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                MainActivity.this,
//
//                // Now we provide a list of Pair items which contain the view we can transitioning
//                // from, and the name of the view it is transitioning to, in the launched activity
//                new Pair<>(view.findViewById(R.id.imageview_item),
//                        DetailActivity.VIEW_NAME_HEADER_IMAGE),
//                new Pair<>(view.findViewById(R.id.textview_name),
//                        DetailActivity.VIEW_NAME_HEADER_TITLE));
//
//        // Now we can start the Activity, providing the activity options as a bundle
//        ActivityCompat.startActivity(MainActivity.this, intent, activityOptions.toBundle());

    }

}
