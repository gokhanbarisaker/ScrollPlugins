package com.gokhanbarisaker.scrollpluginsapp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v8.renderscript.RenderScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gokhanbarisaker.scrollplugins.models.ParallaxScrollPlugin;
import com.gokhanbarisaker.scrollplugins.models.ScrollPlugin;
import com.gokhanbarisaker.scrollplugins.views.ObservableScrollView;
import com.gokhanbarisaker.scrollpluginsapp.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParallaxStickyHeaderCollectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParallaxStickyHeaderCollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ParallaxStickyHeaderCollectionFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ParallaxStickyHeaderCollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParallaxStickyHeaderCollectionFragment newInstance() {
        ParallaxStickyHeaderCollectionFragment fragment = new ParallaxStickyHeaderCollectionFragment();
        return fragment;
    }

    public ParallaxStickyHeaderCollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_parallax_sticky_header_collection, container, false);

        if (rootView != null)
        {
            ObservableScrollView scrollView = (ObservableScrollView) rootView.findViewById(R.id.scrollview);
            View parallaxableView = rootView.findViewById(R.id.parallax_reference);
            final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageview);
            final View holyView = rootView.findViewById(R.id.holycow);
            //holyView.setDrawingCacheEnabled(true);


            final RenderScript renderScript = RenderScript.create(getActivity());

            scrollView.addScrollPlugin(new ParallaxScrollPlugin(parallaxableView) {

                private float lastObservedVerticalPercentage = Float.MIN_VALUE;

                @Override
                public void onParallax(float verticalPercentage, float horizontalPercentage) {

                    // Log.e("Foo", "Parallax: %" + verticalPercentage);

                    if (lastObservedVerticalPercentage != verticalPercentage)
                    {
                        int asdf = (int) (-holyView.getHeight() * verticalPercentage);

                        holyView.setPadding(0, asdf, 0, (int) (-asdf * .5f));
                    }

//                    boolean shouldBlur = ((((int) (verticalPercentage * 40)) % 2) == 0) && (lastObservedVerticalPercentage != verticalPercentage);
//
//                    if (verticalPercentage >= 0.f && verticalPercentage <= 1.f && shouldBlur)
//                    {
//
//                        Bitmap bitmap = holyView.getDrawingCache();
//
//                        // 0 < x <= 25
//                        float blurRadius = (24.99f * verticalPercentage) + .01f;
//
////                        final BitmapFactory.Options options = new BitmapFactory.Options();
////                        options.inSampleSize = calculateSampleSize(verticalPercentage);
////                        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pattern, options);
//
//                        final Allocation input = Allocation.createFromBitmap(renderScript, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
//                        final Allocation output = Allocation.createTyped(renderScript, input.getType());
//                        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(renderScript, android.support.v8.renderscript.Element.U8_4(renderScript));
//                        script.setRadius(blurRadius);
//                        script.setInput(input);
//                        script.forEach(output);
//                        output.copyTo(bitmap);
//
//                        imageView.setImageBitmap(bitmap);
//                    }

                    lastObservedVerticalPercentage = verticalPercentage;
                }

                private int calculateSampleSize(final float parallaxPercentage)
                {
                    if (parallaxPercentage > .75f)
                    {
                        return 8;
                    }
                    else if (parallaxPercentage > .50f)
                    {
                        return 4;
                    }
                    else if(parallaxPercentage > .25f)
                    {
                        return 2;
                    }
                    else
                    {
                        return 1;
                    }
                }
            });

            final View headerStickyView = rootView.findViewById(R.id.header_sticky);
            final View headerDynamicView = rootView.findViewById(R.id.header_dynamic);

            scrollView.addScrollPlugin(new ScrollPlugin(){

                @Override
                public void onScroll(int scrollY, int visibleHeight, int scrollableHeight) {

                    if (scrollY >= headerDynamicView.getTop())
                    {
                        headerDynamicView.setVisibility(View.INVISIBLE);
                        headerStickyView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        headerDynamicView.setVisibility(View.VISIBLE);
                        headerStickyView.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
