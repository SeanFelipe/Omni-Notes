package it.feio.android.omninotes.alt.prev;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.feio.android.omninotes.MainActivity;
import it.feio.android.omninotes.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListProto {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listProto() {
        ViewInteraction viewInteraction = onView(
            Matchers.allOf(ViewMatchers.withId(R.id.fab_expand_menu_button),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    3),
                isDisplayed()));
        viewInteraction.perform(click());

        ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.fab_note),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    2),
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
        editText.perform(click());

        ViewInteraction editText2 = onView(
            allOf(withId(R.id.detail_title),
                childAtPosition(
                    allOf(withId(R.id.title_wrapper),
                        childAtPosition(
                            withId(R.id.detail_tile_card),
                            0)),
                    1),
                isDisplayed()));
        editText2.perform(replaceText("listy list"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
            allOf(withId(R.id.detail_content),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    0)));
        editText3.perform(scrollTo(), replaceText("auspicious"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton = onView(
            allOf(withContentDescription("drawer open"),
                childAtPosition(
                    allOf(withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(is("android.widget.RelativeLayout")),
                            0)),
                    0),
                isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction frameLayout = onView(
            allOf(withId(R.id.root),
                childAtPosition(
                    allOf(withId(R.id.list),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            0)),
                    0),
                isDisplayed()));
        frameLayout.perform(click());
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
