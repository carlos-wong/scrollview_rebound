package com.rebound.myscroll;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * ScrollView����Ч����ʵ�� 
 * ������ ת����ע������
 */
public class MyScrollView extends ScrollView
{
    private View inner;

    private float y;

    private Rect normal = new Rect();;

    public MyScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate()
    {
        if (getChildCount() > 0)
        {
            inner = getChildAt(0);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (inner == null)
        {
            return super.onTouchEvent(ev);
        }
        else
        {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev)
    {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_UP:

                if (isNeedAnimation())
                {
                    animation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowY = ev.getY();
                int deltaY = (int) (preY - nowY);
                // ����
                scrollBy(0, deltaY);

                y = nowY;
                // �����������ϻ�������ʱ�Ͳ����ٹ�������ʱ�ƶ�����
                if (isNeedMove())
                {
                    if (normal.isEmpty())
                    {
                        // ���������Ĳ���λ��
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());

                    }
                    // �ƶ�����
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY, inner.getRight(), inner.getBottom() - deltaY);
                }
                break;

            default:
                break;
        }
    }

    // ���������ƶ�

    public void animation()
    {
        // �����ƶ�����
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(), normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // ���ûص������Ĳ���λ��
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        normal.setEmpty();

    }

    // �Ƿ���Ҫ��������
    public boolean isNeedAnimation()
    {
        return !normal.isEmpty();
    }

    // �Ƿ���Ҫ�ƶ�����
    public boolean isNeedMove()
    {

        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset)
        {
            return true;
        }
        return false;
    }

}