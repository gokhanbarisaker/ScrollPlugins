package com.gokhanbarisaker.scrollplugins.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.gokhanbarisaker.scrollplugins.models.ScrollPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokhanbarisaker on 11/11/14.
 */
public class ObservableHorizontalScrollView extends HorizontalScrollView
{
    final private List<ScrollPlugin> scrollPluginList = new ArrayList<ScrollPlugin>();

    public ObservableHorizontalScrollView(Context context) {
        super(context);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);

        final int width = getWidth();
        final int scrollableHeight = getChildAt(0).getWidth() - width;

        synchronized (scrollPluginList)
        {
            for (ScrollPlugin plugin : scrollPluginList) {
                plugin.onScroll(x, width, scrollableHeight);
            }
        }
    }

    public boolean addScrollPlugin(ScrollPlugin plugin)
    {
        synchronized (scrollPluginList)
        {
            boolean added = false;

            if (plugin != null)
            {
                added = scrollPluginList.add(plugin);
            }

            return added;
        }
    }

    public boolean removeScrollPlugin(ScrollPlugin plugin)
    {
        synchronized (scrollPluginList)
        {
            boolean removed = false;

            if (plugin != null)
            {
                removed = scrollPluginList.remove(plugin);
            }

            return removed;
        }
    }
}