package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;

/**
 * Created by sajediba on 2/8/16.
 */
public class LonelyTwitterActivityUITest extends ActivityInstrumentationTestCase2 {

    Instrumentation instrumentation;
    Activity activity;
    EditText textInput;

    public LonelyTwitterActivityUITest() {
        super(LonelyTwitterActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instrumentation = getInstrumentation();
        activity = getActivity();
        textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
    }

    //makeTweet(text) fills in the input text field and clicks the 'save' button for the activity under test:
    private void makeTweet(String text) {
        assertNotNull(activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save));
        textInput.setText(text);
        ((Button) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save)).performClick();
    }

    //
    //
    @UiThreadTest
    public void testMakeTweet() {
        LonelyTwitterActivity lonelyTwitterActivity = (LonelyTwitterActivity)getActivity();
        int oldLength = lonelyTwitterActivity.getAdapter().getCount();
        makeTweet("Tweet1");
        ArrayAdapter<Tweet> arrayAdapter = lonelyTwitterActivity.getAdapter();
        Tweet lastItem = arrayAdapter.getItem(arrayAdapter.getCount() - 1);
        int newLength = lonelyTwitterActivity.getAdapter().getCount();

        assertTrue("No new tweet made!", newLength - oldLength == 1);
        assertTrue("New element is not a Tweet", arrayAdapter.getItem(arrayAdapter.getCount() - 1) instanceof Tweet);
        assertTrue("This is not the expected text", lastItem.getMessage().equals("Tweet1"));

    }
    //
    //
}
