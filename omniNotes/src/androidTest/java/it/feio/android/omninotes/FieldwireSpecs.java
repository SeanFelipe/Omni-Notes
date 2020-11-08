package it.feio.android.omninotes;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FieldwireSpecs {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verifyCreateNewNote() {
        Matcher expandMenuMatcher = allOf(withId(R.id.fab_expand_menu_button), isDisplayed());
        ViewInteraction expandMenu = onView(expandMenuMatcher);

        Matcher noteMenuOptionMatcher = allOf(withId(R.id.fab_note), isDisplayed());
        ViewInteraction createNote = onView(noteMenuOptionMatcher);

        Matcher titleMatcher = allOf(withId(R.id.detail_title), isDisplayed());
        ViewInteraction title = onView(titleMatcher);

        Matcher contentMatcher = allOf(withId(R.id.detail_content), isDisplayed());
        ViewInteraction content = onView(contentMatcher);

        Matcher reminderButtonMatcher = allOf(withId(R.id.reminder_layout),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.LinearLayout")),
                    1),
                2), isDisplayed());
        ViewInteraction reminderButton = onView(contentMatcher);

        Matcher timePMMatcher = allOf(withId(R.id.pm_label), isDisplayed());
        ViewInteraction timePM = onView(timePMMatcher);

        Matcher timeOKMatcher = allOf(withId(R.id.buttonPositive), withText("Ok"), isDisplayed());
        ViewInteraction timeOK = onView(timeOKMatcher);

        Matcher listScreenTitleMatcher = allOf(withId(R.id.note_title), isDisplayed());
        ViewInteraction listPageTitle = onView(listScreenTitleMatcher);

        Matcher listPageContentMatcher = allOf(withId(R.id.note_content), isDisplayed());
        ViewInteraction listPageContent = onView(listPageContentMatcher);

        Matcher backArrowMatcher = allOf(withContentDescription("drawer open"),
            childAtPosition(
                allOf(withId(R.id.toolbar),
                    childAtPosition(
                        withClassName(is("android.widget.RelativeLayout")),
                        0)),
                0),
            isDisplayed());
        ViewInteraction backArrow = onView(backArrowMatcher);


        expandMenu.perform(click());
        createNote.perform(click());
        title.perform(replaceText("Press the red button"));
        content.perform(scrollTo(), replaceText("Should blow up"), closeSoftKeyboard());

        //title.perform(replaceText("Press the blue button"));
        //content.perform(scrollTo(), replaceText("Should survive"), closeSoftKeyboard());

        reminderButton.perform(scrollTo(), click());

        ViewInteraction linearLayout = onView(
            allOf(withId(R.id.reminder_layout),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    2), isDisplayed()));
        linearLayout.perform(scrollTo(), click());


        timePM.perform(click());
        timeOK.perform(click());
        backArrow.perform(click());

        listPageTitle.check(matches(withText("Press the red button")));
        listPageContent.check(matches(withText("Should blow up")));
    }


    @Test
    public void verifySearchByTitle() {
        Matcher searchButtonMatcher = allOf(withId(R.id.menu_search), isDisplayed());
        ViewInteraction searchButton = onView(searchButtonMatcher);

        Matcher searchQueryMatcher =  allOf(withId(R.id.search_src_text),
            childAtPosition(
                allOf(withId(R.id.search_plate),
                    childAtPosition(
                        withId(R.id.search_edit_frame),
                        1)),
                0),
            isDisplayed());
        ViewInteraction searchQuery = onView(searchQueryMatcher);

        searchButton.perform(click());
        searchQuery.perform(replaceText("searchable"), closeSoftKeyboard());
        searchQuery.perform(pressImeActionButton());

        ViewInteraction textView = onView(
            allOf(withId(R.id.note_title), withText("searchable"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.card_layout),
                        1),
                    0),
                isDisplayed()));
        textView.check(matches(withText("searchable")));
    }

    @Test
    public void verifySearchByContent() {
        Matcher searchButtonMatcher = allOf(withId(R.id.menu_search), isDisplayed());
        ViewInteraction searchButton = onView(searchButtonMatcher);

        Matcher searchQueryMatcher =  allOf(withId(R.id.search_src_text),
            childAtPosition(
                allOf(withId(R.id.search_plate),
                    childAtPosition(
                        withId(R.id.search_edit_frame),
                        1)),
                0),
            isDisplayed());
        ViewInteraction searchQuery = onView(searchQueryMatcher);

        searchButton.perform(click());
        searchQuery.perform(replaceText("contentable"), closeSoftKeyboard());
        searchQuery.perform(pressImeActionButton());

        ViewInteraction textView = onView(
            allOf(withId(R.id.note_content), withText("contentable"), isDisplayed()));
        textView.check(matches(withText("contentable")));
    }

    @Test
    public void verifyEdit() {
        String timestamp = "" + System.currentTimeMillis();

        // first item in list
        DataInteraction firstItem = onData(anything())
            .inAdapterView(allOf(withId(R.id.list),
                childAtPosition(
                    withClassName(is("android.widget.FrameLayout")),
                    0)))
            .atPosition(0);
        firstItem.perform(click());

        ViewInteraction noteDetails = onView(
            allOf(withId(R.id.detail_content),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    0)));
        noteDetails.perform(scrollTo(), replaceText("editing  " + timestamp), closeSoftKeyboard());

        ViewInteraction appCompatImageButton2 = onView(
            allOf(withContentDescription("drawer open"),
                childAtPosition(
                    allOf(withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(is("android.widget.RelativeLayout")),
                            0)),
                    0),
                isDisplayed()));
        appCompatImageButton2.perform(click());

        firstItem.perform(click());
        noteDetails.check(matches(withText("editing " + timestamp)));
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
