package com.example.vamsi.task.Class;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class mySingleton {

    private static mySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mxt;

    private mySingleton(Context context)
    {
        mxt=context;
        requestQueue=getRequestQueue();
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mxt.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized mySingleton getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance=new mySingleton(context);
        }
        return mInstance;
    }


    public <T>void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}

