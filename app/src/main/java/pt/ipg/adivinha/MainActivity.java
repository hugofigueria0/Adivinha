package pt.ipg.adivinha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private int numeroAdivinhar;
    private int tentativas;

    private EditText editTextNumero = null ;
    private TextView TextViewAcertou = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoJogo();
    }

    private void novoJogo() {
        numeroAdivinhar = random.nextInt(10) +1 ;
        tentativas = 0;
    }

    private EditText getEditTextNumero(){
        if(editTextNumero != null){
            editTextNumero= findViewById(R.id.editTextNumero);
        }
        return editTextNumero;
    }
    private TextView getTextViewAcertou(){
        if( TextViewAcertou == null){
            TextViewAcertou = findViewById(R.id.textViewAcertou);
        }
        return TextViewAcertou;
    }

    public void Adivinha(View view) {
        int numero = getNumero();
        if(numero < 1 || numero >10 ){

          getEditTextNumero().setError(getString(R.string.numeroInvalido));
            return;
        }

        if (numero == numeroAdivinhar){
            getTextViewAcertou().setText("Acertou");

        }else if (numero < numeroAdivinhar){
            getTextViewAcertou().setText("O numero que estou a pensar é maior");
        }else{
            getTextViewAcertou().setText("O numero que estou a pensar é menor");
        }
    }

    private int getNumero() {

        String s = getEditTextNumero().getText().toString();

        try {
           return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            editTextNumero.setError(getString(R.string.numeroInvalido));
            e.printStackTrace();
            return 0;
        }
    }
}