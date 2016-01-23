package accounts.accounts;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.*;
public class MainActivity extends Activity implements View.OnClickListener  {

    //A List of saved account data
    ArrayList<String> accounts = new ArrayList<String>();

    //A Class that handles saving and loading data to and from SharedPreferences
    SaveAndLoadManager saveAndLoad = new SaveAndLoadManager();

    //OnCreate function sets up listeners, loads saved data etc.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accounts = saveAndLoad.loadFromSharedPreferences(this);
        updateList();
        Button button;
        button = (Button) findViewById(R.id.saveButton);
        button.setOnClickListener(this);
        ListView listview = (ListView) findViewById(R.id.accountListView);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            // function that erases data row on long press
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                String selectedItem = accounts.get(pos);
                accounts.remove(selectedItem);
                updateList();
                saveAndLoad.saveToSharedPreferences(MainActivity.this, accounts);
                return true;
            }
        });
    }
    //Function that organizes account data and updates the ListView
    public void updateList() {
        Collections.sort(accounts);
        ListView ls = (ListView) findViewById(R.id.accountListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accounts);
        ls.setAdapter(arrayAdapter);
    }

    //Function that is called when 'save account data' is pressed
    public void onClick(View v) {
        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText accountText = (EditText) findViewById(R.id.accountText);
        String name = nameText.getText().toString();
        String number = accountText.getText().toString();
        nameText.setEnabled(false);
        accountText.setEnabled(false);

        //If data rows are not empty, data will be saved
        if (name.length() >= 1 && number.length() >= 1) {
            nameText.setText("");
            accountText.setText("");
            Account account = new Account(name, number);
            accounts.add(account.generateRow());
            updateList();
            saveAndLoad.saveToSharedPreferences(MainActivity.this, accounts);
        }

        //If data rows are empty, data will not be saved and an error message will be displayed
        else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Name or account number cannot be empty!");
            builder.setCancelable(true);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        nameText.setEnabled(true);
        accountText.setEnabled(true);
    }
}
