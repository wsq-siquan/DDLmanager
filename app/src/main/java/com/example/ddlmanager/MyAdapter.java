package com.example.ddlmanager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王思全 on 2016/12/14.
 */
public class MyAdapter extends BaseAdapter {
    public List<ItemData> list = new ArrayList<ItemData>();
    private Context mContext;
    public String[] colors1 ={"#d0366a","#ec407a","#f85290","#ff6ca0","#fe7a92","#fea5a1","#fec3b1"};
    public String[] colors2 ={"#89658b","#cd8183","#f3a87e","#d1cb91","#f3c669","#ffd96a","#f4e980"};
    public String[] colors3 ={"#63171b","#71201f","#86201b","#962f1e","#9f4425","#b75f2d","#bd7833"};
    public String[] colors4 ={"#cfc9bd","#ead4bc","#f6e4b6","#faeebc","#fdf7a1","#fee79b","#fad5a1"};
    public String[] colors5 ={"#015941","#087556","#31815e","#4c8d65","#6d9b74","#8ca57b","#bbb271"};
    public int ioc=1;

    public MyAdapter(Context mContext, List<ItemData> list,int i) {
        this.list = list;
        this.mContext = mContext;
        int maxMemory = (int)Runtime.getRuntime().maxMemory();
        int chcheSize = maxMemory / 8;
        this.ioc=i;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.std = (TextView) view.findViewById(R.id.std);
            holder.edd = (TextView) view.findViewById(R.id.edd);
            holder.detail = (TextView) view.findViewById(R.id.detail);
            holder.seekBar = (SeekBar) view.findViewById(R.id.seekBar);
            holder.percent =(TextView)view.findViewById(R.id.percent);
            holder.linearLayout=(LinearLayout)view.findViewById(R.id.llayout);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ItemData item = (ItemData)getItem(position);
        if (item != null) {
            String str= item.progress + "%";
            holder.std.setText(item.std_s);
            holder.edd.setText(item.edd_s);
            holder.detail.setText(item.detail);
            holder.seekBar.setProgress(item.progress);
            holder.percent.setText(str);
            if(ioc==1) {
                holder.linearLayout.setBackgroundColor(Color.parseColor(colors1[position%7]));
            } else if(ioc==2) {
                holder.linearLayout.setBackgroundColor(Color.parseColor(colors2[position%7]));
            } else if(ioc==3) {
                holder.linearLayout.setBackgroundColor(Color.parseColor(colors3[position%7]));
            } else if(ioc==4) {
                holder.linearLayout.setBackgroundColor(Color.parseColor(colors4[position%7]));
            } else if(ioc==5) {
                holder.linearLayout.setBackgroundColor(Color.parseColor(colors5[position%7]));
            }

        }
        return view;
    }

    public class ViewHolder {
        public TextView std,edd,detail,percent;
        public SeekBar seekBar;
        public LinearLayout linearLayout;
       // public int ioc;
    }
}
