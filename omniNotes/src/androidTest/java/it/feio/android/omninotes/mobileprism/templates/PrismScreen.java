package it.feio.android.omninotes.mobileprism.templates;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import it.feio.android.omninotes.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

public abstract class PrismScreen {
    //public static PrismElement genel(String ref, int rid) {
    //return new PrismElement(ref, rid);
    public static PrismElement genel(int rid) {
        return new PrismElement(rid);
    }

    public static PrismElement genel(String refName, int rid) {
        return new PrismElement(refName, rid);
    }

    public static PrismElement genel(int rid, String withText) {
        return new PrismElement(rid, withText);
    }

    public static PrismElement genel(Matcher m) {
        return new PrismElement(m);
    }

    public static DeferredElement deferredProto(int id) {
        return new DeferredElement(id);
    }

    public static PrismRVChildElement genAtPosition(int position, int listId) {
        return new PrismRVChildElement(position, listId);
    }

    public static PrismRecyclerView genlist(int listId) {
        return new PrismRecyclerView(listId);
    }

    public static PrismListElement genlist(int position, int id) {
        return new PrismListElement(position, id);
    }

    public static PrismListElement gend(int rid) {
        DataInteraction di = onData(anything()).inAdapterView(allOf(withId(R.id.list))).atPosition(0);
        return new PrismListElement(di);
    }

    public static PrismListElement first(int rid) {
        DataInteraction di = onData(anything()).inAdapterView(allOf(withId(R.id.list))).atPosition(0);
        return new PrismListElement(di);
    }

    private static Matcher<View> childAtPosition(
        final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                    && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
