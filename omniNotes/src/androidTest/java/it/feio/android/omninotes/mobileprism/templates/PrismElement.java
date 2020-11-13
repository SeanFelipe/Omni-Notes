package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class PrismElement {
    class AgnosticInteraction {
        DataInteraction dataInteraction;
        ViewInteraction viewInteraction;
        AgnosticInteraction(ViewInteraction vi) {
            viewInteraction = vi;
        }
        AgnosticInteraction(DataInteraction di) {
            dataInteraction = di;
        }
        void doPerform(ViewAction... viewActions) {
            if ( dataInteraction != null ) {
                for ( ViewAction va: viewActions ) {
                    dataInteraction.perform(va);
                }
            } else {
                for ( ViewAction va: viewActions ) {
                    viewInteraction.perform(va);
                }
            }
        }

        void doCheck(ViewAssertion va) {
            if ( dataInteraction != null ) {
                dataInteraction.check(va);
            } else {
                viewInteraction.check(va);
            }
        }
    }

    int aid = -1;
    AgnosticInteraction interaction;

    public PrismElement(int id) {
        aid = id;
        interaction = new AgnosticInteraction(onView(withId(aid)));
    }

    public PrismElement(DataInteraction di) {
        interaction = new AgnosticInteraction(di);
    }

    public PrismElement(Matcher m) {
        ViewInteraction vi = onView(m);
        interaction = new AgnosticInteraction(vi);
    }

    public void click() {
        //onView(withId(aid)).perform(ViewActions.click());
        interaction.doPerform(ViewActions.click());
    }

    public void set(String str) {
        //onView(withId(aid)).perform(typeText(str), closeSoftKeyboard());
        interaction.doPerform(ViewActions.typeText(str), ViewActions.closeSoftKeyboard());
        //viewInteraction.perform(typeText(str), closeSoftKeyboard());
    }

    public void matchesText(String text) {
        //onView(withId(aid)).check(matches(withText(text)));
        interaction.doCheck(matches(withText(text)));
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