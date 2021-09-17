---
layout: default
title: Start Activity For Result Deprecated
---

## Working through the Android Fundabmentals Codelab 2.1

While working through [this codelab](https://developer.android.com/codelabs/android-training-create-an-activity?index=..%2F..%2Fandroid-training#5), 
in Task 4 we investigate returning data from another Activity, 
to be used by the calling Activity. ```startActivityForResult``` works, but it's deprecated. 

Here's the new and preferred way to start an activity for result: 

When creating the Activity, create an ```ActivityResultLauncher```, which results from 
"registering" for an Activity Result: 

```java
someActivityResultLauncher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // Here, no request code
                Intent data = result.getData();
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    });
```

You'll see I put the code to update the text views in the ```onActivityResult``` anonymous method definition. 

Then, in the onClick handler for the button, we have: 

```java
//        startActivityForResult(intent, TEXT_REQUEST);
        someActivityResultLauncher.launch(intent);

```
Instead of calling ```startActivityForResult```, we tell the ActivityResultLauncher we already created to 
launch the intent. 

Here's the full code: 

```java
package edu.northeastern.cs5520.twoactivities;

import ....

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";
    private EditText mMessageEditText;
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        someActivityResultLauncher = registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          new ActivityResultCallback<ActivityResult>() {
              @Override
              public void onActivityResult(ActivityResult result) {
                  if (result.getResultCode() == Activity.RESULT_OK) {
                      // Here, no request code
                      Intent data = result.getData();
                      String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                      mReplyHeadTextView.setVisibility(View.VISIBLE);
                      mReplyTextView.setText(reply);
                      mReplyTextView.setVisibility(View.VISIBLE);
                  }
              }
          });
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        String message = mMessageEditText.getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        someActivityResultLauncher.launch(intent);

    }
}

```



