package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;

import it.feio.android.omninotes.mobileprism.utils.Utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PrismRecyclerView {

    int aid = -1;
    String refName = "not yet defined";
    ViewInteraction interaction;

    public PrismRecyclerView(int id) {
        aid = id;
        interaction = onView(withId(aid));
    }

    public PrismRecyclerView(String n, int id) {
        refName = n;
        aid = id;
        interaction = onView(withId(aid));
    }

    public void actionOnItemAtPosition(int position, ViewAction action) {
        RecyclerViewActions.actionOnItemAtPosition(position, action);
    }

    public void set(String str) {
        //onView(withId(aid)).perform(typeText(str), closeSoftKeyboard());
        interaction.perform(ViewActions.typeText(str), ViewActions.closeSoftKeyboard());
        //viewInteraction.perform(typeText(str), closeSoftKeyboard());
    }

    public void matchesText(String text) {
        //onView(withId(aid)).check(matches(withText(text)));
        interaction.check(matches(withText(text)));
    }

    public <T extends Activity> String getText(ActivityTestRule<T> activityTestRule)
        throws RuntimeException {
        // ints can't be null in java
        if ( aid == -1 ) {
            throw new RuntimeException("tried to PrismElement.getText(), but no android R.id found");
        }
        Activity a = activityTestRule.getActivity();
        TextView tv = (TextView) a.findViewById(aid);
        return "tv text: " + tv.getText().toString();
    }

    public View getViewViaActivity() {
        Activity a = Utils.getActivity();
        return a.findViewById(aid);
    }

    public void sizeShouldBe(int expectedSize) {
        /*
        ViewGroup vg = (ViewGroup) getViewViaActivity();
        int count = vg.getChildCount();
        Log.d("swolfe", refName + " sizeShould be: " + expectedSize + " found vg: " + count);
        */
        Matcher m = ViewMatchers.hasChildCount((expectedSize));
        interaction.check(matches(m));
    }

    public void logDownstreamViews() {
        logDownstreamViews(false);
    }

    public void logDownstreamViews(boolean follow) {
        Activity a = Utils.getActivity();
        Resources r = a.getResources();
        logDownstreamViews(a, r, follow);
    }

    public void logDownstreamViews(Activity a, Resources r, boolean follow) {
        logDownstreamViews(a, r, this.aid, follow, 0);
    }

    public void logDownstreamViews(Activity a, Resources r, int viewId, boolean follow, int recursionCount) {
        int MAX_RECURSIONS = 10;
        ViewGroup vg = (ViewGroup) a.findViewById(viewId);
        for (int i=0; i < vg.getChildCount(); i++ ) {
            int vid = vg.getChildAt(i).getId();
            try {
                String resourceName = r.getResourceEntryName(vid);
                Log.d("swolfe", "child " + i + " class: " + vg.getChildAt(i).getClass() + " resName: " + resourceName);
                if (follow) {
                    if (recursionCount < MAX_RECURSIONS) {
                        Log.d("swolfe", "continuing downstream with resname: " + resourceName);
                        logDownstreamViews(a, r, vid, true, recursionCount + 1);
                    } else {
                        Log.d("swolfe", "logDownstreamViews stopping at recursionCount: " + recursionCount);
                    }
                }
            } catch(Exception e) {
                Log.d("swolfe", "child " + i + " class: " + vg.getChildAt(i).getClass() + " resName: not found");
            }

        }
    }

    /*
    public String getText() {
        final String[] out = new String[1];
        interaction.doPerform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                //return isAssignableFrom(TextView.class);
                return null;
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view; //Save, because of check in getConstraints()
                out[0] = tv.getText().toString();
            }
        });
        return out[0];
    }

     */
    /*
    public String getText() {
        interaction.doPerform(new ActionGetTextViewText());

        }
    }

    public void click() {
        //onView(withId(aid)).perform(ViewActions.click());
        if ( dataInteraction != null ) {
            Log.d("swolfe", "dataInteraction click()");
            dataInteraction.perform(ViewActions.click());
        } else {
            Log.d("swolfe", "viewI click()");
            viewInteraction.perform(ViewActions.click());
        }
    }

    public void getText() {
        if ( dataInteraction != null ) {
            Log.d("swolfe", "dataInteraction click()");
            dataInteraction.perform(ViewActions.click());
        } else {
            Log.d("swolfe", "viewI click()");
            viewInteraction.perform(ViewActions.click());
        }

    }

    public void set(String str) {
        //onView(withId(aid)).perform(typeText(str), closeSoftKeyboard());
        if ( dataInteraction != null ) {
            dataInteraction.perform(ViewActions.typeText(str), ViewActions.closeSoftKeyboard());
        } else {
            viewInteraction.perform(ViewActions.typeText(str), ViewActions.closeSoftKeyboard());
        }
        //viewInteraction.perform(typeText(str), closeSoftKeyboard());
    }

    public void matchesText(String text) {
        onView(withId(aid)).check(matches(withText(text)));
    }
    */
}