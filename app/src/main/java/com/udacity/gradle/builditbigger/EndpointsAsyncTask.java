package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Jonas on 21.02.2016.
 */
public class EndpointsAsyncTask extends AsyncTask<iEndPointcallback, Void, String>
{
    private MyApi api;
    private iEndPointcallback callback = null;


    @Override
    protected void onCancelled()
    {
        if (callback != null)
            callback.endPoints(null);
        super.onCancelled();
    }

    @Override
    protected String doInBackground(iEndPointcallback... params)
    {
        if (api == null)
        {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("http://10.0.2.2:8080/_ah/api").setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
            {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException
                {
                    request.setDisableGZipContent(true);
                }
            });

            api = builder.build();
        }

        callback = params[0];
        try
        {
            // return api.
            return api.tellJoke().execute().getData();
        }
        catch (IOException e)
        {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke)
    {
        if (callback != null)
            callback.endPoints(joke);
    }
}