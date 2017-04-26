package com.example.nicklevesque.thepanthertour;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nicklevesque on 4/23/17.
 * Class acts as a bridge between an AdapterView and the underlying data for that view.
 * Its also provides access to the data items. This class is also responsible for making a
 * View for each photo in the data set
 */

public class SlideShowAdapter extends PagerAdapter {

    //instantiate variables
    private List<Names> items;
    private Context context;

    /*array of photos used for the slideshow*/
    private int[] image_resources = {
        R.mipmap.frontpage,
        R.mipmap.frontpage,
        R.mipmap.frontpage

    };

    //Constructor
    public SlideShowAdapter(Context context, List<Names> items){
        this.context = context;
        this.items = items;
    }

    /*Method that returns length of the images_resource array*/
    public int getCount(){
        return image_resources.length;
    }

    /* A necessary method on a class that extends PagerAdapter, Determines whether a page View
    is associated with a specific key object as returned by instantiateItem
     */
    public boolean isViewFromObject(View view, Object object){
        return (view == object);
    }

    /*Create the page for the given position. The adapter is responsible
    for adding the view to the container given here. Another
    method that must be implemented in order for pager adapter to work properly
     */
    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater lay = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View itemView = lay.inflate(R.layout.screenshoots,container,false);

        TextView topTextItem = (TextView) itemView.findViewById(R.id.textView);
        TextView bottomTextItem = (TextView) itemView.findViewById(R.id.slideshowText);
        ImageView iv = (ImageView) itemView.findViewById(R.id.slider_image);

        Names name = items.get(position);

        topTextItem.setText("bah");
        bottomTextItem.setText("bah");


        iv.setImageResource(image_resources[position]);
        container.addView(itemView);
        return itemView;
    }


    /*Method that destroys the View of the previous photo when the user slides the picture over*/
    public void destroyItem(ViewGroup container, int Position, Object object){
        container.removeView((View) object);
    }


}
