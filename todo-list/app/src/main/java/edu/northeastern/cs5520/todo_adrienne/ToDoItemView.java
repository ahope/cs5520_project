package edu.northeastern.cs5520.todo_adrienne;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

/**
 * TODO: document your custom view class.
 */
public class ToDoItemView extends ConstraintLayout {


    public ToDoItemView(Context context) {
        super(context);
        setupView(context);
    }

    public ToDoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
    }

    public ToDoItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupView(context);
    }

    private void setupView(Context context) {
        // Inflate the view from the layout resource.
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.to_do_item_view, this, true);
    }



}