package it.feio.android.omninotes.mobileprism.screens;

import it.feio.android.omninotes.R;
import mobileprism.components.EspressoElement;
import mobileprism.components.PrismScreen;


public class OneNote extends PrismScreen {
    public static EspressoElement expandMenu() {
        return new EspressoElement("expand menu", R.id.fab_expand_menu_button);
    }
}
