package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import it.feio.android.omninotes.R;
import it.feio.android.omninotes.mobileprism.utils.Utils;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anything;

public class PrismListElement {
    int aid = -1;
    DataInteraction interaction;

    /*
    public PrismDataElement(int id) {
        aid = id;
        interaction = new DataInteraction(onView(withId(aid)));
    }
     */

    public PrismListElement(int position, int id) {
        aid = id;
        // use the first list
        //interaction = onData(anything()).inAdapterView(allOf(withId(aid))).atPosition(0);
        DataInteraction di = onData(anything())
            .inAdapterView(allOf(withId(R.id.list)))
            .atPosition(position);
        interaction = di;
    }

    public PrismListElement(DataInteraction di) {
        interaction = di;
    }

    public void click() {
        //onView(withId(aid)).perform(ViewActions.click());
        interaction.perform(ViewActions.click());
    }

    public void sizeShouldBe(int expectedSize) {
        //Activity a = Utils.getActivity();
        //ViewGroup vg = a.findViewById(aid);
        //int count = vg.getChildCount();
        //Log.d("swolfe", "sizeShould be: " + expectedSize + " found vg: " + count);
        //Matcher m = Utils.withListSize(aid, expectedSize);
        Matcher m = ViewMatchers.hasChildCount((expectedSize));
        interaction.check(matches(m));
    }

    private static Matcher<View> childAtPosition(
        final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position);
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