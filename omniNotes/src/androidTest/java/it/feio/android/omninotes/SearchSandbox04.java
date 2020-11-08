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
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchSandbox04 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchSandbox04() {
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
        editText.perform(replaceText("qux"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
            allOf(withId(R.id.detail_title), withText("qux"),
                childAtPosition(
                    allOf(withId(R.id.title_wrapper),
                        childAtPosition(
                            withId(R.id.detail_tile_card),
                            0)),
                    1),
                isDisplayed()));
        editText2.perform(click());

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

        ViewInteraction actionMenuItemView = onView(
            allOf(withId(R.id.menu_search), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        1),
                    0),
                isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
            allOf(withId(R.id.search_src_text),
                childAtPosition(
                    allOf(withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1)),
                    0),
                isDisplayed()));
        searchAutoComplete.perform(replaceText("qux"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
            allOf(withId(R.id.search_src_text), withText("qux"),
                childAtPosition(
                    allOf(withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1)),
                    0),
                isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction textView = onView(
            allOf(withId(R.id.note_title), withText("qux"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_layout),
                        1),
                    0),
                isDisplayed()));
        textView.check(matches(withText("qux")));
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
