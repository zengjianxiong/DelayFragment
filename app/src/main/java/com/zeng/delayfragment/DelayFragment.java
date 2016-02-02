package com.zeng.delayfragment;

import android.support.v4.app.Fragment;

/**
 * Created by zeng on 2016/2/2.
 */
public abstract class DelayFragment extends Fragment {
    /*
    * fragment是否可见
    * */
    protected boolean isVisible=false;

    /*
    *实现fragment懒加载
    * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    /*
    *可见
    */
    protected void onVisible(){
        lazyLoad();
    }
    /*
    不可见
    */
    protected void onInvisible(){

    }


    public abstract void lazyLoad();
}
