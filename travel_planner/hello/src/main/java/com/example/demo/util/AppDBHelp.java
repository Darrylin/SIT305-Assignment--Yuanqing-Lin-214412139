package com.example.demo.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.demo.model.Note;
import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class AppDBHelp {
    private int dbVersion = 1;
    public DBHelp dbhelp;
    private static AppDBHelp appDBHelp;

    private AppDBHelp(Context context) {
        this.dbhelp = new DBHelp(context);
    }

    public synchronized static AppDBHelp getInstance(Context context) {
        if (appDBHelp == null)
            appDBHelp = new AppDBHelp(context);
        return appDBHelp;
    }

    /**
     *
     *
     * @return
     */
    public List<Note> findNote(int uid) {
        List<Note> noteList = new ArrayList<>();
        Note note = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String sql = "SELECT id,uid,content,date FROM note where uid =" + uid + " order by date desc";
        try {
            db = dbhelp.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                note = new Note();
                note.setId(cursor.getInt(0));
                note.setUid(cursor.getInt(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return noteList;
    }

    /**
     *
     *
     * @return
     */
    public List<Note> findNote(int uid, String keywords) {
        List<Note> noteList = new ArrayList<>();
        Note note = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String sql = "SELECT id,uid,content,date FROM note where uid =" + uid + " and content like '%" + keywords + "%' order by date desc";
        try {
            db = dbhelp.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                note = new Note();
                note.setId(cursor.getInt(0));
                note.setUid(cursor.getInt(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return noteList;
    }

    /**
     *
     *
     * @param id
     */
    public void deleteNote(int id) {
        SQLiteDatabase db = null;
        try {
            db = dbhelp.getWritableDatabase();
            String sql = "DELETE FROM note WHERE id=" + id;
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("hel->", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     *
     *
     * @param note
     */
    public void saveNote(Note note) {
        SQLiteDatabase db = null;
        try {
            db = dbhelp.getWritableDatabase();
            String sql = "INSERT INTO note(uid,content,date) VALUES('" + note.getUid() + "','" + note.getContent() + "','" + note.getDate() + "')";
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("hel->", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     *
     */
    public void updateNote(Note note) {
        SQLiteDatabase db = null;
        try {
            db = dbhelp.getWritableDatabase();
            db.execSQL("UPDATE note SET content='" + note.getContent() + "',date='" + ToolUtils.getCurDate() + "' WHERE id = " + note.getId());
        } catch (Exception e) {
            Log.e("", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     *
     *
     * @param user
     */
    public void saveUser(User user) {
        SQLiteDatabase db = null;
        try {
            db = dbhelp.getWritableDatabase();
            String sql = "INSERT INTO user(username, password,email) VALUES('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getEmail() + "')";
            db.execSQL(sql);
        } catch (Exception e) {
            Log.e("hel->", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     *
     */
    public void updateUser(int uid, String password) {
        SQLiteDatabase db = null;
        try {
            db = dbhelp.getWritableDatabase();
            db.execSQL("UPDATE user SET password='" + password + "' WHERE id = " + uid);
        } catch (Exception e) {
            Log.e("", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     *
     */
    public User findUser(String username, String password) {
        User user = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbhelp.getReadableDatabase();
            String sql = "select id,email from user where username='" + username + "' and password='" + password + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                user = new User();
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
            }
        } catch (Exception e) {
            Log.e("hel->", e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return user;
        }
    }

    public class DBHelp extends SQLiteOpenHelper {
        public void onCreate(SQLiteDatabase sqlitedatabase) {
            sqlitedatabase.execSQL("CREATE TABLE [note](\n" +
                    "  [id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "  [uid] NVARCHAR(250), \n" +
                    "  [content] NVARCHAR(250), \n" +
                    "  [date] NVARCHAR(250))");
            sqlitedatabase.execSQL("CREATE TABLE [user](\n" +
                    "  [id] INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "  [username] NVARCHAR(250), \n" +
                    "  [password] NVARCHAR(250), \n" +
                    "  [email] NVARCHAR(250))");
            Log.i("hel->", "===========================create sql=============================");
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int oldV, int newV) {
            Log.i("hel->", "===========================drop sql=============================");
        }

        public DBHelp(Context context) {
            super(context, "travel.db", null, dbVersion);
        }
    }

}