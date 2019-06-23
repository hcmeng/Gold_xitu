package com.jiyun.a123.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.a123.myapplication2.adapter.MyAdapter;
import com.jiyun.a123.myapplication2.bean.ResultBean;
import com.jiyun.a123.myapplication2.contract.MusicContract;
import com.jiyun.a123.myapplication2.presenter.MusicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MusicContract.View {

    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.caozuo)
    Button caozuo;

    @BindView(R.id.complete)
    Button complete;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.collect)
    TextView collect;
    private ArrayList <ResultBean> arrayList;
    private MusicPresenter musicPresenter;
    private MyAdapter myAdapter;
    private int money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        arrayList = new ArrayList <>();
        getMvp();
        myAdapter = new MyAdapter(arrayList, this);
        xrv.setAdapter(myAdapter);
        myAdapter.setMyOnClick(new MyAdapter.MyOnClick() {
            @Override
            public void onClick(ResultBean resultBean, View view) {

                //获取checkbox的控件
                CheckBox byId = view.findViewById(R.id.cbx);
                int havehigh = resultBean.getHavehigh();
                if (byId.isChecked()) {//被选中的数据添加到数据库打印toast
                    MyUtil.insetOne(resultBean);
                    money+=havehigh;
                    collect.setText(money+"");
                    Toast.makeText(MainActivity.this, "收藏数据成功", Toast.LENGTH_SHORT).show();
                }else
                    money-=havehigh;
            }
        });
    }

    private void getMvp() {
        musicPresenter = new MusicPresenter(this);
        musicPresenter.preData();
    }

    @OnClick({R.id.caozuo, R.id.complete, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.caozuo:
                if (myAdapter.isCheck) {
                    myAdapter.setCheck(false);
                } else
                    myAdapter.setCheck(true);
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.complete:
                getMvp();
                myAdapter.setCheck(false);
                myAdapter.notifyDataSetChanged();

                break;
            case R.id.query:
                xrv.setLayoutManager(new LinearLayoutManager(this));
                xrv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                List <ResultBean> resultBeans = MyUtil.queryAll();
                MyAdapter myAdapter = new MyAdapter((ArrayList <ResultBean>) resultBeans, MainActivity.this);
                xrv.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void success(List <ResultBean> resultBeans) {
        arrayList.addAll(resultBeans);
        myAdapter.notifyDataSetChanged();
        Log.d(TAG, "success: " + resultBeans.size());
    }

    @Override
    public void failed(String string) {
        Log.d(TAG, "failed: " + string);
    }


    private static final String TAG = "MainActivity";

}
