// NIM      : 10120049
// Nama     : Mochammad Gymnastiar
// Kelas    : IF-2

package com.example.uas_akb_if2_10120049;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;


public class AddNoteActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText categoryInput;
    private EditText descInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = findViewById(R.id.form_add_title);
        categoryInput = findViewById(R.id.form_add_kategori);
        descInput = findViewById(R.id.form_add_desc);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    public void saveNote() {
        String title = titleInput.getText().toString();
        String category = categoryInput.getText().toString();
        String desc = descInput.getText().toString();

        if (title.trim().isEmpty() || desc.trim().isEmpty() || category.trim().isEmpty()) {
            Toast.makeText(AddNoteActivity.this, "Semua Field harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate);

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Notes");
        collectionReference.add(new Note(title, category, desc, timestamp));
        Toast.makeText(this, "Berhasil menambah Note!", Toast.LENGTH_SHORT).show();
        resetInput();
        finish();

    }

    public void resetInput() {
        titleInput.setText("");
        categoryInput.setText("");
        descInput.setText("");
    }
}