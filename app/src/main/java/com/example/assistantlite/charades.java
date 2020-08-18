package com.example.assistantlite;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class charades extends AppCompatActivity {
    Button button;
    TextView t;
    TextView t2;
    String str;
    DatabaseReference reff;

    int present=-1,total=0,current=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charades);

        button= (Button) findViewById(R.id.button);
        t=(TextView) findViewById(R.id.task);
        t2=(TextView) findViewById(R.id.taskactual);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //.child("tasks").child(String.valueOf(present));
                reff = FirebaseDatabase.getInstance().getReference().child("tables");

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        present=Integer.parseInt(dataSnapshot.child("variables").child("task").getValue().toString());
                        total=Integer.parseInt(dataSnapshot.child("variables").child("maxtask").getValue().toString());
                        present=present%total+1;
                        if(current==-1) current=present;
                        else current=current%total+1;
                        //reff.child("variables").child("task").setValue(present);
                        String name= dataSnapshot.child("tasks").child(String.valueOf(current)).child("name").getValue().toString();
                        String type="you have to enact the " + dataSnapshot.child("tasks").child(String.valueOf(current)).child("type").getValue().toString();
                        t.setText(type);
                        t2.setText(name);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        reff=FirebaseDatabase.getInstance().getReference().child("tables").child("variables");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(current!=-1) reff.child("task").setValue(current);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        super.onBackPressed();

    }
}