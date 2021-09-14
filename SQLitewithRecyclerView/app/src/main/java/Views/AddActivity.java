package Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitewithrecyclerview.MainActivity;
import com.example.sqlitewithrecyclerview.R;

import Controller.DatabaseHelper;
import Model.Data;

public class AddActivity extends AppCompatActivity {
    private EditText editId,editName,editAge,editAddress;
    private Button saveButton;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        databaseHelper = new DatabaseHelper(this);
        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editAddress = findViewById(R.id.editAddress);
        saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(editId.getText().toString());
                String name = editName.getText().toString();
                int age = Integer.parseInt(editAge.getText().toString());
                String address = editAddress.getText().toString();
                databaseHelper.addData(new Data(id, name, age, address));
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}