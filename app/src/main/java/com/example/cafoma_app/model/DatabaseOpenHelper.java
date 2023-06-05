package com.example.cafoma_app.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	private static String TAG = "DatabaseOpenHelper";
	final public static String TABLE_NAME = "formation";
	final public static String FORMATION_ID_FIELD = "formation_id";
	final public static String FK_USER_ID_FIELD = "fk_user_id";
	final public static String LIBELLE_FIELD = "libelle";
	final public static String ACRONYME_FIELD = "acronyme";
	final public static String DESCRIPTION_FIELD = "description";
	final public static String IMG_FIELD = "img";
	final public static String VIDEO_FIELD = "video";

	final public static String[] columns = { FORMATION_ID_FIELD, FK_USER_ID_FIELD, LIBELLE_FIELD, ACRONYME_FIELD, DESCRIPTION_FIELD, IMG_FIELD, VIDEO_FIELD};

	private String create_table="create table " + TABLE_NAME + "("
			+ FORMATION_ID_FIELD + " INTEGER PRIMARY KEY,"
			+ FK_USER_ID_FIELD + " INTEGER NOT NULL,"
			+ LIBELLE_FIELD + " TEXT NOT NULL,"
			+ ACRONYME_FIELD + " TEXT NOT NULL,"
			+ DESCRIPTION_FIELD + " TEXT NOT NULL,"
			+ IMG_FIELD + " TEXT NOT NULL,"
			+ VIDEO_FIELD + " TEXT NOT NULL);";
	final private static String NAME_DB = "cafoma";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DatabaseOpenHelper(Context context) {
		super(context, NAME_DB, null, VERSION);
		this.mContext = context;
		Log.i(TAG,"constructeur DatabaseOpenHelper");
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG,"onCreate");
		db.execSQL(create_table);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}
}
