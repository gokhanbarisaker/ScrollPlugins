package com.gokhanbarisaker.scrollplugins.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.gokhanbarisaker.scrollplugins.models.ScrollPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokhanbarisaker on 22/12/13.
 */
public class ObservableScrollView extends ScrollView
{
    private List<ScrollPlugin> scrollPluginList = new ArrayList<ScrollPlugin>();

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy)
    {
        super.onScrollChanged(x, y, oldx, oldy);

        final int height = getHeight();
        final int scrollableHeight = getChildAt(0).getHeight() - height;

        synchronized (scrollPluginList)
        {
            for (ScrollPlugin plugin : scrollPluginList) {
                plugin.onScroll(y, height, scrollableHeight);
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
