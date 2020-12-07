package com.example.evaluacion3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaluacion3.ui.main.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText txt_email,txt_nombre,txt_celular,txt_clave;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_email = findViewById(R.id.register_email);
        txt_nombre = findViewById(R.id.register_nombre);
        txt_celular = findViewById(R.id.register_celular);
        txt_clave = findViewById(R.id.register_clave);

        auth = FirebaseAuth.getInstance();
    }

    public void createAccount(View view) {
        final String email,nombre,celular,clave;
        email = txt_email.getText().toString();
        nombre = txt_nombre.getText().toString();
        celular = txt_celular.getText().toString();
        clave = txt_clave.getText().toString();

        if (email.isEmpty() || nombre.isEmpty() || celular.isEmpty() || clave.isEmpty()){
            Toast.makeText(this,"COMPLETA LOS CAMPOS",Toast.LENGTH_LONG).show();
        }else{

            auth.createUserWithEmailAndPassword(email, clave)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("usuario");
                                User user = new User();
                                user.setEmail(email);
                                user.setNombre(nombre);
                                user.setCelular(celular);
                                user.setClave(clave);
                                user.setUid(task.getResult().getUser().getUid());

                                myRef.push().setValue(user);

                                Toast.makeText(RegisterActivity.this,"USUARIO REGISTRADO",Toast.LENGTH_LONG).show();
                                txt_email.setText("");
                                txt_nombre.setText("");
                                txt_celular.setText("");
                                txt_clave.setText("");
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                            }


                        }
                    });

        }
    }

    public void launchLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}