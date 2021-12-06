package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;

public class CustomerMainActivity extends AppCompatActivity {
    int count = 0;
    LinearLayout layout;
    Customer current_user;
    public static final String EXTRA_MESSAGE = "com.example.gproject.CUSTOMER_MAIN";
    public static final String CUSTOMER_EXTRA_MESSAGE = "com.example.gproject.CUSTOMER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        layout = findViewById(R.id.container);
        DB.getStores(this);
        Intent intent = getIntent();
        current_user = (Customer)intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        setTitle();
    }

    public void setTitle(){
        TextView title = findViewById(R.id.customer_main_title);
        String new_string = getString(R.string.owner_main_title) + " " + Helper.trim(current_user.getFirstName(), 12) + "! 👋";
        title.setText(new_string);
    }

    public void add(View view){
        // test
        addCard("","test","test", "https://www.simplyrecipes.com/thmb/8caxM88NgxZjz-T2aeRW3xjhzBg=/2000x1125/smart/filters:no_upscale()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-3-8f256746d649404baa36a44d271329bc.jpg");
    }

    public void onShopClicked(String user_name, String store_name, String store_location){
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra(EXTRA_MESSAGE, user_name);
        intent.putExtra("STORE_NAME", store_name);
        intent.putExtra("STORE_LOC", store_location);
        intent.putExtra(CUSTOMER_EXTRA_MESSAGE, current_user.getUsername());
        startActivity(intent);
    }

    public void addCard(String owner_username, String store_name, String store_location, String store_image_link){
        count +=1;
        final View view = getLayoutInflater().inflate(R.layout.card, null);
        TextView store_name_tv = view.findViewById(R.id.store_name);
        TextView store_location_tv = view.findViewById(R.id.store_location);
        ImageView store_image_tv = view.findViewById(R.id.store_image);
        RelativeLayout store_card_relative = view.findViewById(R.id.store_card_relative);
        CardView store_cardview = view.findViewById(R.id.store_cardview);
        store_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShopClicked(owner_username, store_name, store_location);
            }
        });

        store_name_tv.setText(Helper.trim(store_name, 17));
        store_location_tv.setText(Helper.trim(store_location, 30));

        try{
            new DownloadImageTask(store_image_tv).execute(store_image_link);
        }catch(Exception e){
            Log.i("console", e.getMessage());
        }

        store_image_tv.setClipToOutline(true);
        layout.addView(view);
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}