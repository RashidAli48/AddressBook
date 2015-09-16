package com.rashidali.addressbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHandler dbHandler;
    Button addData,add;
    EditText name,address,cell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        add= (Button)findViewById(R.id.addContactButtonId);
        dbHandler = new MyDBHandler(this);
        name = (EditText)findViewById(R.id.editTextNameId);
        address = (EditText)findViewById(R.id.editTextAddressId);
        cell = (EditText)findViewById(R.id.editTextCellId);
        addData = (Button)findViewById(R.id.addDataButtonId);
    }

    public void addClicked(View view){

        Intent i = new Intent(this,addContact.class);
        startActivity(i);
    }



    public void deleteClicked(View view){
        Intent i = new Intent(this, deleteContact.class);
        startActivity(i);

    }
    public void viewClicked(View view){
        Cursor pointer = dbHandler.getAllData();
        if (pointer.getCount()==0) {
            showMessage("Error", "No any data found:");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (pointer.moveToNext()){
            buffer.append("ID : "+pointer.getString(0) + "\n");
            buffer.append("Name : "+pointer.getString(1)+ "\n");
            buffer.append("Address : "+pointer.getString(2)+ "\n");
            buffer.append("Cell No : "+pointer.getString(3)+ "\n\n");
        }
        showMessage("Contact List", buffer.toString());
    }


    public void showMessage(String title,String message){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setCancelable(true);
        popUp.setTitle(title);
        popUp.setMessage(message);
        popUp.show();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }
}
