package com.rashidali.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addContact extends AppCompatActivity {


    MyDBHandler dbHandler;
    Button addData,add;
    EditText name,address,cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayout);

        add= (Button)findViewById(R.id.addContactButtonId);
        dbHandler = new MyDBHandler(this);
        name = (EditText)findViewById(R.id.editTextNameId);
        address = (EditText)findViewById(R.id.editTextAddressId);
        cell = (EditText)findViewById(R.id.editTextCellId);
        addData = (Button)findViewById(R.id.addDataButtonId);


    }

    public void addDBClicked(View view){

        String no = cell.getText().toString();
        int len=0,check=0;

        if (cell.getText().toString().length()==11) {
            while (len < no.length()) {

                char character = no.charAt(len); // This gives the character 'a'
                int ascii = (int) character;
                if (ascii > 57 || ascii < 48) {
                    check = 1;
                    break;
                }
                len++;
            }
            if (check == 0) {
                if (name.getText().toString().length() > 0 && address.getText().toString().length() > 0){

                    boolean isInserted = dbHandler.insertData(name.getText().toString(), address.getText().toString(), cell.getText().toString());
                    if (isInserted == true) {
                        name.setText("");
                        address.setText("");
                        cell.setText("");
                        Toast.makeText(addContact.this, "Contact Added Successfully", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(addContact.this, "Contact Not Saved", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(addContact.this, "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(addContact.this, "You Entered Cell No Should  Not Contain Alphabets", Toast.LENGTH_LONG).show();
            }
        }

        else {
            if (name.getText().toString().length()==0 ||  address.getText().toString().length() ==0)
                Toast.makeText(addContact.this, "Please Fill All Fields" , Toast.LENGTH_LONG).show();
            else
                Toast.makeText(addContact.this, "Entered Cell No Is Wronge" , Toast.LENGTH_LONG).show();
        }
    }




}
