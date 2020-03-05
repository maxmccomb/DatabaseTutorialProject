package com.example.databasetutorialproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
    To do this tutorial yourself go to: https://www.youtube.com/watch?v=EM2x33g4syY
 */
public class MainActivity extends AppCompatActivity {



    EditText nameEntry;
    Spinner genreSpinner;
    Button addButton;
    TextView t;

    int zero = 0;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        nameEntry = findViewById(R.id.nameTextEdit);
        genreSpinner = findViewById(R.id.genreSpinner);
        addButton = findViewById(R.id.button);
        t = findViewById(R.id.textView);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addArtist();
                setText();
            }
        });
    }

    public void addArtist(){
       String name = nameEntry.getText().toString().trim();
       String genre = genreSpinner.getSelectedItem().toString();

       if(!TextUtils.isEmpty(name)){
           zero ++;
            String id = "" + zero;

            Artist artist = new Artist(name, genre, id);

            databaseArtists.child(id).setValue(artist);


           Toast.makeText(this, "Artist Added", Toast.LENGTH_LONG).show();

       }
       else{
           Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
       }
    }

    public void setText(){
        DatabaseReference ref = databaseArtists.child("-M1cvbVHNmuvqojIO0Wh");
        DatabaseReference ref2 = ref.child("artistName");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t.setText(""+dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
