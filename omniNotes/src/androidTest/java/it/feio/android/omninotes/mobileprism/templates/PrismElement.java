package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;

import it.feio.android.omninotes.mobileprism.actions.ActionFactory;
import it.feio.android.omninotes.mobileprism.actions.PerformTouchXYAction;
import it.feio.android.omninotes.mobileprism.utils.Utils;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class PrismElement {

    int aid = -1;
    String refName = "not yet defined";
    public ViewInteraction interaction;

    public PrismElement(int id) {
        aid = id;
        interaction = onView(withId(aid));
    }

    public PrismElement(int id, String withText) {
        aid = id;
        Matcher m = allOf(withId(aid), withText(withText), isDisplayed());
        interaction = onView(m);
    }


    public PrismElement(String n, int id) {
        refName = n;
        aid = id;
        interaction = onView(withId(aid));
    }

    public PrismElement(String msg) {
        Log.d("swolfe", "PrismElement constructor message: " + msg);
    }

    public PrismElement(ViewInteraction vi) {
        interaction = vi;
    }

    public PrismElement(Matcher m) {
        ViewInteraction vi = onView(m);
        interaction = vi;
    }

    public PrismElement(Matcher m1, Matcher m2, Matcher m3) {
        ViewInteraction vi = onView(allOf(m1, m2, m3));
        interaction = vi;
    }

    public void click() {
        //onView(withId(aid)).perform(ViewActions.click());
        interaction.perform(ViewActions.click());
    }

    public void touchEventXY(float x, float y) {
        interaction.perform(new PerformTouchXYAction(x, y));
        //View v = Utils.getView(aid);
        //v.performContextClick(x, y);
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

    public void performLogDownstreamAction() {
        interaction.perform(ActionFactory.logChildInfosAction());
    }

    public void logDownstreamViews() {
        logDownstreamViews(false);
    }

    public void logDownstreamViews(boolean follow) {
        Activity a = Utils.getActivity();
        Resources r = a.getResources();
        logDownstreamViews(a, r, follow);
    }

    private void logDownstreamViews(Activity a, Resources r, boolean follow) {
        logDownstreamViews(a, r, this.aid, follow, 0);
    }

    private void logDownstreamViews(Activity a, Resources r, int viewId, boolean follow, int recursionCount) {
        int MAX_RECURSIONS = 10;
        ViewGroup vg = (ViewGroup) a.findViewById(viewId);
        for (int i=0; i < vg.getChildCount(); i++ ) {
            View v = vg.getChildAt(i);
            int vid = v.getId();
            String className = v.getClass().toString();
            String resourceName = r.getResourceEntryName(vid);
            boolean isTextView = className.contains(("TextView"));
            Log.d("swolfe", "logging downstream encountered classname: " + className);
            if ( isTextView) {
                String t = ((TextView) v).getText().toString();
                Log.d("swolfe", String.format("found TextView %s with text: %s", resourceName, t));
            } else {
                try {
                    Log.d("swolfe", "child " + i + " class: " + vg.getChildAt(i).getClass() + " resName: " + resourceName);
                    if ( follow && recursionCount < MAX_RECURSIONS ) {
                        //Log.d("swolfe", "continuing downstream with resname: " + resourceName);
                        logDownstreamViews(a, r, vid, true, recursionCount + 1);
                    } else {
                        Log.d("swolfe", "logDownstreamViews stopping at recursionCount: " + recursionCount);
                    }
                } catch(ClassCastException cce) {
                    Log.d("swolfe", "logDownstreamViews() ClassCastException: " + cce.getMessage());
                } catch(Exception e) {
                    Log.d("swolfe", "logDownstreamViews() exception: " + e.getClass());
                    //Log.d("swolfe", String.join("\n", ExceptionUtils.getStackFrames(e)));
                }
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