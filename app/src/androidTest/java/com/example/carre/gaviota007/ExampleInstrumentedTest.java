package com.example.carre.gaviota007;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;


/**
 * @author ernesto
 */

public class ExampleInstrumentedTest extends ActivityInstrumentationTestCase2<ActivityLogin> {

    private Button mloginbutton;
    private EditText etext1;
    private EditText etext2;

    public ExampleInstrumentedTest() {
        super(ActivityLogin.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        ActivityLogin actividad = getActivity();
        etext1 = actividad.findViewById(R.id.etLog_email);
        etext2 = actividad.findViewById(R.id.etLog_pass);
        mloginbutton = actividad.findViewById(R.id.log_in);

    }

//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }

    private static final String USERNAME = "ernesto";
    private static final String PASSWORD = "123456";

    public void testLoginSignup() {
        TouchUtils.tapView(this, etext1);
        getInstrumentation().sendStringSync(USERNAME);
//        // now on value2 entry
        TouchUtils.tapView(this, etext2);
        getInstrumentation().sendStringSync(PASSWORD);
        // now on Add button
        TouchUtils.tapView(this, mloginbutton);

    }
}

