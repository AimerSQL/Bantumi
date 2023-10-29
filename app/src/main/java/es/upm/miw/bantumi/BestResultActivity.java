package es.upm.miw.bantumi;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.upm.miw.bantumi.database.PuntuacionRepository;
import es.upm.miw.bantumi.view.PuntuacionListAdapter;

public class BestResultActivity extends AppCompatActivity {
    PuntuacionRepository puntuacionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_result);
        puntuacionRepository = new PuntuacionRepository((Application) getApplicationContext());

        RecyclerView rv = findViewById(R.id.best_result_recycler_view);
        final PuntuacionListAdapter adapter = new PuntuacionListAdapter(new PuntuacionListAdapter.PuntuacionDiff());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        puntuacionRepository.getAllPuntacion().observe(this, adapter::submitList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.best_result_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.opcBorrarTodo:
                showDeleteConfirmationDialog();
                return true;
            default:
                break;
        }
        return true;
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estas seguro de que quieres borrar todos los datos?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        puntuacionRepository.deleteAll();
                        Toast.makeText(BestResultActivity.this, "Borrado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
