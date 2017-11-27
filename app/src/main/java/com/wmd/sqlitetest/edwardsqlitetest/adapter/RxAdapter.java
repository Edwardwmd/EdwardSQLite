package com.wmd.sqlitetest.edwardsqlitetest.adapter;

import android.content.Context;

import com.wmd.adapter.baserecyadapter.base.BaseRecyAdapter;
import com.wmd.adapter.baserecyadapter.base.BaseViewHolder;
import com.wmd.adapter.baserecyadapter.item.ItemViewDelegate;
import com.wmd.sqlitetest.edwardsqlitetest.R;
import com.wmd.sqlitetest.edwardsqlitetest.gen.PersonBean;

import java.util.List;

/**
 * 时间：2017/11/27/18：05
 * 作者：MingDe_Wu
 * 网站：https://github.com/Edwardwmd
 * 作用：
 * 声明：版权归作者所有
 */

public class RxAdapter extends BaseRecyAdapter {
    private List<PersonBean> personBeans;

    public RxAdapter(Context context) {
        super(context);
    }

    @Override
    public void setData(List mDatas) {
        super.setData(mDatas);
        personBeans = mDatas;
    }

    public void initLayout() {
        addItemViewDelegate(new ItemViewDelegate() {
            @Override
            public boolean isForViewType(Object item, int position) {
                return true;
            }

            @Override
            public void convert(BaseViewHolder holder, Object item, int position) {
                holder.setText(R.id.name, personBeans.get(position).getName());
                holder.setText(R.id.sex, personBeans.get(position).getSex());
                holder.setText(R.id.age, String.valueOf(personBeans.get(position).getAge()));
            }

            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_greendao;
            }
        });
    }
}
