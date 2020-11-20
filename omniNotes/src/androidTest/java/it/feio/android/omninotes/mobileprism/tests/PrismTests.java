package it.feio.android.omninotes.mobileprism.tests;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.feio.android.omninotes.MainActivity;
import it.feio.android.omninotes.mobileprism.screens.AlphaScreen;
import it.feio.android.omninotes.mobileprism.screens.NewNoteScreen;
import it.feio.android.omninotes.mobileprism.utils.Utils;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class PrismTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        //Log.d("swolfe", "init() @Before");
        Activity a = mActivityTestRule.getActivity();
        Utils.setActivity(a);
    }

    @Test
    public void proto() throws InterruptedException {
        String titleText = "prism root test ";
        AlphaScreen.expandMenu.click();
        AlphaScreen.fabNote.click();
        NewNoteScreen.reminderIcon.click();
        //NewNoteScreen.sublime_root.performLogDownstreamAction();
        NewNoteScreen.timePicker.click();
        //NewNoteScreen.radialPicker.click();
        NewNoteScreen.radialPicker.touchEventXY(300, 100);
        NewNoteScreen.radialPicker.touchEventXY(400, 200);
        NewNoteScreen.radialPicker.touchEventXY(500, 300);

        Thread.sleep(3000);
        //NewNoteScreen.root.interaction.perform(CustomActions.logChildInfosAction());
        //Thread.sleep(3000);
        //NewNoteScreen.timePicker.click();
        //Thread.sleep(8000);
        //NewNoteScreen.hours6.click();
        //Thread.sleep(8000);
        /*
        NewNoteScreen.title.set(titleText);
        //String t = NewNoteScreen.title.getText(mActivityTestRule);
        //Log.d("swolfe", "NewNote string: " + t);
        //NewNote.title.matchesText("prism title 11");
        String t = NewNoteScreen.titleProto.getText();
        Log.d("swolfe", "NewNoteScreen titleProto: " + t);
        NewNoteScreen.back.click();
        //AlphaScreen.notesList.sizeShouldBe(1);
        //AlphaScreen.notesList.sizeShouldBe(7);
        //AlphaScreen.firstNote.click();
        //NewNoteScreen.title.matchesText(titleText);
        Thread.sleep(3000);
        AlphaScreen.rooty.click();
        Thread.sleep(3000);
         */
    }

    /*

    @Test
    public void createNewNote() throws InterruptedException {
        String titleText = "prism title " + System.currentTimeMillis();
        AlphaScreen.expandMenu.click();
        AlphaScreen.optionsMenu.click();
        NewNoteScreen.title.set(titleText);
        //String t = NewNoteScreen.title.getText(mActivityTestRule);
        //Log.d("swolfe", "NewNote string: " + t);
        //NewNote.title.matchesText("prism title 11");
        NewNoteScreen.back.click();
        //AlphaScreen.notesList.logDownstreamViews(true);
        AlphaScreen.notesList.sizeShouldBe(1);
        //AlphaScreen.notesList.sizeShouldBe(7);
        //AlphaScreen.firstNote.click();
        //NewNoteScreen.title.matchesText(titleText);
        Thread.sleep(3000);
    }

    @Test
    public void rvProto() throws InterruptedException {
        AlphaScreen.firstNote.click();
        Thread.sleep(5000);
    }

    @Test
    public void createNoteWithReminder() throws InterruptedException {
        String titleText = "prism title " + System.currentTimeMillis();
        AlphaScreen.expandMenu.click();
        AlphaScreen.optionsMenu.click();
        NewNoteScreen.title.set(titleText);
        NewNoteScreen.reminderIcon.click();
        NewNoteScreen.reminderLayout.logDownstreamViews(true);
        Thread.sleep(2000);
        NewNoteScreen.timeSubmit.click();
        NewNoteScreen.back.click();
        Thread.sleep(5000);
        AlphaScreen.firstNote.click();
        NewNoteScreen.reminderLayout.logDownstreamViews(true);
        Thread.sleep(5000);
    }
*/

    /*

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
     */

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
