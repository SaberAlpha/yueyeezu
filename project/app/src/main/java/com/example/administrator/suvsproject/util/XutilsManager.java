package com.example.administrator.suvsproject.util;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2016/11/12.
 */
public class XutilsManager {

    private static XutilsManager instance;
    private DbManager dbManager;

    private XutilsManager(){

    }

    public  static XutilsManager getInstance(){
        if(instance==null){
            synchronized (XutilsManager.class){
                if(instance==null){
                    instance=new XutilsManager();
                }
            }
        }
        return instance;
    }

    public DbManager getDbManager(){
        if(dbManager==null){
            initDbManager();
        }
        return  dbManager;
    }

    private void initDbManager() {
        File dbfile=new File("/sdcard/shipinxutils/db");
        if(!dbfile.exists()){
            dbfile.mkdir();
        }

        DbManager.DaoConfig config=new DbManager.DaoConfig().setDbDir(dbfile)
                .setDbName("shipinXutils").setDbVersion(1).setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        dbManager= x.getDb(config);

    }
}
