package com.udacity.gradle.backend;

import org.junit.Test;
/**
 * Created by Jonas on 22.02.2016.
 */
public class MyBeanTest
{
    @Test
    public void setDataTest(){
        MyBean bean=new MyBean();
        String data="abc";

        bean.setData(data);
        assert data.equals(bean.getData());
    }

}
