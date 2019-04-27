package com.siso.edu.mqq;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static utils.Constant.*;


public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.iv_fruit_image_view);
        TextView fruitTextView = (TextView) findViewById(R.id.tv_fruit_content_text_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = getFruitContent(fruitName);
        fruitTextView.setText(fruitContent);

    }

    private String getFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        switch (fruitName) {
            case "Banana":
                fruitContent.append(Banana_1).append("\n").append("\n").append(Banana_2).append("\n").append("\n").append("\n")
                        .append(Banana_3).append("\n").append("\n").append(Banana_4).append("\n").append("\n").append("\n")
                        .append(Banana_5).append("\n").append("\n").append(Banana_6).append("\n").append("\n").append("\n")
                        .append(Banana_7).append("\n").append("\n").append(Banana_8).append("\n").append("\n").append("\n")
                        .append(Banana_9).append("\n").append("\n").append(Banana_10).append("\n").append("\n").append("\n")
                        .append(Banana_11).append("\n").append("\n").append(Banana_12).append("\n").append("\n").append("\n");
                break;
            case "Apple":
                fruitContent.append(Apple_1).append("\n").append("\n").append(Apple_2).append("\n").append("\n").append("\n")
                        .append(Apple_3).append("\n").append("\n").append(Apple_4).append("\n").append("\n").append("\n")
                        .append(Apple_5).append("\n").append("\n").append(Apple_6).append("\n").append("\n").append("\n")
                        .append(Apple_7).append("\n").append("\n").append(Apple_8).append("\n").append("\n").append("\n")
                        .append(Apple_9).append("\n").append("\n").append(Apple_10).append("\n").append("\n").append("\n")
                        .append(Apple_11).append("\n").append("\n").append(Apple_12).append("\n").append("\n").append("\n");
                break;
            case "Orange":
                fruitContent.append(Orange_1).append("\n").append("\n").append(Orange_2).append("\n").append("\n").append("\n")
                        .append(Orange_3).append("\n").append("\n").append(Orange_4).append("\n").append("\n").append("\n")
                        .append(Orange_5).append("\n").append("\n").append(Orange_6).append("\n").append("\n").append("\n")
                        .append(Orange_7).append("\n").append("\n").append(Orange_8).append("\n").append("\n").append("\n")
                        .append(Orange_9).append("\n").append("\n").append(Orange_10).append("\n").append("\n").append("\n")
                        .append(Orange_11).append("\n").append("\n").append(Orange_12).append("\n").append("\n").append("\n");
                break;
            case "Pineapple":
                fruitContent.append(Pineapple_1).append("\n").append("\n").append(Pineapple_2).append("\n").append("\n").append("\n")
                        .append(Pineapple_3).append("\n").append("\n").append(Pineapple_4).append("\n").append("\n").append("\n")
                        .append(Pineapple_5).append("\n").append("\n").append(Pineapple_6).append("\n").append("\n").append("\n")
                        .append(Pineapple_7).append("\n").append("\n").append(Pineapple_8).append("\n").append("\n").append("\n")
                        .append(Pineapple_9).append("\n").append("\n").append(Pineapple_10).append("\n").append("\n").append("\n")
                        .append(Pineapple_11).append("\n").append("\n").append(Pineapple_12).append("\n").append("\n").append("\n");
                break;
            case "Mango":
                fruitContent.append(Mango_1).append("\n").append("\n").append(Mango_2).append("\n").append("\n").append("\n")
                        .append(Mango_3).append("\n").append("\n").append(Mango_4).append("\n").append("\n").append("\n")
                        .append(Mango_5).append("\n").append("\n").append(Mango_6).append("\n").append("\n").append("\n")
                        .append(Mango_7).append("\n").append("\n").append(Mango_8).append("\n").append("\n").append("\n")
                        .append(Mango_9).append("\n").append("\n").append(Mango_10).append("\n").append("\n").append("\n")
                        .append(Mango_11).append("\n").append("\n").append(Mango_12).append("\n").append("\n").append("\n");
                break;
            case "Cherry":
                fruitContent.append(Cherry_1).append("\n").append("\n").append(Cherry_2).append("\n").append("\n").append("\n")
                        .append(Cherry_3).append("\n").append("\n").append(Cherry_4).append("\n").append("\n").append("\n")
                        .append(Cherry_5).append("\n").append("\n").append(Cherry_6).append("\n").append("\n").append("\n")
                        .append(Cherry_7).append("\n").append("\n").append(Cherry_8).append("\n").append("\n").append("\n")
                        .append(Cherry_9).append("\n").append("\n").append(Cherry_10).append("\n").append("\n").append("\n");
                break;
            case "Strawberry":
                fruitContent.append(Strawberry_1).append("\n").append("\n").append(Strawberry_2).append("\n").append("\n").append("\n")
                        .append(Strawberry_3).append("\n").append("\n").append(Strawberry_4).append("\n").append("\n").append("\n")
                        .append(Strawberry_5).append("\n").append("\n").append(Strawberry_6).append("\n").append("\n").append("\n")
                        .append(Strawberry_7).append("\n").append("\n").append(Strawberry_8).append("\n").append("\n").append("\n")
                        .append(Strawberry_9).append("\n").append("\n").append(Strawberry_10).append("\n").append("\n").append("\n")
                        .append(Strawberry_11).append("\n").append("\n").append(Strawberry_12).append("\n").append("\n").append("\n");
                break;
            case "Pear":
                fruitContent.append(Pear_1).append("\n").append("\n").append(Pear_2).append("\n").append("\n").append("\n")
                        .append(Pear_3).append("\n").append("\n").append(Pear_4).append("\n").append("\n").append("\n")
                        .append(Pear_5).append("\n").append("\n").append(Pear_6).append("\n").append("\n").append("\n")
                        .append(Pear_7).append("\n").append("\n").append(Pear_8).append("\n").append("\n").append("\n")
                        .append(Pear_9).append("\n").append("\n").append(Pear_10).append("\n").append("\n").append("\n")
                        .append(Pear_11).append("\n").append("\n").append(Pear_12).append("\n").append("\n").append("\n");
                break;
            case "Grape":
                fruitContent.append(Grape_1).append("\n").append("\n").append(Grape_2).append("\n").append("\n").append("\n")
                        .append(Grape_3).append("\n").append("\n").append(Grape_4).append("\n").append("\n").append("\n")
                        .append(Grape_5).append("\n").append("\n").append(Grape_6).append("\n").append("\n").append("\n")
                        .append(Grape_7).append("\n").append("\n").append(Grape_8).append("\n").append("\n").append("\n")
                        .append(Grape_9).append("\n").append("\n").append(Grape_10).append("\n").append("\n").append("\n")
                        .append(Grape_11).append("\n").append("\n").append(Grape_12).append("\n").append("\n").append("\n");
                break;
            case "Watermelon":
                fruitContent.append(Watermelon_1).append("\n").append("\n").append(Watermelon_2).append("\n").append("\n").append("\n")
                        .append(Watermelon_3).append("\n").append("\n").append(Watermelon_4).append("\n").append("\n").append("\n")
                        .append(Watermelon_5).append("\n").append("\n").append(Watermelon_6).append("\n").append("\n").append("\n")
                        .append(Watermelon_7).append("\n").append("\n").append(Watermelon_8).append("\n").append("\n").append("\n")
                        .append(Watermelon_9).append("\n").append("\n").append(Watermelon_10).append("\n").append("\n").append("\n");
                break;
            default:

        }


//        for (int i=0;i<500;i++){
//            fruitContent.append(fruitName);
//        }
        return fruitContent.toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
