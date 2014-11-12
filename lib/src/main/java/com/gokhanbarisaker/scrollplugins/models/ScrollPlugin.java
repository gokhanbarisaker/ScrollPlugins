package com.gokhanbarisaker.scrollplugins.models;

/**
 * Created by gokhanbarisaker on 11/11/14.
 */
public interface ScrollPlugin
{
    public void onScroll(final int scrolledPixels, final int visiblePixels, final int scrollablePixels);
}
