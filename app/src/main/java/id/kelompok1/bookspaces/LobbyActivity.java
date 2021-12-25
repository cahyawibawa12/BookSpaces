package id.kelompok1.bookspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.kelompok1.bookspaces.R;

public class LobbyActivity extends AppCompatActivity {
    private TextView label_name, label_alamat;
    private String nik, nama, username, jenis_kelamin, email, alamat, minat_baca, id;
    private ImageView profile, edukasi, ilmiah, fiksi, data_pinjam, lihat_pinjam, add_category;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        sessionManager = new SessionManager(this);

        if (sessionManager.getSession()==-1){
            Intent moveToLogin = new Intent(LobbyActivity.this, LoginActivity.class);
            moveToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
              Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(moveToLogin);
            finish();
        }

        profile = (ImageView)findViewById(R.id.display_picture);
        label_name = (TextView)findViewById(R.id.display_nama);
        label_alamat = (TextView)findViewById(R.id.display_alamat);
        edukasi = (ImageView)findViewById(R.id.cover3);
        ilmiah = (ImageView)findViewById(R.id.cover1);
        fiksi = (ImageView)findViewById(R.id.cover2);
        data_pinjam = (ImageView)findViewById(R.id.btn_pinjam);
        lihat_pinjam = (ImageView)findViewById(R.id.btn_list);
        add_category = (ImageView)findViewById(R.id.btn_tambah);

        Intent getData = getIntent();
        id = getData.getStringExtra("id");

        DBHelper dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.tampilkanPenggunaDariID(id);

        while (cursor.moveToNext()) {
            nama = cursor.getString(2);
            alamat = cursor.getString(3);
        }

//        nama = getData.getStringExtra("nama");
//        username = getData.getStringExtra("username");
//        jenis_kelamin = getData.getStringExtra("jeniskelamin");
//        email = getData.getStringExtra("email");
//        alamat = getData.getStringExtra("alamat");
//        minat_baca = getData.getStringExtra("minatbaca");

        label_name.setText(nama);
        label_alamat.setText(alamat);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(LobbyActivity.this, ProfileActivity.class);
                gotoProfile.putExtra("id", id);
                startActivity(gotoProfile);
            }
        });

        edukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goListEdukasi = new Intent(LobbyActivity.this,BukuEdukasiActivity.class);
                startActivity(goListEdukasi);
            }
        });

        ilmiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goListIlmiah = new Intent(LobbyActivity.this,BukuIlmiahActivity.class);
                startActivity(goListIlmiah);
            }
        });

        fiksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goListFiksi = new Intent(LobbyActivity.this,BukuFiksiActivity.class);
                startActivity(goListFiksi);
            }
        });

        data_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddPinjam = new Intent(LobbyActivity.this,PinjamActivity.class);
                startActivity(goAddPinjam);
            }
        });

        lihat_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goListPinjam = new Intent(LobbyActivity.this,ListPinjamActivity.class);
                startActivity(goListPinjam);
            }
        });

        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddKategori = new Intent(LobbyActivity.this,TambahBukuActivity.class);
                startActivity(goAddKategori);
            }
        });
    }
}