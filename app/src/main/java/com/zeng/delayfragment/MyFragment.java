package com.zeng.delayfragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zeng on 2016/2/2.
 */
public class MyFragment extends DelayFragment {


    private TextView textView1;
    private TextView textView2;

    private String index;
    /** 标志位，标志已经初始化完成 */
    private boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    private boolean mHasLoadedOnce;

    public static MyFragment newInstance(String str){
        MyFragment myFragment=new MyFragment();
        Bundle bundle=new Bundle();
        bundle.putString("index",str);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index=getArguments().getString("index");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,null);
        textView1= (TextView) view.findViewById(R.id.text1);
        textView1.setText(index);
        textView2= (TextView) view.findViewById(R.id.text2);

        /*完成初始化*/
        isPrepared=true;
        /*
        * 因为setUserVisibleHint会比onCreateView先执行，为了防止空指针错误，需要执行了咋样load();
        * */
        lazyLoad();
        return view;
    }

    @Override
    public void lazyLoad() {

        /*
        * 判断是否满足条件
        * */
        if(!isVisible || !isPrepared ||mHasLoadedOnce){
            return;
        }
        download();

    }

    private void download() {

        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("加载中。。。");
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                textView2.setText("完成加载");
                mHasLoadedOnce=true;
                progressDialog.dismiss();

            }
        },2000);

    }
}
