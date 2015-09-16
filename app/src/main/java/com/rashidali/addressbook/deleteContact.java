package com.rashidali.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteContact extends AppCompatActivity {

    EditText name;
    Button del;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);
        name= (EditText)findViewById(R.id.delTextId);
        del = (Button)findViewById(R.id.delButtonId);
        dbHandler = new MyDBHandler(this);
    }

    public void finalDeleteClicked(View view){
        if (name.getText().toString().length() > 0){
            if (dbHandler.deleteContact(name.getText().toString())==1)
                Toast.makeText(deleteContact.this, "Contact Deleted Successfully", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(deleteContact.this, "Contact Not Found In List", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(deleteContact.this, "Enter Name First", Toast.LENGTH_LONG).show();

        }


    }


}
