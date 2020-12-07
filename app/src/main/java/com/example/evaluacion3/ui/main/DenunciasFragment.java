package com.example.evaluacion3.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evaluacion3.R;
import com.example.evaluacion3.adapter.AdapterDenuncia;
import com.example.evaluacion3.ui.main.model.Denuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DenunciasFragment extends Fragment {

    TextView txt;
    FirebaseAuth auth;
    List<Denuncia> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_denuncias, container, false);

        txt = view.findViewById(R.id.recicler_todas_denuncias);
        auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();

        list = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Denuncia denuncia = ds.getValue(Denuncia.class);
                        denuncia.setId(ds.getKey());
                        list.add(denuncia);
                    }

                    for (Denuncia d : list){
                        txt.setText(txt.getText().toString() + d.getTitulo()+"\n");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        return view;
    }

}