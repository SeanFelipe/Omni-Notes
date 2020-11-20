package it.feio.android.omninotes.mobileprism.screens;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import it.feio.android.omninotes.R;
import it.feio.android.omninotes.mobileprism.templates.DeferredElement;
import it.feio.android.omninotes.mobileprism.templates.PrismElement;
import it.feio.android.omninotes.mobileprism.templates.PrismScreen;
import it.feio.android.omninotes.utils.date.SublimePickerFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

public class NewNoteScreen extends PrismScreen {
    public static PrismElement root = genel(R.id.detail_root);
    public static PrismElement title = genel(R.id.detail_title);
    public static PrismElement back = genel(withContentDescription("drawer open"));
    public static PrismElement reminderIcon = genel(R.id.reminder_icon);
    public static PrismElement reminderLayout = genel(R.id.reminder_layout);
    public static PrismElement timeHeader = genel(R.id.time_header);
    public static PrismElement toggleAM = genel(R.id.am_label);
    public static PrismElement togglePM = genel(R.id.pm_label);
    public static PrismElement timeSubmit = genel(R.id.buttonPositive, "Ok");
    public static PrismElement hours6 = genel(R.id.buttonPositive, "6");
    public static PrismElement sublime_root = genel(R.id.sublime_picker);
    public static PrismElement timePicker = genel(R.id.timePicker);
    public static PrismElement radialPicker = genel(R.id.radial_picker);


    //public static PrismElement timePicker = genFromSPFClass(SublimePickerFragment.class);

    private static PrismElement genFromSPFClass(Class<SublimePickerFragment> sublimePickerFragmentClass) {
        Matcher m = withClassName(Matchers.equalTo(sublimePickerFragmentClass.toString()));
        ViewInteraction vi = onView(m);
        return new PrismElement(vi);
    }

    /*
    Matcher timeOKMatcher = allOf(withId(R.id.buttonPositive), withText("Ok"), isDisplayed());
    ViewInteraction timeOK = onView(timeOKMatcher);

    Matcher listScreenTitleMatcher = allOf(withId(R.id.note_title), isDisplayed());
    ViewInteraction listPageTitle = onView(listScreenTitleMatcher);

    Matcher listPageContentMatcher = allOf(withId(R.id.note_content), isDisplayed());
    ViewInteraction listPageContent = onView(listPageContentMatcher);
     */

}
