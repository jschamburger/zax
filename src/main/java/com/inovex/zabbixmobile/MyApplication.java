package com.inovex.zabbixmobile;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by jschamburger on 21.08.15.
 */
public class MyApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)).enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)).build());
    }

}
