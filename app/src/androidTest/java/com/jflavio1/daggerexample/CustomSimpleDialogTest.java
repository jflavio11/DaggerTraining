package com.jflavio1.daggerexample;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.jflavio1.daggerexample.core.components.alerts.BaseCustomAlertDialog;
import com.jflavio1.daggerexample.core.components.alerts.CustomAlertDialog;
import com.jflavio1.daggerexample.dialogssample.DialogsSampleActivity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

/**
 * CustomSimpleDialogTest
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 2/11/2019
 */
@RunWith(AndroidJUnit4.class)
public class CustomSimpleDialogTest {

    @Rule
    public ActivityTestRule<DialogsSampleActivity> activityRule = new ActivityTestRule<>(
            DialogsSampleActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent
    Context context;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        activityRule.launchActivity(null);
    }

    @Test
    public void dialogAttrsTest() {
        BaseCustomAlertDialog dialog = new CustomAlertDialog()
                .setTitle("titulo de ejemplo")
                .setDescription("descripcionnn")
                .setAcceptButtonText("aceptar")
                .setAcceptClickListener(DialogFragment::dismiss);

        Assert.assertNotNull(dialog.getTitle());
        Assert.assertNotNull(dialog.getDescription());
        Assert.assertEquals(0, dialog.getDrawableResId());
        Assert.assertNotNull(dialog.getAcceptButtonText());

        Assert.assertEquals("titulo de ejemplo", dialog.getTitle());
        Assert.assertEquals("descripcionnn", dialog.getDescription());
        Assert.assertEquals("aceptar", dialog.getAcceptButtonText());

        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            dialog.showNow(activityRule.getActivity().getSupportFragmentManager(), "dialog");
            Assert.assertEquals("titulo de ejemplo", dialog.getTitleTextView().getText());
            Assert.assertEquals("descripcionnn", dialog.getDescriptionTextView().getText());
            Assert.assertEquals("aceptar", dialog.getAcceptButton().getText());
        });

    }

    @Test
    public void dialogButtonClickTest() {
        BaseCustomAlertDialog dialog = new CustomAlertDialog()
                .setTitle("titulo de ejemplo")
                .setDescription("descripcionnn")
                .setAcceptButtonText("aceptar")
                .setAcceptClickListener(dialog1 -> dialog1.dismiss());

        InstrumentationRegistry.getInstrumentation().runOnMainSync(() ->
                dialog.showNow(activityRule.getActivity().getSupportFragmentManager(), "dialog"));

        onView(ViewMatchers.withId(R.id.dialogCustomFull_btn)).perform(ViewActions.click());

        Assert.assertTrue(dialog.isDismissed());

    }
}
