package com.example.quotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.viewholder> implements Filterable {
    Context context;
    private int lastposition=-1;
    ArrayList<data> al;
    ArrayList<data>backup;
    public recycleradapter(Context context,ArrayList<data>al){
        this.context=context;
        this.al=al;
        backup=new ArrayList<data>(al);//creating obj of arraylist
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.frontlayout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        //this method used to get data using constructor not getter and setter

        final data list_position=al.get(position);//storing all position of data;
        holder.image.setImageResource(al.get(position).imag);
        holder.name.setText(al.get(position).name);
        //set animation to item ,so call animation method
        setanimation(holder.itemView,position);
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),secondactivity.class);//getting context of main activity;
               i.putExtra("title",list_position.name);
               i.putExtra("image",list_position.imag);
//               i.putExtra("quotes",list_position.quotes);
                view.getContext().startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    //creating anoynomus inner class
    //two classes are 1)performaing 2)publish
    Filter filter=new Filter() {
        @SuppressLint("SuspiciousIndentation")
        @Override
        // for backgound thread
        protected FilterResults performFiltering(CharSequence keyword) {
            //creating blank arraylist to store searched data
            //keyword will be searcehed in backup arrarylist then ,whatever data matches
            //that data has to put in filtered arraylist
            ArrayList<data>filtereddata=new ArrayList<data>();
            //now start searching
            if(keyword.toString().isEmpty()){//if searching box is empty then we add all data of backup into filtereddata;
                filtereddata.addAll(backup);
            }
            else {
                //using for each loop to convert multiple obj into single obj;
                for(data obj:backup){
                    //now we can use any obj of backup(two obj-img,name,)
                    if(obj.name.toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                    filtereddata.add(obj);
                }
            }
            //after completing searching ,all data stored in filtereddata but we have to return
            //filterResults.so for that stored data in filterResults form
            //make an obj(results)
            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return  results;
            //now publishing results in nesxt class

        }
//        for UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //first cleare all data of data arraylist
            al.clear();
            al.addAll((ArrayList<data>)filterResults.values);
            //after competing it will notify that all data
            // whenever  we update adapter then it must be used
            //or
            notifyDataSetChanged();

        }
    };

    public class viewholder extends  RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        LinearLayout row;
        public  viewholder( @NonNull View itemview){
            super(itemview);
            name=itemview.findViewById(R.id.name);
            image=itemview.findViewById(R.id.image);
            row=itemview.findViewById(R.id.row);
        }
    }
    //method for setanimation to items of recycle view;
    private void setanimation(View viewtoanimate,int pos){
        if(pos>lastposition) {
            Animation slidein = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            viewtoanimate.startAnimation(slidein);
            lastposition=pos;
        }
    }

}
