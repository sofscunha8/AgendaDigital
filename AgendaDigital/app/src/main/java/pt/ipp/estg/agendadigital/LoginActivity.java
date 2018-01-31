package pt.ipp.estg.agendadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login, registar;
    private EditText etUsername, etPassword, etEmail;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        db = new DbHelper(this);
        login = findViewById(R.id.btnLogin);
        registar = findViewById(R.id.btnRegisto);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        login.setOnClickListener(this);
        registar.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegisto:
                startActivity(new Intent(LoginActivity.this, RegistoActivity.class));
                break;
            default:

        }
    }

    private void login(){
        String pass = etPassword.getText().toString();
        String mail = etEmail.getText().toString();
        if(db.getUser(pass, mail)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
    }
}
