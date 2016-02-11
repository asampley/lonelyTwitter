package ca.ualberta.cs.lonelytwitter;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.TextView;

/**
 * Created by sajediba on 2/8/16.
 */
public class IntentReaderActivityTest extends ActivityInstrumentationTestCase2{

    public IntentReaderActivityTest() {
        super(IntentReaderActivity.class);
    }

    //
    //
    public void testSendText() {
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "IntentReaderText1");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.NORMAL);

        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        assertTrue("IntentReaderActivity should get the text from intent!", intentReaderActivity.getText().equals("IntentReaderText1"));
    }

    //
    //

    public void testDisplayText() {
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "IntentReaderText2");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.NORMAL);

        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        TextView textView = (TextView)intentReaderActivity.findViewById(R.id.intentText);

        assertTrue("IntentReaderActivity TextView does not match", textView.getText().toString().equals("IntentReaderText2"));
    }

    public void testDoubleText() {
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "IntentReaderTextDouble");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.DOUBLE);

        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        assertTrue("IntentReaderActivity does not double string", intentReaderActivity.getText().equals("IntentReaderTextDoubleIntentReaderTextDouble"));
    }
    //TODO: Add your code here ...
//-------------------------------------------------------------------------------
    public void testReverseText() {
        Intent intent = new Intent();
        intent.putExtra(IntentReaderActivity.TEXT_TO_TRANSFORM_KEY, "IntentReaderText4");
        intent.putExtra(IntentReaderActivity.MODE_OF_TRANSFORM_KEY, IntentReaderActivity.REVERSE);

        setActivityIntent(intent);

        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        assertTrue("IntentReaderActivity does not reverse string", intentReaderActivity.getText().equals("4txeTredaeRtnetnI"));
    }

    public void testDefaultText() {
        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        assertTrue("IntentReaderActivity does not have correct default", intentReaderActivity.getText().equals("default value"));
    }

    public void testTextViewOnScreen() {
        IntentReaderActivity intentReaderActivity = (IntentReaderActivity)getActivity();

        TextView textView = (TextView)intentReaderActivity.findViewById(R.id.intentText);

        ViewAsserts.assertOnScreen(intentReaderActivity.getWindow().getDecorView(), textView);
    }
//-------------------------------------------------------------------------------
}
