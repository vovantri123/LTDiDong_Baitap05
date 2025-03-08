package vn.iostar.baitap05;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai báo biến toàn cục
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ListView và gọi adapter
        listView = (ListView) findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        adapter = new NotesAdapter(this, R.layout.row_note,arrayList);
        listView.setAdapter(adapter);

        // Gọi hàm databaseSQLite
        InitDatabaseSQLite();
        createDatabaseSQLite();
        databaseSQLite();




    }

    private void createDatabaseSQLite() {
        // Thêm dữ liệu vào database
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 1')");
        databaseHandler.QueryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 2')");
    }

    private void InitDatabaseSQLite() {
        // Khởi tạo database
        databaseHandler = new DatabaseHandler(this, "notes.sqlite", null, 1);

        // Tạo bảng Notes
        databaseHandler.QueryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    private void databaseSQLite() {
        // Lấy dữ liệu
        Cursor cursor = databaseHandler.GetData("SELECT * FROM Notes");
        while (cursor.moveToNext()) {
            // Thêm dữ liệu vào arrayList
            String name = cursor.getString(1);
            int id = cursor.getInt(0);
            arrayList.add(new NotesModel(id, name));
            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}
