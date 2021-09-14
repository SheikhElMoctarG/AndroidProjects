package Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitewithrecyclerview.MainActivity;
import com.example.sqlitewithrecyclerview.R;

import Controller.DatabaseHelper;
import Model.Data;

public class EditActivity extends AppCompatActivity {

    private EditText editId,editName,editAge,editAddress;
    private Button saveButton;
    private DatabaseHelper databaseHelper;
    Data personData;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editAddress = findViewById(R.id.editAddress);
        saveButton = findViewById(R.id.button);
        extra = getIntent().getExtras();
        databaseHelper = new DatabaseHelper(this);
        personData = databaseHelper.getData(extra.getInt("position"));
        editId.setText(String.valueOf(personData.getId()));
        editName.setText(personData.getName());
        editAge.setText(String.valueOf(personData.getAge()));
        editAddress.setText(personData.getAddress());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personData.setId(Integer.parseInt(editId.getText().toString()));
                personData.setName(editName.getText().toString());
                personData.setAge(Integer.parseInt(editAge.getText().toString()));
                personData.setAddress(editAddress.getText().toString());
                databaseHelper.updateData(personData);
                MainActivity.notifyAdapter();
                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}