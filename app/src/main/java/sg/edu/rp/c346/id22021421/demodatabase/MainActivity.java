package sg.edu.rp.c346.id22021421.demodatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import sg.edu.rp.c346.id22021421.demodatabase.DBHelper;
import sg.edu.rp.c346.id22021421.demodatabase.R;
import sg.edu.rp.c346.id22021421.demodatabase.Task;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    EditText etTask , etDate;
    TextView tvResults;
    ListView list;
    ArrayAdapter<Task> adapter;
    ArrayList<Task> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etDate = findViewById(R.id.etDates);
        etTask = findViewById(R.id.etTasks);
        list = findViewById(R.id.lv);
        al = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        list.setAdapter(adapter);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
               String date = etDate.getText().toString();
               String task = etTask.getText().toString();
                Task newTask = new Task(al.size(), task, date);
                al.clear();
                al.add(newTask);
                adapter.notifyDataSetChanged();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                al.addAll(db.getTasks());
                adapter.notifyDataSetChanged();
                db.close();


                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

            }
        });
    }
}
