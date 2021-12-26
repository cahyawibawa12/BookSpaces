package id.kelompok1.bookspaces;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.kelompok1.bookspaces.R;

public class ProfileActivity extends AppCompatActivity {
    private String strNik,strNama, strUsername, strJenisKelamin, strEmail, strAlamat, strMinatBaca, strId;
    private TextView nik, nama, username, jenis_kelamin, email, alamat, minat_baca;
    private ImageView profile;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);

        profile = (ImageView)findViewById(R.id.icon_profile);
        nik = (TextView)findViewById(R.id.profile_nik);
        nama = (TextView)findViewById(R.id.profile_nama);
        username = (TextView)findViewById(R.id.profile_username);
        jenis_kelamin = (TextView)findViewById(R.id.profile_jk);
        email = (TextView)findViewById(R.id.profile_email);
        alamat = (TextView)findViewById(R.id.profile_alamat);
        minat_baca = (TextView)findViewById(R.id.profile_minatbaca);

        btnLogout = findViewById(R.id.btnLogout);
        Intent getData = getIntent();
        strId = getData.getStringExtra("id");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogoutAlert();
            }
        });

        DBHelper dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.tampilkanPenggunaDariID(strId);

        while (cursor.moveToNext()) {
            strNik = cursor.getString(1);
            strNama = cursor.getString(2);
            strUsername = cursor.getString(6);
            strJenisKelamin = cursor.getString(4);
            strEmail = cursor.getString(5);
            strAlamat = cursor.getString(3);
            strMinatBaca = cursor.getString(8);
        }

        nik.setText(strNik);
        nama.setText(strNama);
        username.setText(strUsername);
        jenis_kelamin.setText(strJenisKelamin);
        email.setText(strEmail);
        alamat.setText(strAlamat);
        minat_baca.setText(strMinatBaca);
    }

    private void dialogLogoutAlert(){
        AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(ProfileActivity.this);
        dialogAlertBuilder.setTitle("Konfirmasi Logout");
        dialogAlertBuilder
          .setMessage("Apakah anda yakin untuk logout?")
          .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  sessionManager.removeSesion();
                  Intent moveToLogin = new Intent(ProfileActivity.this, LoginActivity.class);
                  moveToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(moveToLogin);
                  finish();
              }
          })
          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.cancel();
              }
          });
        AlertDialog dialog = dialogAlertBuilder.create();

        dialog.show();
    }
}