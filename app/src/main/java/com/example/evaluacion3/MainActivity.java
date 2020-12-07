package com.example.evaluacion3;

import android.content.Intent;
import android.os.Bundle;

import com.example.evaluacion3.ui.main.model.Denuncia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.evaluacion3.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        auth = FirebaseAuth.getInstance();
        TextView text = findViewById(R.id.title);
        text.setText("Bienvenido " + auth.getCurrentUser().getEmail());

    }

    public void boton(View view) {
       // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
         //       .setAction("Action", null).show();

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncia");

        Denuncia denuncia = new Denuncia();
        denuncia.setId("1");
        denuncia.setTitulo("Tarea 1");
        denuncia.setDireccion("Chimbarongo");
        denuncia.setEstado(false);
        myRef.push().setValue(denuncia);
         */

        auth.signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }
}