package id.kelompok1.bookspaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

import id.kelompok1.bookspaces.R;

public class BukuFiksiActivity extends AppCompatActivity {
    private DBHelper database;
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter bukuFiksiAdapter;
    private ArrayList<BukuHandler> bukuHandler = new ArrayList<BukuHandler>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku_fiksi);

        database = new DBHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.list_fiksi);

        final DBHelper dh = new DBHelper(getApplicationContext());
        Cursor cursor = dh.tampilkanBukuFiksi();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                BukuHandler bukuHandlerList = new BukuHandler();
                bukuHandlerList.setId((cursor.getInt(cursor.getColumnIndexOrThrow("id"))));
                bukuHandlerList.setJudul((cursor.getString(cursor.getColumnIndexOrThrow("judul"))));
                bukuHandlerList.setKategori((cursor.getString(cursor.getColumnIndexOrThrow("kategori"))));
                bukuHandler.add(bukuHandlerList);
                cursor.moveToNext();
            }
            dh.close();
        }

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        bukuFiksiAdapter = new BukuFiksiAdapter(bukuHandler, BukuFiksiActivity.this, recyclerView);
        recyclerView.setAdapter(bukuFiksiAdapter);
    }
}