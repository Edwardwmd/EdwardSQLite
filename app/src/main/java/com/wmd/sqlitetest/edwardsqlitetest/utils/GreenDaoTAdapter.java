package com.wmd.sqlitetest.edwardsqlitetest.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wmd.sqlitetest.edwardsqlitetest.R;
import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间：2017/11/14/22：04
 * 作者：MingDe_Wu
 * 网站：https://github.com/Edwardwmd
 * 作用：
 * 声明：版权归作者所有
 */

public class GreenDaoTAdapter extends RecyclerView.Adapter<GreenDaoTAdapter.GreenDaoViewHolder> {
    private LayoutInflater mF;
    private List<PersonBean> mPs = new ArrayList<PersonBean>();

    public GreenDaoTAdapter(Context context) {
        this.mF = LayoutInflater.from(context);
    }

    public void setData(List<PersonBean> lps) {
        this.mPs.clear();
        mPs.addAll(lps);
        notifyDataSetChanged();

    }

    @Override
    public GreenDaoTAdapter.GreenDaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mF.inflate(R.layout.item_greendao, parent, false);

        return new GreenDaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GreenDaoTAdapter.GreenDaoViewHolder holder, int position) {
        holder.name.setText(mPs.get(position).getName());
        holder.sex.setText(mPs.get(position).getSex());
        holder.age.setText(String.valueOf(mPs.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return mPs == null ? 0 : mPs.size();
    }

    public class GreenDaoViewHolder extends RecyclerView.ViewHolder {
        private TextView name, sex, age;

        public GreenDaoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            sex = (TextView) itemView.findViewById(R.id.sex);
            age = (TextView) itemView.findViewById(R.id.age);
        }
    }


}
