package com.gokhanbarisaker.scrollplugins.listeners.abslistview;

import android.view.View;
import android.widget.AbsListView;

import com.gokhanbarisaker.scrollplugins.models.Parallaxable;

/**
 * Created by gokhanbarisaker on 11/11/14.
 */
public class OnScrollListener implements AbsListView.OnScrollListener
{
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        final int listHeight = view.getHeight();

        View itemView = null;
        int itemHeight = 0;
        int parallaxableHeight = 0;

        float parallaxPercentage = .0f;

        for(int i = 0; i < visibleItemCount; i++)
        {
            itemView = view.getChildAt(i);
            itemHeight = itemView.getHeight();
            parallaxableHeight = listHeight + itemHeight;


            // Check if current childView is parallaxable.
            //
            // !!!: Note that, it turned out it is easier & efficient to apply Parallaxable
            // !!!: to ViewHolder, that is also a tag of childView.
            if(itemView != null && itemView.getTag() instanceof Parallaxable)
            {
                parallaxPercentage = ((float) itemView.getBottom()) / parallaxableHeight;

                ((Parallaxable) itemView.getTag()).onParallax(parallaxPercentage, .0f);
            }
        }
    }
}
