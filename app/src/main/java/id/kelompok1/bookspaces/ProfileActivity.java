package id.kelompok1.bookspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.kelompok1.bookspaces.R;

public class ProfileActivity extends AppCompatActivity {
    private String strNik,strNama, strUsername, strJenisKelamin, strEmail, strAlamat, strMinatBaca, strId;
    private TextView nik, nama, username, jenis_kelamin, email, alamat, minat_baca;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = (ImageView)findViewById(R.id.icon_profile);
        nik = (TextView)findViewById(R.id.profile_nik);
        nama = (TextView)findViewById(R.id.profile_nama);
        username = (TextView)findViewById(R.id.profile_username);
        jenis_kelamin = (TextView)findViewById(R.id.profile_jk);
        email = (TextView)findViewById(R.id.profile_email);
        alamat = (TextView)findViewById(R.id.profile_alamat);
        minat_baca = (TextView)findViewById(R.id.profile_minatbaca);


        Intent getData = getIntent();
        strId = getData.getStringExtra("id");

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
}