package com.udacity.gradle.builditbigger;
import android.test.AndroidTestCase;
import com.udacity.gradle.jokes.Joker;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jonas on 21.02.2016.
 */
public class EndpointAsyncTaskTest extends AndroidTestCase
{
    public void testResponse(){
        final CountDownLatch cd=new CountDownLatch(1);
        Long start=new Date().getTime();

        new EndpointsAsyncTask().execute(new iEndPointcallback() {
            @Override
            public void endPoints(String result)
            {
                Joker joke=new Joker();

                String expectedResult=joke.getJoke();

                assertEquals(result,expectedResult);
                cd.countDown();
            }
        });

        try{
            if(!cd.await(30, TimeUnit.SECONDS))
            {
                Long end = (new Date().getTime() - start) / 1000;
                throw new AssertionError("Service not responding after " + end + " seconds");
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}




