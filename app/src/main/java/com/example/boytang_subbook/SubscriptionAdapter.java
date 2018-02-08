package com.example.boytang_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Represents a custom adapter used to make the layouts work
 *
 * Class taken from https://stackoverflow.com/questions/30371684/listview-displaying-objects-details
 * Accessed on February 7, 2018
 * Modified by me to fit requirements of my code
 *
 * @author Diane B
 *
 * @version 1.0
 */

public class SubscriptionAdapter extends ArrayAdapter<Subscription> {

    //Attributes
    private Context context;
    private int resource;
    private ArrayList<Subscription> data;


    //Constructor
    public SubscriptionAdapter(Context context, int resource, ArrayList<Subscription> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Subscription subscription = getItem(position);
        View row = null;

        LayoutInflater inflater = LayoutInflater.from(context);

        if (convertView == null){
            convertView = inflater.inflate(resource, parent, false);
        }

        TextView subName = (TextView) convertView.findViewById(R.id.textViewname);
        subName.setText(subscription.getName());

        TextView subDate = (TextView) convertView.findViewById(R.id.textViewdate);
        subDate.setText(subscription.getDate());

        TextView subAmount = (TextView) convertView.findViewById(R.id.textViewamount);
        subAmount.setText(String.valueOf(subscription.getAmount()));

        TextView subComment = (TextView) convertView.findViewById(R.id.textViewcomment);
        subComment.setText(subscription.getComment());

        row = inflater.inflate(R.layout.one_list_element, parent, false);

        return convertView;

    }
    /* Attempt to override remove function in order to make delete button work. Does not work.
       Taken from https://stackoverflow.com/questions/10311431/how-to-delete-a-custom-listview-item-in-android
       Accessed February 7, 2018
     */
    @Override
    public void remove(Subscription subscription){
        data.remove(subscription);
        super.remove(subscription);
    }


}

