package jameshigashiyama.com.cryptotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView output;
    static String out;
    //String TEST_STRING = "abcdefghijklmnopqrstuvwxyz";
    //static String PASSKEY = "thePasswordIsPassword";
    static byte[] SALT = {1, 2, 4, 5 };
    static int ITERATIONS = 1979;

    protected EditText mDataField;
    protected EditText mPassword;
    protected Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView)findViewById(R.id.outputTextView);
        mDataField = (EditText)findViewById(R.id.dataEditText);
        mPassword = (EditText)findViewById(R.id.passwordEditText);
        goButton = (Button)findViewById(R.id.goButton);



        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = mPassword.getText().toString();
                String data = mDataField.getText().toString();

                out = "";
                runTest(password, data);
                output.setText(out);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void runTest(String pass, String data) {
        out = "Test string: " + pass + "\n";
        out += "Passkey: " + data + "\n";
        try {
            Crypto crypto = new Crypto(pass);
            String encryptedData = crypto.encrypt(data);
            out += "Encrypted: " + encryptedData + "\n";
            out += "Decrypted: " + crypto.decrypt(encryptedData);
        } catch (Exception e) {
            out += "Error: " + e.getMessage() + "\n";
            e.printStackTrace();
        }
    }
}
