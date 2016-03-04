package com.ly.supermvp.ui.fragment;

import android.view.View;

import com.ly.supermvp.R;
import com.ly.supermvp.delegate.WeatherFragmentDelegate;
import com.ly.supermvp.model.WeatherModel;
import com.ly.supermvp.model.WeatherModelImpl;
import com.ly.supermvp.model.entity.ShowApiWeather;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.orhanobut.logger.Logger;

/**
 * <Pre>
 *     天气预报fragment
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/2/29 17:43
 */
public class WeatherFragment extends FragmentPresenter<WeatherFragmentDelegate> implements View.OnClickListener {

    private WeatherModel mWeatherModel;

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }

    @Override
    protected Class<WeatherFragmentDelegate> getDelegateClass() {
        return WeatherFragmentDelegate.class;
    }

    @Override
    protected void initData() {
        super.initData();
        mWeatherModel = new WeatherModelImpl();
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.bt_weather);
    }

    /**
     * 获取天气预报
     */
    private void netWeather() {
        mWeatherModel.netLoadWeatherWithLocation("北京", "1", "1", "1", "1", new WeatherModel.OnLoadWeatherListener() {
            @Override
            public void onSuccess(ShowApiWeather weather) {
                Logger.d("onSuccess");
                viewDelegate.showNowWeatherDialog(weather);
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.d("onFailure");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_weather:
                netWeather();
                break;
        }
    }
}