package com.example.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class secondactivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        toolbar=findViewById(R.id.toolbar2);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
             tool_bar(title);//setting title of new page
        int image=intent.getIntExtra("image",0);
//        String[] quotes= intent.getStringArrayExtra("quotes");
//        getSupportActionBar().setTitle(quotes[0]);

//        getSupportActionBar().setIcon(image);//it is working



    }
    public void tool_bar(String title){
        //step 1
        setSupportActionBar(toolbar);//linking action bar in mainactivity
        //step 2;
        //checking backward possible or not
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//by default bacward arrow is enable in toolbar
            getSupportActionBar().setTitle(title);
        }
//        getSupportActionBar().setIcon(R.drawable.ic_baseline_share_24);//working but not right position

    }
//creating menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        new MenuInflater(this).inflate(R.menu.optionmenu,menu);//inflate (path of menu,where to set )
        //or  both code true but after using first line ,icon is not visible of optionmenu
        getMenuInflater().inflate(R.menu.optionmenu,menu);//inflate (path of menu,where to set )
        //for searching//in this activity not required search view

//        MenuItem searchitem=menu.findItem(R.id.search);
//        SearchView  searchVie= (SearchView) searchitem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();//gettion all id of menu option
        // now checking codintions;
        if(itemid==R.id.ho){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else if(itemid==R.id.sh){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }
        else if(itemid==android.R.id.home){
            super.onBackPressed();//backward direction move;
        }
        else
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}