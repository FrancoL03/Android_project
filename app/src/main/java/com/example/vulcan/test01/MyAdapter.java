package com.example.vulcan.test01;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


//This is an adapter for create the RecyclerView
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> ListItems;
    private Context context;



/////////////////////////////////////////////////////////////////////////////////////


    public MyAdapter(List<ListItem> listItems, Context context) {
        ListItems = listItems;
        this.context = context;
    }







    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {//Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {//Called by RecyclerView to display the data at the specified position.
      final ListItem listItem = ListItems.get(i);

        int map_size = AlarmList.size();
        AlarmList.addAlarm(listItem.getItemName(),map_size);

        holder.textView_itemName.setText(listItem.getItemName());
        holder.textView_month.setText("Month: " + listItem.getMonth());
        holder.textView_day.setText("Date: " + listItem.getDay());
        holder.textView_hours.setText("Hours: " + listItem.getHours());
        holder.textView_minutes.setText("Minutes: " + listItem.getMinutes());
        holder.textView_musicname.setText("Music Names: "+ listItem.getMusicName());
        holder.textView_itemName.setOnClickListener(new View.OnClickListener() {//点击以后切换页面
            @Override
            public void onClick(View v) {
             Toast.makeText(context,"You clicked "+listItem.getItemName(),Toast.LENGTH_LONG).show();
//           Intent intent = new Intent(context, volley_json_parse_test.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//           context.startActivity(intent);
            }
        });

        holder.button_change_data.setOnClickListener(new View.OnClickListener() {//实现点击change按钮切换页面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Page_of_Change_Data.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ItemName",listItem.getItemName());//用于传递参数至下一个页面,key和value对应
                intent.putExtra("Month",listItem.getMonth());
                intent.putExtra("Day",listItem.getDay());
                intent.putExtra("Hours",listItem.getHours());
                intent.putExtra("Minutes",listItem.getMinutes());
                intent.putExtra("Music",listItem.getMusicName());


                Log.d("Debug,putEtra ItemName",listItem.getItemName() );
                context.startActivity(intent);
            }
        });


        holder.button_deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//实现点击delete按钮删除Item
                Intent intent = new Intent(context, Page_For_Delete_Item.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ItemName",listItem.getItemName());//用于传递参数
                Toast.makeText(context,"Do you want to delete "+listItem.getItemName()+" ?",Toast.LENGTH_LONG).show();
                Log.d("Debug,putEtra ItemName",listItem.getItemName() );
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {

        return ListItems.size();
    } //Returns the total number of items in the data set held by the adapter.

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_itemName; //实例化textview 以及buttons
        public TextView textView_month;
        public TextView textView_day;
        public TextView textView_hours;
        public TextView textView_minutes;
        public TextView textView_musicname;
        public Button button_change_data;
        public Button button_deleteData;


        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {//A ViewHolder describes an item view and metadata about its place within the RecyclerView.
            super(itemView);

            textView_itemName = (TextView) itemView.findViewById(R.id.textView_ItemName);
            textView_month = (TextView) itemView.findViewById(R.id.textView_Month);
            textView_day = (TextView) itemView.findViewById(R.id.textView_Day);
            textView_hours = (TextView) itemView.findViewById(R.id.textView_Hours);
            textView_minutes = (TextView) itemView.findViewById(R.id.textView_Minutes);
            textView_musicname = (TextView) itemView.findViewById(R.id.textView_MusicName);
            button_change_data = (Button) itemView.findViewById(R.id.button_changeData);
            button_deleteData = (Button) itemView.findViewById(R.id.button_deleteData);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
