package com.rebound;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class Rebound extends Activity
{

    private ScrollView scroll;

    private TextView tv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        scroll = (ScrollView) findViewById(R.id.sv);
        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    public void onAttachedToWindow()
    {

        super.onAttachedToWindow();
        // scrollToBottom(scroll, tv);
    }

    public void scrollToBottom(final View scroll, final View inner)
    {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable()
        {
            public void run()
            {
                if (scroll == null || inner == null)
                {
                    return;
                }

                int offset = inner.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0)
                {
                    offset = 0;
                }

                scroll.scrollTo(0, offset);
                scroll.scrollBy(0, -offset);
                System.out.println("scrollX:" + scroll.getScrollX() + "scrollY:" + scroll.getScrollY());
            }
        });
    }

}
