package com.jflavio1.daggerexample;

import android.app.Instrumentation;
import android.content.Context;
import androidx.fragment.app.DialogFragment;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
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
            true);   // launchActivity. False to customize the intent
    Context context;
    DialogsSampleActivity activity;
    Instrumentation.ActivityMonitor monitor;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        monitor = InstrumentationRegistry.getInstrumentation()
                .addMonitor(DialogsSampleActivity.class.getName(), null, false);
    }

    @Test
    public void dialogAttrsTest() {
        activity = (DialogsSampleActivity) monitor.waitForActivityWithTimeout(3000);
        Assert.assertNotNull("DialogsSampleActivity not started, activity is null", activity);

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

    }

    @Test
    public void dialogButtonClickTest() {
        activity = (DialogsSampleActivity) monitor.waitForActivityWithTimeout(3000);
        Assert.assertNotNull("DialogsSampleActivity not started, activity is null", activity);

        BaseCustomAlertDialog dialog = new CustomAlertDialog()
                .setTitle("titulo de ejemplo")
                .setDescription("descripcionnn")
                .setAcceptButtonText("aceptar")
                .setAcceptClickListener(DialogFragment::dismiss);

        dialog.showNow(activity.getSupportFragmentManager(), "dialog");

        onView(ViewMatchers.withId(R.id.dialogCustomFull_btn)).perform(ViewActions.click());

        Assert.assertTrue(dialog.isRemoving());

    }
}
