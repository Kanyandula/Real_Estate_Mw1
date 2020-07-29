package com.example.realestatemw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Images> slideImg;
    private DatabaseReference mDatabaseRef;
    private ViewPager viewPager;
    private ImageView imageView;


    public ViewPagerAdapter (List<Images> slideImg, Context context){
        this.context = context;
        this.slideImg = slideImg;
    }


    @Override
    public int getCount() {
        return slideImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem (ViewGroup container, int position){
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.pager_item,null);
        Images agentSlide = slideImg.get(position);
        ImageView imageView = new ImageView(context);
       // imageView = view.findViewById(R.id.detailImage);
        Picasso.get()
                .load(agentSlide.getImages())
               .fit()
               .into(imageView);
       container.addView(imageView);


        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
