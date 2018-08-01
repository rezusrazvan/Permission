package com.example.student.permisiune;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button IDbt;
    private static final int UNIQUE_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IDbt = (Button) findViewById(R.id.IDbt);

        IDbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED)
                   {
                       ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},UNIQUE_REQUEST_CODE);
                   }
                else
                    {
                        Toast.makeText(MainActivity.this, "Permisiunea A fost acceptata", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == UNIQUE_REQUEST_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Ai dat permisiune", Toast.LENGTH_LONG).show();
            }
            else if (grantResults[0] == PackageManager.PERMISSION_DENIED)
            {

                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);

                    dialog.setMessage("Permisiunea ajuta aplicatia sa foloseasca camera, aceasta permisiune este esentiala");
                    dialog.setTitle("Permisiune Importanta!");

                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, UNIQUE_REQUEST_CODE);
                        }
                    });

                    dialog.setNegativeButton("Respinge", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Permisiunea nu a fost primita", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
                else
                    {
                        Toast.makeText(this, "Nu o sa mai afisam mesajul", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }
}
