package com.epicodus.myrestaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.ui.RestaurantDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Guest on 9/18/17.
 */


public class FirebaseRestaurantViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public ImageView mRestaurantImageView;

    public FirebaseRestaurantViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRestaurant(Restaurant restaurant) {
        mRestaurantImageView = (ImageView) mView.findViewById(R.id.restaurantImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.restaurantNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);



        //This is where the code to show the RecyclerView for *My Saved restaurants*

        //Validation code to insure there is an image
        String imageToLoad;
        if(restaurant.getImageUrl().isEmpty()){
            // Uri.parse("android.resource://your.package.name/" + R.drawable.sample_1);
            imageToLoad = "android.resource://com.epicodus.myrestaurants/" + R.drawable.placeholder;
        } else {
            imageToLoad = restaurant.getImageUrl();
        }

        Picasso.with(mContext)
                .load(Uri.parse(imageToLoad))
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRestaurantImageView);

        nameTextView.setText(restaurant.getName());
        categoryTextView.setText(restaurant.getCategories().get(0));
        ratingTextView.setText("Rating: " + restaurant.getRating() + "/5");
    }

}
