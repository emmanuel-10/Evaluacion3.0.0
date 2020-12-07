package com.example.evaluacion3.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evaluacion3.R;
import com.example.evaluacion3.ui.main.model.Denuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DenunciarFragment extends Fragment {

    EditText txt_titulo,txt_direccion;
    Button button;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        txt_titulo = view.findViewById(R.id.fragment_denunciar_titulo);
        txt_direccion = view.findViewById(R.id.fragment_denunciar_direccion);
        button = view.findViewById(R.id.fragment_denunciar_boton);

        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txt_titulo.getText().toString();
                String direccion = txt_direccion.getText().toString();
                String uid = auth.getCurrentUser().getUid();

                if (titulo.isEmpty() || direccion.isEmpty()){
                    Toast.makeText(getActivity(),"faltan datos",Toast.LENGTH_LONG).show();
                }else{

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myref = database.getReference("denuncias").child(uid);

                    Denuncia denuncia = new Denuncia();
                    denuncia.setTitulo(titulo);
                    denuncia.setDireccion(direccion);
                    myref.push().setValue(denuncia);
                    Toast.makeText(getActivity(),"Denuncia Registrada",Toast.LENGTH_LONG).show();
                    txt_titulo.setText("");
                    txt_direccion.setText("");

                }
            }
        });

        return view;
    }
}