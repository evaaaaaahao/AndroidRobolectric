package haohuiyan.robolectricdemo;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.lang.Exception;

import haohuiyan.robolectricdemo.FirstActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.Robolectric.shadowOf;

/**
 * Created by haohuiyan on 7/16/14.
 */
@RunWith(RobolectricTestRunner.class)
public class FirstActivityTest {
    private Button btnLaunch;
    private EditText etResut;
    private FirstActivity activity;

    @Before
    public void setup() throws Exception{
        activity = Robolectric.buildActivity(FirstActivity.class).create().visible().get();
        btnLaunch = (Button)activity.findViewById(R.id.btn);
        etResut = (EditText)activity.findViewById(R.id.myText);
    }

    @Test
    public void shouldHaveButtonThatSaysAudit() throws Exception{
        assertNotNull(btnLaunch);
        assertNotNull(etResut);
        assertEquals(btnLaunch.getText().toString(), "Click");
    }

    @Test
    public void pressingClickButtonForSecondActivity() throws Exception{
        String resultValue = "Testing Text";
        etResut.setText(resultValue);
        assertNotNull(btnLaunch);
        btnLaunch.performClick();
        Intent startedIntent = shadowOf(activity).getNextStartedActivity();
        assertEquals(resultValue, startedIntent.getStringExtra("result"));
        assertEquals(startedIntent.getComponent().getClassName(), SecondActivity.class.getName());
    }

    @After
    public void tearDown() throws Exception{

    }
}
