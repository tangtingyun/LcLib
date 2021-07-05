package com.step.lclib.work;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// https://blog.csdn.net/wzy_1988/article/details/51374979
// https://guides.codepath.com/android/local-databases-with-sqliteopenhelper
// https://juejin.cn/post/6844903486400757773
class LcSqlHelper extends SQLiteOpenHelper {
    public LcSqlHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void test(Context context){
//        context.openOrCreateDatabase();
//        DatabaseUtils.stringForQuery();
    }
}
