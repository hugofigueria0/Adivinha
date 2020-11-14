package pt.ipg.adivinha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private int numeroAdivinhar;
    private int tentativas;

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

    public void Adivinha(View view) {
        int numero = getNumero();
    }

    private int getNumero() {
        EditText editTextNumero = findViewById(R.id.editTextNumero);
        String s = editTextNumero.getText().toString();

        try {
           return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            editTextNumero.setError(getString(R.string.numeroInvalido));
            e.printStackTrace();
            return 0;
        }
    }
}