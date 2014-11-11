package com.gokhanbarisaker.scrollplugins.models;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by gokhanbarisaker on 11/11/14.
 */
public abstract class ParallaxScrollPlugin implements Parallaxable, ScrollPlugin
{
    // == Variables =========================================

    private WeakReference<View> parallaxableViewWeak = null;


    // == Constructors ======================================

    public ParallaxScrollPlugin(View parallaxView)
    {
        setParallaxableViewWeak(parallaxView);
    }


    // == ===================================================

    public void onScroll(final int scrollY, final int visibleHeight, final int scrollableHeight)
    {
        View parallaxableView = getParallaxableView();

        if (parallaxableView != null)
        {
            float parallaxPercentage = .0f;

            final int parallaxableViewTop = parallaxableView.getTop();
            final int parallaxableViewBottom = parallaxableView.getBottom();

            if (parallaxableViewTop <= visibleHeight)
            {
                parallaxPercentage = (float) scrollY / (float) parallaxableViewBottom;
            }
            else
            {
                final int parallaxableHeight = parallaxableViewBottom - parallaxableViewTop + visibleHeight;
                final int scrollYTranslated = scrollY + visibleHeight - parallaxableViewTop;

                parallaxPercentage = (float) scrollYTranslated / (float) parallaxableHeight;
            }

            // Clip parallax range between [0,100]
            if (parallaxPercentage < .0f) { parallaxPercentage = .0f; }
            else if (parallaxPercentage > 1.f) { parallaxPercentage = 1.f; }

            onParallax(parallaxPercentage, -1.f);
        }
        else
        {
            float verticalScrollPercentage = (float) scrollY / (float) scrollableHeight;

            onParallax(verticalScrollPercentage, -1.f);
        }
    }

    private void setParallaxableViewWeak(View view)
    {
        if (view == null)
        {
            parallaxableViewWeak = null;
        }
        else
        {
            parallaxableViewWeak = new WeakReference<View>(view);
        }
    }

    private View getParallaxableView()
    {
        return (parallaxableViewWeak == null)?(null):(parallaxableViewWeak.get());
    }
}