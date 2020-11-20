package it.feio.android.omninotes.mobileprism.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class Utils {
    static Activity AUT;
    static Resources resources;

    public static void setActivity(Activity a) {
        //Log.d("swolfe", "setActivity with a: " + a.toString());
        if ( AUT == null ) {
            AUT = a;
            resources = a.getResources();
        }
    }

    public static Activity getActivity() throws RuntimeException {
        if ( AUT == null ) {
            throw new RuntimeException("Activity AUT not initialized");
        } else {
            return AUT;
        }
    }

    public static View getView(int viewId) {
        View v =  AUT.findViewById(viewId);
        if ( v == null ) {
            throw new RuntimeException("could not find view for id " + viewId);
        } else {
            return v;
        }
    }

    public static String getResName(int rid) {
        return resources.getResourceName(rid);
    }

    public static String getResName(View v) {
        return resources.getResourceName(v.getId());
    }

    public static Matcher<View> withListSize(final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(final View view) {
                Log.d("swolfe", "ViewGroup getChildCount(): " + ((ViewGroup) view).getChildCount());
                return ((ViewGroup) view).getChildCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("ListView should have " + size + " items");
            }
        };
    }
}
