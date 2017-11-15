package com.wmd.sqlitetest.edwardsqlitetest.nomalsql.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wmd.sqlitetest.edwardsqlitetest.nomalsql.DAO.ContantsBean;
import com.wmd.sqlitetest.edwardsqlitetest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2017/10/25/16：23
 * 作者：吴明德
 * 邮箱：1732141816@qq.com
 * 作用：xxxx
 * 声明：版权归作者所有
 */

public class ShowDataAdapter extends BaseAdapter {
    private LayoutInflater mlif;
    private List<ContantsBean> contantsBeanList = new ArrayList<>();

    public ShowDataAdapter(Context context) {
        mlif = LayoutInflater.from(context);
    }

    public void setData(List<ContantsBean> cbl) {
        contantsBeanList.clear();
        contantsBeanList = cbl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contantsBeanList == null ? 0 : contantsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return contantsBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SuggestionsViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new SuggestionsViewHolder();
            view = mlif.inflate(R.layout.suggest_item, viewGroup, false);
            viewHolder.tv_Id = view.findViewById(R.id.tv_id);
            viewHolder.tv_Name =  view.findViewById(R.id.tv_name);
            viewHolder.tv_Age = view.findViewById(R.id.tv_age);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SuggestionsViewHolder) view.getTag();
        }
        viewHolder.tv_Id.setText(String.valueOf(contantsBeanList.get(i).getContantId()));
        viewHolder.tv_Name.setText(contantsBeanList.get(i).getContantName());
        viewHolder.tv_Age.setText(String.valueOf(contantsBeanList.get(i).getAge()));
        return view;
    }


    private class SuggestionsViewHolder {
        TextView tv_Id, tv_Name, tv_Age;
    }
}
