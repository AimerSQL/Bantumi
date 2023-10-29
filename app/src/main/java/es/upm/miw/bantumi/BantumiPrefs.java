package es.upm.miw.bantumi;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import es.upm.miw.bantumi.entity.SettingEntity;

public class BantumiPrefs extends AppCompatActivity {
    public static SettingEntity getSetting(Context context) {
        try {
            FileInputStream in = context.openFileInput("Setting.json");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            inputStreamReader.close();

            String jsonLoad = sb.toString();
            Gson gsonLoad = new Gson();
            return gsonLoad.fromJson(jsonLoad, SettingEntity.class);
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                return new SettingEntity();
            }
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return new SettingEntity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        SettingEntity settingEntity = getSetting(this);

        EditText playerOneName = findViewById(R.id.playerOneName);
        playerOneName.setText(settingEntity.playerOneName);
        EditText playerTwoName = findViewById(R.id.playerTwoName);
        playerTwoName.setText(settingEntity.playerTwoName);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            settingEntity.playerOneName = playerOneName.getText().toString();
            settingEntity.playerTwoName = playerTwoName.getText().toString();

            Gson gson = new Gson();
            String jsonSave = gson.toJson(settingEntity);

            try {
                FileOutputStream outStream = openFileOutput("Setting.json", MODE_PRIVATE);
                outStream.write(jsonSave.getBytes(StandardCharsets.UTF_8));
                outStream.close();
                Toast.makeText(BantumiPrefs.this, "Guardado", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(BantumiPrefs.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
