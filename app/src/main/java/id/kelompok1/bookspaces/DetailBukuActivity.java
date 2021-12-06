package id.kelompok1.bookspaces;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailBukuActivity extends AppCompatActivity {
    private String judul_buku, kategori_buku;
    private EditText judul, kategori;
    private Button btnSuntingBuku, btnHapusBuku;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        judul = (EditText)findViewById(R.id.value_detail_judul);
        kategori = (EditText)findViewById(R.id.value_detail_kategori);
        btnSuntingBuku = (Button)findViewById(R.id.btn_edit_buku);
        btnHapusBuku = (Button)findViewById(R.id.btn_hapus_buku);

        Intent getData = getIntent();
        id = getData.getIntExtra("id", 0);

        if (id > 0) {
            final DBHelper dh = new DBHelper(getApplicationContext());
            Cursor cursor = dh.detailBuku(id);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    judul.setText((cursor.getString(cursor.getColumnIndexOrThrow("judul"))));
                    kategori.setText((cursor.getString(cursor.getColumnIndexOrThrow("kategori"))));
                    cursor.moveToNext();
                }
                dh.close();
            }
        }

        btnSuntingBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul_buku = judul.getText().toString();
                kategori_buku = kategori.getText().toString();
                suntingAlert();
            }
        });

        btnHapusBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusAlert();
            }
        });
    }

    private void suntingAlert(){
        AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(DetailBukuActivity.this);
        dialogAlertBuilder.setTitle("Konfirmasi Suntingan");
        dialogAlertBuilder
                .setMessage("Judul : " +judul_buku+ "\n" +
                        "Kategori : " +kategori_buku+ "\n")
                .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper = new DBHelper(getApplicationContext());
                        BukuHandler bukuHandler = new BukuHandler();
                        bukuHandler.setJudul(judul_buku.toUpperCase());
                        bukuHandler.setKategori(kategori_buku.toUpperCase());

                        boolean suntingBuku = dbHelper.suntingBuku(bukuHandler, id);

                        if (suntingBuku) {
                            Toast.makeText(DetailBukuActivity.this, "Sunting Buku Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailBukuActivity.this, "Sunting Buku Gagal", Toast.LENGTH_SHORT).show();
                        }
                        dbHelper.close();

                        judul.getText().clear();
                        kategori.getText().clear();

                        Intent goListBuku = new Intent(DetailBukuActivity .this, LobbyActivity.class);
                        startActivity(goListBuku);
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

    private void hapusAlert(){
        AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(DetailBukuActivity.this);
        dialogAlertBuilder.setTitle("Konfirmasi");
        dialogAlertBuilder
                .setMessage("Yakin menghapus buku?")
                .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper = new DBHelper(getApplicationContext());

                        boolean hapusBuku = dbHelper.hapusBuku(id);

                        if (hapusBuku) {
                            Toast.makeText(DetailBukuActivity.this, "Hapus buku Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailBukuActivity.this, "Hapus buku Gagal", Toast.LENGTH_SHORT).show();
                        }
                        dbHelper.close();
                        Intent goListBuku = new Intent(DetailBukuActivity .this, LobbyActivity.class);
                        startActivity(goListBuku);
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