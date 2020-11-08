package it.feio.android.omninotes;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FieldWireSandbox {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void fieldWireSandbox() {
        ViewInteraction viewInteraction = onView(
            allOf(withId(R.id.fab_expand_menu_button),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    3),
                isDisplayed()));
        viewInteraction.perform(click());

        ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.fab_checklist),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    1),
                isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction editText = onView(
            allOf(withId(R.id.detail_title),
                childAtPosition(
                    allOf(withId(R.id.title_wrapper),
                        childAtPosition(
                            withId(R.id.detail_tile_card),
                            0)),
                    1),
                isDisplayed()));
        editText.perform(replaceText("as"), closeSoftKeyboard());

        ViewInteraction linearLayout = onView(
            allOf(withId(R.id.reminder_layout),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    2)));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatCheckedTextView = onView(
            allOf(withId(R.id.pm_label), withText("PM"),
                childAtPosition(
                    allOf(withId(R.id.ampm_layout),
                        childAtPosition(
                            withId(R.id.time_header),
                            3)),
                    1),
                isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton = onView(
            allOf(withId(R.id.buttonPositive), withText("Ok"),
                childAtPosition(
                    allOf(withId(R.id.button_layout),
                        childAtPosition(
                            withId(R.id.llMainContentHolder),
                            2)),
                    5),
                isDisplayed()));
        appCompatButton.perform(click());
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
