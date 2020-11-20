package pt.ipg.adivinha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private TextView textViewTentativas = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            novoJogo();

        }else {
            numeroAdivinhar = savedInstanceState.getInt("NUM_ADIVINHAR");
            tentativas = savedInstanceState.getInt("TENTATIVAS");
            MostraTentativa();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("TENTATIVAS", tentativas);
        outState.putInt("NUM_ADIVINHAR", numeroAdivinhar);

        super.onSaveInstanceState(outState);
    }


    private void novoJogo() {
        numeroAdivinhar = random.nextInt(10) + 1 ;
        tentativas = 0;
        MostraTentativa();
        getTextViewAcertou().setText("");
        getEditTextNumero().setText("");
    }

    private EditText getEditTextNumero(){
        if(editTextNumero == null){
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

    public TextView getTextViewTentativas() {
        if( textViewTentativas == null){
            textViewTentativas = findViewById(R.id.textViewTentativas);
        }
        return textViewTentativas;
    }

    public void Adivinha(View view) {
        int numero = getNumero();
        if(numero < 1 || numero >10 ){

          getEditTextNumero().setError(getString(R.string.numeroInvalido));
            return;
        }

        tentativas++;

        MostraTentativa();

        if (numero == numeroAdivinhar){
            Acertou();

        }else if (numero < numeroAdivinhar){
            getTextViewAcertou().setText("O numero que estou a pensar é maior");
        }else{
            getTextViewAcertou().setText("O numero que estou a pensar é menor");
        }
    }

    private void MostraTentativa() {
        getTextViewTentativas().setText(getString(R.string.tentativas) + tentativas);
    }

    private void Acertou() {
        getTextViewAcertou().setText("Acertou");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acertou");
        builder.setMessage(R.string.jogar_novamente);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              novoJogo();
            }
        });

        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
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