package com.pals.profile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Database extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ListView dbListView;
    ArrayAdapter<String> dbArrayAdapter;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        dbListView = (ListView) findViewById(R.id.list_db);
        dbHandler = new DatabaseHandler(this, null, null, 1);

        List<String> dbList = dbHandler.databaseToList();
        dbArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbList);

        dbListView.setAdapter(dbArrayAdapter);

        //dbListView.setOnItemLongClickListener(this);
        registerForContextMenu(dbListView);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        registerForContextMenu(dbListView);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0,"View Image");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemPosition = info.position;
        if(item.getItemId()==0){
            //Intent dbMenuIntent = new Intent(getApplicationContext(), Database.class);
            //startActivity(dbMenuIntent);
            Dialog settingsDialog = new Dialog(this);
            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.profile_pic_layout , null));
            settingsDialog.show();
            return true;
        } else
            return super.onContextItemSelected(item);
    }
}
