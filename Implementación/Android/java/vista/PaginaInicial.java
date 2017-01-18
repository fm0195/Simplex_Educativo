package vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jjime.simplex.R;


public class PaginaInicial extends AppCompatActivity {

    private boolean solucionDirecta = true;
    private boolean fraccional = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_inicial);
    }

    @Override
    public void onResume(){
        super.onResume();
        EditText campoProblema = (EditText) findViewById(R.id.areaProblema);
        campoProblema.setText(new char[]{},0,0);
        ((RadioButton) findViewById(R.id.radio_directa)).setSelected(true);
        ((RadioButton) findViewById(R.id.radio_Fraccional)).setSelected(true);
    }

    public void ejecutar(View vie) {
        EditText campoProblema = (EditText) findViewById(R.id.areaProblema);
        String problema = campoProblema.getText().toString();
        if (problema.equals("")) {
            mostrarExcepcion("Error, el campo del problema no puede estar vacío.");
            return;
        }
        Intent llamado = new Intent(PaginaInicial.this, PasoIntermedios.class);
        llamado.putExtra("problema", problema);
        llamado.putExtra("formato", fraccional);
        llamado.putExtra("tipoSolucion", solucionDirecta);
        startActivity(llamado);
        finish();
    }

    public void mostrarExcepcion(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void acercaDe (View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Acerca de");
        builder1.setMessage("Simplex Educativo para plataforma móvil\n"+
                "Proyecto de Ingeniería de Software\n"+
                "Realizado por:\n\n" +
                "\tYordan Jiménez - jjimenez1937@gmail.com\n" +
                "\tFernando Molina - fm0105@gmail.com\n\n" +
                "Versión 1.0\n"+
                "Instituto Tecnológico de Costa Rica\n" +
                "Enero 2017");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void ayuda(View view){
        Intent llamado = new Intent(this, AyudaPaginaPrincipal.class);
        startActivity(llamado);
        finish();
    }

    public void setFraccional(View view) {
        fraccional = true;
    }

    public void setDecimal(View view) {
        fraccional = false;
    }

    public void setPorPasos(View view) {
        solucionDirecta = false;
    }

    public void setDirecta(View view) {
        solucionDirecta = true;
    }
}

