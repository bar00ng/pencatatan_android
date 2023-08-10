// NIM      : 10120049
// Nama     : Mochammad Gymnastiar
// Kelas    : IF-2

package com.example.uas_akb_if2_10120049;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AddNoteActivity extends AppCompatActivity {
    private static final String TAG = "AddNoteActivity";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "createdOn";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DESCRIPTION = "description";

    private EditText titleInput;
    private EditText categoryInput;
    private EditText descInput;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = findViewById(R.id.form_add_title);
        categoryInput = findViewById(R.id.form_add_kategori);
        descInput = findViewById(R.id.form_add_desc);
    }

    public void saveNote(View v) {
        if (TextUtils.isEmpty(titleInput.getText())) {
            Toast.makeText(AddNoteActivity.this, "Title Wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(categoryInput.getText())) {
            Toast.makeText(AddNoteActivity.this, "Kategori Wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(descInput.getText())) {
            Toast.makeText(AddNoteActivity.this, "Desckripsi Wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        String collectionName = currentDate.replace("-", "_");

        String title = titleInput.getText().toString();
        String category = categoryInput.getText().toString();
        String desc = descInput.getText().toString();

        Map<String, Object> note = new HashMap<>();

        note.put(KEY_TITLE, title);
        note.put(KEY_CATEGORY, category);
        note.put(KEY_DESCRIPTION, desc);
        note.put(KEY_DATE, Timestamp.now());

        db.collection(collectionName).document().set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddNoteActivity.this, "Note berhasil disimpan!", Toast.LENGTH_SHORT).show();
                        resetInput();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNoteActivity.this, "Note Gagal disimpan!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    public void resetInput() {
        titleInput.setText("");
        categoryInput.setText("");
        descInput.setText("");
    }
}