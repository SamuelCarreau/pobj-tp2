package ca.csf.pobj.tp2.romanNumber.roman;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import ca.csf.pobj.tp2.romanNumber.util.*;
import ca.csf.pobj.tp2.R;

public class RomanConversionActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        outputTextView = findViewById(R.id.outputTextView);

        setListener(inputEditText);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentRomanNumber",getOutput());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setOutput(savedInstanceState.getString("currentRomanNumber"));
    }

    public void onConvertButtonClicked(View view) {
        doConvertAction();
        hideKeyboard(this);
    }

    private void setListener(final EditText editText) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doConvertAction();
                }
                return false;
            }
        });
    }

    private void doConvertAction(){
        if (!haveError()) {
            setOutput(RomanConverter.ConvertToRoman(getInput()));
        }
    }

    private void setOutput(String value) {
        outputTextView.setText(String.valueOf(value));
    }

    private int getInput() {
        return NumberUtils.toInt(inputEditText.getText().toString());
    }

    private String getOutput(){
        return outputTextView.getText().toString();
    }

    private boolean haveError() {
        if (isInputBlank()) {
            showErrorMessage(getResources().getString(R.string.stringEmptyError));
        } else if (!isNumber()) {
            showErrorMessage(getResources().getString(R.string.stringNotNumberError));
        } else if (!isValid()) {
            showErrorMessage(getResources().getString(R.string.stringNotValidError));
        } else {
            return false;
        }
        setOutput(getString(R.string.stringEmpty));
        return true;
    }

    private boolean isInputBlank() {
        return StringUtils.isBlank((inputEditText.getText().toString()));
    }

    private boolean isNumber() {
        return NumberUtils.isNumber(inputEditText.getText().toString());
    }

    private boolean isValid() {
        int value = getInput();
        int minValue = 1;
        int maxValue = 4999;
        return (value >= minValue && value <= maxValue);
    }

    private void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.romanCoordinatorLayout), message, Snackbar.LENGTH_LONG)
                .show();
    }

    public static void hideKeyboard(Activity activity) {
        //Source : https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
