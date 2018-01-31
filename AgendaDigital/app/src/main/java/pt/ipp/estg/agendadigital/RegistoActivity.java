package pt.ipp.estg.agendadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistoActivity extends AppCompatActivity implements View.OnClickListener{
    private Button registo;
    private TextView tvLogin;
    private EditText etUsername, etEmail, etPassword, confPassword;
    private DbHelper db;

    public RegistoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        db = new DbHelper(this);
        registo = findViewById(R.id.btnRegisto);
        tvLogin = findViewById(R.id.tvLogin);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        confPassword = findViewById(R.id.confPassword);
        registo.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvLogin:
                startActivity(new Intent(RegistoActivity.this, LoginActivity.class));
                break;
            case R.id.btnRegisto:
                registo();
                startActivity(new Intent(RegistoActivity.this, LoginActivity.class));
                break;
            default:
        }
    }

    private void registo(){
        String username = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String confPass = confPassword.getText().toString();
        if(username.isEmpty() && pass.isEmpty()){
            displayToast("Username/Password field empty");
        }else{
            if(pass.equals(confPass)){
                
                  db.addUser(username, pass, email);
                  displayToast("User Registado");
                  finish();


            }else{
                displayToast("Confirmar Password incorreto!");
            }

        }
    }



    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
