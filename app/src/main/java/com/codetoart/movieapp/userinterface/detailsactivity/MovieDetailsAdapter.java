package com.codetoart.movieapp.userinterface.detailsactivity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codetoart.movieapp.R;
import com.codetoart.movieapp.injection.ActivityContext;
import com.codetoart.movieapp.model.image.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by vikas on 11-Jan-17.
 */

public class MovieDetailsAdapter extends PagerAdapter {
    ImageView mMoviePoster;

    private Context mContext;
    private List<Image> mImageArrayList;

    @Inject
    public MovieDetailsAdapter(@ActivityContext Context mContext) {
        this.mContext = mContext;
        mImageArrayList = new ArrayList<Image>();
    }

    public void setImages(List<Image> images) {
        if(images!=null){
            mImageArrayList.addAll(images);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (mImageArrayList == null) return 0;

        if (mImageArrayList.size() >= 5) {
            return 5;
        } else {
            return mImageArrayList.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.movie_details, container, false);
        mMoviePoster = (ImageView) view.findViewById(R.id.moviePoster);

        Picasso.with(mContext)
                .load(mImageArrayList.get(position).getFilePath())
                .into(mMoviePoster);

        container.addView(mMoviePoster);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) mMoviePoster);
    }
}
