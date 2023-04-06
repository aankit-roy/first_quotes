package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
     recycleradapter adapter;
     SearchView searchicon;
    ArrayList<data> al=new ArrayList<data>();
    AutoCompleteTextView autoco;
    ArrayAdapter<data> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchicon=(SearchView) findViewById(R.id.searchicon);//it should be above the recyclerView
        RecyclerView  recyclerView=findViewById(R.id.rcview);
        toolbar=findViewById(R.id.toolbar);

        //finding autocomplete textview;
//        autoco=findViewById(R.id.auto);

        tool_bar();//calling toolbar
        notificationmanager();//accessing notification from app;
        searching_icon();//calling search icon with recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//setting linear layout to front page

        al.add(new data(R.drawable.apj1,"A.P.J.Abdul Kalam"));
        al.add(new data(R.drawable.swami,"Swami Vivekanand"));
        al.add(new data(R.drawable.apj1,"Gautama Buddha"));
        al.add(new data(R.drawable.steve,"Steve Jobs"));
        al.add(new data(R.drawable.elon,"Elon Musk"));
        al.add(new data(R.drawable.sachine,"Sachine Tendulkar"));
        al.add(new data(R.drawable.ratan,"Ratan Tata"));
        al.add(new data(R.drawable.tagor,"Rabindranath Tagore"));
        al.add(new data(R.drawable.chanakya,"Chanakya"));
        al.add(new data(R.drawable.apj1,"Bruce Lee"));
        al.add(new data(R.drawable.apj1,"Albert Einstein"));
        al.add(new data(R.drawable.apj1,"Isaac Newto"));
        al.add(new data(R.drawable.apj1,"Mark Zuckerberg"));
        al.add(new data(R.drawable.apj1,"Shahrukh khan"));
        al.add(new data(R.drawable.tesla,"Nikola Tesla"));
        al.add(new data(R.drawable.apj1,"Alan Turing"));
        al.add(new data(R.drawable.singh,"Bhagat Singh"));
         adapter=new recycleradapter(this,al);
        recyclerView.setAdapter(adapter);
//        arrayAdapter=new ArrayAdapter<data>(getApplicationContext(), android.R.layout.simple_list_item_1,al);
//      recycleradapter  adapter1=new recycleradapter(this,al);
//        autoco.setAdapter(arrayAdapter);
//        autoco.setThreshold(1);//not working autocompletetextview;
//

    }
    public void tool_bar(){
        //step 1
        setSupportActionBar(toolbar);//linking action bar in mainactivity
        //step 2;
        //checking backward possible or not
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);//by default bacward arrow is enable in toolbar
            getSupportActionBar().setTitle("Qoutes");

        }



//        super.onBackPressed();
//        getSupportActionBar().setSubtitle("thought");//working but not required

    }
    private static final String CHANEL_ID="update thoughts";//settion chanel id;
    private  static final int NOTIFICATION_ID=100;//setting id of notification
    Notification notification;
    Intent inotify;
    //changing some style of notification manager
    private  static final int REQ_CODE=100;//  different  request code for different pending intent
    //pending intent requrired to move from external point to inside the app activity;
    //flag updaate current means update the previous notification
    public void notificationmanager(){
        //creating intent//for style of notification bar
        inotify=new Intent(getApplicationContext(),MainActivity.class);//this activity to move that activity at which want
        inotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//it helps to stop to go multiple time on home screen (means clearing top stack)
        PendingIntent pi=PendingIntent.getActivity(this,REQ_CODE,inotify,PendingIntent.FLAG_UPDATE_CURRENT);//this must be inside the class
        //of notification

        //******************************************************
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.bookno,null);//taking image from drawable
        BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;//storing in bitmapdrawable
        Bitmap lageicon=bitmapDrawable.getBitmap();//accessing bitmapdrwawable images

        NotificationManager nmanager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // first big picture style **************************
        Notification.BigPictureStyle bigPictureStyle=new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable)(ResourcesCompat.getDrawable(getResources(),R.drawable.apj1,null))).getBitmap())//image should be in jpeg
                //to show the background
                //shortcut to use bitmapdrawable ,without creating multiple variable
                .bigLargeIcon(lageicon)
                .setBigContentTitle("Dr.A.P.J. Abdul Klam")
                .setSummaryText("Top 100 Quotes of the Day!!\n some more greate person");

        //second inbox style
        Notification.InboxStyle inboxStyle=new Notification.InboxStyle()
                .addLine("Swami vivekananda")
                .addLine("chanakya")
                .addLine("Elon Musk")
                .addLine("Sachine Tendulakar");



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification= new Notification.Builder(this)
                   .setLargeIcon(lageicon)//must be in png form
                   .setSmallIcon(R.drawable.bookno)//it can be format
                   .setContentText("Enjoy with Quotes!")
                   .setSubText("Thouthts of the day")
                    .setAutoCancel(false)//this is for advanced verson
//                    .setOngoing(true)//for lower android verson//but working of both is same
                    //chanel is valid only when android verson is gratter then 8 so we put this in if condition
                    .setContentIntent(pi)//settion action of notification button
                    .setStyle(bigPictureStyle)//setting bigpicture style or Inbox style ,just
//                    .setStyle(inboxStyle)
                   .setChannelId(CHANEL_ID)
                   .build();//it should be present in condition
            //creating notification chanel and it contains three things(chanel id ,name,priority)
            nmanager.createNotificationChannel(new NotificationChannel(CHANEL_ID,"quotes",NotificationManager.IMPORTANCE_HIGH));
        }
        else{

            notification= new Notification.Builder(this)
                    .setLargeIcon(lageicon)//must be in png form
                    .setSmallIcon(R.drawable.bookno)//it can be format
                    .setContentText("Enjoy with Quotes!")
                    .setSubText(" Thoughts of the day")
                    .setAutoCancel(false)//it will not disappear after swaping notificatin right or left
//                    .setOngoing(true)
                    .setContentIntent(pi)//settion action of notification button
//                    .setStyle(inboxStyle)
                    .setStyle(bigPictureStyle)//setting bigpicture style
                    //chanel is valid only when android verson is gratter then 8 so we put this in if condition
                  //  .setChannelId(CHANEL_ID)not required chanel
                    .build();//it should be present in condition
            //creating notification chanel and it contains three things(chanel id ,name,priority)


        }
        nmanager.notify(NOTIFICATION_ID,notification);//notification id and notification variabel//most important line

    }
    public void searching_icon(){
        searchicon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}