package com.example.sheikhhamza.com_app;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class msg_adapter extends BaseAdapter  {
	private static LayoutInflater inflater = null;
    ArrayList<String> chatMessageList;
 
    public msg_adapter(Activity activity, ArrayList<String> list) {
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
    }
    public msg_adapter(Activity activity) {
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
    }
    @Override
    public int getCount() {
        return chatMessageList.size();
    }
 
    @Override
    public Object getItem(int position) {
        return position;
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String message = (String) chatMessageList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.chatlayout, null);
 
        TextView msg = (TextView) vi.findViewById(R.id.message_text);
        msg.setText(message);
        LinearLayout layout = (LinearLayout) vi
                .findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) vi
                .findViewById(R.id.bubble_layout_parent);
 
        // if message is mine then align to right
      /*  if (message.isMine) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);
        }
        // If not mine then align to left
        else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);
        }*/
        msg.setTextColor(Color.BLACK);
        return vi;
    }
 
    public void add(String object) {
        chatMessageList.add(object);
    }
}
