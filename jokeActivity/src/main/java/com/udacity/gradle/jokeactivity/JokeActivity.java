package com.udacity.gradle.jokeactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.TextView;
import com.udacity.gradle.imageactivity.R;

/**
 * Created by Jonas on 21.02.2016.
 */
public class JokeActivity extends ActionBarActivity
{
    private static String KEY_JOKE = "joke";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);

        TextView text = (TextView) findViewById(R.id.joke);

        Intent temp = getIntent();
        if (temp != null && temp.hasExtra(KEY_JOKE) && !TextUtils.isEmpty(temp.getStringExtra(KEY_JOKE)))
            text.setText(getIntent().getStringExtra(KEY_JOKE));

    }

    public static void startActivity(Fragment fragment, String joke)
    {
        Activity a = fragment.getActivity();
        Intent i = new Intent(a, JokeActivity.class);
        i.putExtra(KEY_JOKE, joke);
        a.startActivity(i);
    }

    public static void startActivity(Activity activity, String joke)
    {
        Intent i = new Intent(activity, JokeActivity.class);
        i.putExtra(KEY_JOKE, joke);
        activity.startActivity(i);

    }


}
