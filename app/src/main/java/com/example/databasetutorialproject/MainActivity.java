package com.example.databasetutorialproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
    To do this tutorial yourself go to: https://www.youtube.com/watch?v=EM2x33g4syY
 */
public class MainActivity extends AppCompatActivity {



    EditText nameEntry;
    Spinner genreSpinner;
    Button addButton;

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        nameEntry = findViewById(R.id.nameTextEdit);
        genreSpinner = findViewById(R.id.genreSpinner);
        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addArtist();
            }
        });
    }

    public void addArtist(){
       String name = nameEntry.getText().toString().trim();
       String genre = genreSpinner.getSelectedItem().toString();

       if(!TextUtils.isEmpty(name)){
            String id = databaseArtists.push().getKey();

            Artist artist = new Artist(name, genre, id);

            databaseArtists.child(id).setValue(artist);

           Toast.makeText(this, "Artist Added", Toast.LENGTH_LONG).show();

       }
       else{
           Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
       }
    }

}
