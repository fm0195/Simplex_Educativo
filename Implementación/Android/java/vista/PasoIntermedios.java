package vista;

        import android.content.ClipboardManager;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.method.ScrollingMovementMethod;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.jjime.simplex.R;

        import controlador.AbstractControlador;
        import controlador.IVista;
        import controlador.SimplexControlador;
        import dto.DtoSimplex;

public class PasoIntermedios extends AppCompatActivity implements IVista {
    AbstractControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paso_intermedios);
        String problema = getIntent().getStringExtra("problema");
        boolean formato = getIntent().getBooleanExtra("formato", true);
        boolean tipoSolucion = getIntent().getBooleanExtra("tipoSolucion", true);
        controlador = new SimplexControlador();
        controlador.setVista(this);
        problema = problema.replaceAll("(?m)^[ \t]*\r?\n", "");
        if (tipoSolucion) {
            controlador.solucionar(problema, formato);
        } else {
            controlador.siguientePaso(problema, formato);
        }
        TextView resumen = (TextView) findViewById(R.id.areaResumen);
        resumen.setMovementMethod(new ScrollingMovementMethod());
        resumen.setSelected(true);
        Typeface currier = Typeface.createFromAsset(getApplicationContext().getAssets(), "cour.ttf");
        resumen.setTypeface(currier);
    }

    @Override
    public void mostrarMensajeError(String s, String s1) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle(s1);

        alertDialogBuilder
                .setMessage(s)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public void mostrarMatriz(DtoSimplex dtoSimplex) {
        mostrarResumen(controlador.generarResumen());
        mostrarOperaciones(controlador.siguientesOperaciones(dtoSimplex.getCoordenadaPivote()));
    }

    private void mostrarResumen(String resumen) {
        TextView campoResumen = (TextView) findViewById(R.id.areaResumen);
        int inicio = 0;
        campoResumen.setText(resumen.toCharArray(), inicio, resumen.length());
    }

    private void mostrarOperaciones(String operaciones) {
        EditText campoOperaciones = (EditText) findViewById(R.id.areaOperaciones);
        int inicio = 0;
        campoOperaciones.setText(operaciones.toCharArray(), inicio, operaciones.length());
    }

    @Override
    public void mostrarMensajeInformacion(String s, String s1) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(s1);
        builder1.setMessage(s);
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

    private void mensajeRapido(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void menu(String s, String s1) {
        if (s == null) {
            mensajeRapido("Se encuentra en el primer paso.");
            return;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Error");

        alertDialogBuilder
                .setMessage(s)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        salir(null);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        salir(null);
    }

    public void copiarResumen(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(((TextView) findViewById(R.id.areaResumen)).getText());
        mensajeRapido("Se han copiado en el portapapeles el resumen actual.");
    }

    public void copiarOperaciones(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(((TextView) findViewById(R.id.areaOperaciones)).getText());
        mensajeRapido("Se ha copiado en el portapapeles las operaciones.");
    }

    public void ayuda(View view){
        Intent llamado = new Intent(this, AyudaPasosIntermedios.class);
        startActivity(llamado);
    }

    public void salir(View view) {
        Intent llamado = new Intent(PasoIntermedios.this, PaginaInicial.class);
        startActivity(llamado);
        finish();
    }

    public void pasoAnterior(View view) {
        controlador.anteriorPaso();
    }

    public void pasoSiguiente(View view) {
        controlador.siguientePaso();
    }
}
