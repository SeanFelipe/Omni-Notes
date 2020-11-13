package it.feio.android.omninotes.mobileprism.screens;

import it.feio.android.omninotes.R;
import it.feio.android.omninotes.mobileprism.templates.PrismElement;
import it.feio.android.omninotes.mobileprism.templates.PrismScreen;

import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

public class NewNoteScreen extends PrismScreen {
    public static PrismElement title = genel(R.id.detail_title);
    public static PrismElement back = genel(withContentDescription("drawer open"));
}
