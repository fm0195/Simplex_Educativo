package vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jjime.simplex.R;

public class AyudaPasosIntermedios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_pasos_intermedios);
    }
    public void Regresar(View view){
        finish();
    }
}
