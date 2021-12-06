package id.kelompok1.bookspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.kelompok1.bookspaces.R;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText username, password;
    private TextView daftar;
    private String strUsername, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btn_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        daftar = (TextView)findViewById(R.id.link_daftar);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(getApplicationContext());
                strUsername = username.getText().toString();
                strPassword = password.getText().toString();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Lengkapi Username atau Password!", Toast.LENGTH_SHORT).show();
                } else if (db.cekUsername(strUsername)) {
                    if (db.cekUsernameDanPassword(strUsername, strPassword)>0){
                        Intent gotoLoby = new Intent(LoginActivity.this, LobbyActivity.class);
                        gotoLoby.putExtra("id", String.valueOf(db.cekUsernameDanPassword(strUsername, strPassword)));
                        startActivity(gotoLoby);
                    }else{
                        Toast.makeText(LoginActivity.this, "Password Salah!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Username Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegist = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(gotoRegist);
            }
        });
    }
}