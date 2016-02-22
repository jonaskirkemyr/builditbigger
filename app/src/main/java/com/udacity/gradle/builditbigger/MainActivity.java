package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.udacity.gradle.jokeactivity.JokeActivity;


public class MainActivity extends ActionBarActivity
{
    private EndpointsAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = null;
    }

    @Override
    protected void onDestroy()
    {
        if (task != null)
            task.cancel(true);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view)
    {
        if (task == null)
        {
            task = new EndpointsAsyncTask();
            task.execute(new iEndPointcallback()
            {
                @Override
                public void endPoints(String result)
                {
                    task = null;
                    JokeActivity.startActivity(MainActivity.this, result);
                }
            });
        }
    }


}
