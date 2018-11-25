package ca.csf.pobj.tp2.romanNumber.roman;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ca.csf.pobj.tp2.romanNumber.BaseActivity;
import ca.csf.pobj.tp2.romanNumber.util.*;
import ca.csf.pobj.tp2.R;

public class RomanConversionActivity extends BaseActivity {

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
        //BEN_CORRECTION : String aurait du être une constante.
        outState.putString("currentRomanNumber",getOutput());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //BEN_CORRECTION : String aurait du être une constante.
        setOutput(savedInstanceState.getString("currentRomanNumber"));
    }

    public void onConvertButtonClicked(View view) {
        doConvertAction();
        hideKeyboard(this);
    }

    //BEN_CORRECTION : Nommage ambigue. Set "listener" sur « quoi » ? Pour « quoi » ?
    private void setListener(final EditText editText) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //BEN_CORRECTION : Variable du nom de "v".
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
        //BEN_REVIEW : Effet de bord de la fonction. Elle ne devrait que valider. Or, elle valide
        //             et, en prime, modifie la sortie du programme.
        //
        //             De toute façon, semble inutile compte tenu que la sortie sera modifié
        //             immédiatement après.
        setOutput(getString(R.string.stringEmpty));
        return true;
    }

    private boolean isInputBlank() {
        return StringUtils.isBlank((inputEditText.getText().toString()));
    }

    //BEN_REVIWE : Nommage ambigu. isInputNumber ?
    private boolean isNumber() {
        return NumberUtils.isNumber(inputEditText.getText().toString());
    }

    //BEN_REVIWE : Nommage ambigu. isInputValid ? Même à cela, pas assez clair. isInputInBounds ?
    private boolean isValid() {
        int value = getInput();
        int minValue = 1; //BEN_CORRECTION : Valeur aurait du être une constante.
        int maxValue = 4999; //BEN_CORRECTION : Valeur aurait du être une constante.
        return (value >= minValue && value <= maxValue);
    }

    private void showErrorMessage(String message) {
        //BEN_REVIEW : Problème de performance. "findViewById" est très lent. Ne devrait être fait qu'au
        //             "onCreate".
        Snackbar.make(findViewById(R.id.romanCoordinatorLayout), message, Snackbar.LENGTH_LONG)
                .show();
    }




}
