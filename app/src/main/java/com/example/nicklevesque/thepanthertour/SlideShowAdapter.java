package com.example.nicklevesque.thepanthertour;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nicklevesque on 4/23/17.
 */

public class SlideShowAdapter extends PagerAdapter {

    private List<Names> items;
    private Context context;


    private int[] image_resources = {
        R.mipmap.frontpage,
        R.mipmap.frontpage,
        R.mipmap.frontpage

    };

    public SlideShowAdapter(Context context, List<Names> items){
        this.context = context;
        this.items=items;
    }

    public int getCount(){
        return image_resources.length;
    }

    public boolean isViewFromObject(View view, Object object){
        return (view == object);
    }

    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater lay = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View itemView = lay.inflate(R.layout.screenshoots,container,false);

        TextView topTextItem = (TextView) itemView.findViewById(R.id.textView);
        TextView bottomTextItem = (TextView) itemView.findViewById(R.id.slideshowText);
        ImageView iv = (ImageView) itemView.findViewById(R.id.slider_image);

        Names name = items.get(position);

        topTextItem.setText(name.hall);
        bottomTextItem.setText(name.name);


        iv.setImageResource(image_resources[position]);
        container.addView(itemView);
        return itemView;
    }

    public void destroyItem(ViewGroup container, int Position, Object object){
        container.removeView((View) object);
    }


}
