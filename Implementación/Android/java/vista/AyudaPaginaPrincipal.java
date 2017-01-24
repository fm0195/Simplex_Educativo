package vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jjime.simplex.R;

public class AyudaPaginaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda_pagina_principal);
    }
    public void Regresar(View view){
        Intent llamado = new Intent(this,PaginaInicial.class);
        startActivity(llamado);
        finish();
    }
}
