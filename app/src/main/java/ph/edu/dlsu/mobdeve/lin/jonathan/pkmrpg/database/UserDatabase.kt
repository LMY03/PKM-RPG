package ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import ph.edu.dlsu.mobdeve.lin.jonathan.pkmrpg.model.User

class UserDatabase(private val context: Context) {
    private var databaseHandler : DatabaseHandler

    init {
        this.databaseHandler = DatabaseHandler(context, User.DATABASE_NAME, User.DATABASE_VERSION)
    }

    fun addMedia(user: User) : Int {
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(User.USER_NAME, user.username)
        contentValues.put(User.USER_PASS, user.password)

        val id = db.insert(User.USER_TABLE, null, contentValues)

        db.close()

        return id.toInt()
    }

    @SuppressLint("Range")
    fun getUserList(): ArrayList<User> {
        val result = ArrayList<User>()

        val db = databaseHandler.readableDatabase
        val cursor: Cursor?

        try{
            cursor = db.rawQuery("SELECT * FROM ${User.USER_TABLE}", null)
        } catch (e: SQLiteException) {
            db.close()
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val user = User(cursor.getString(0), cursor.getString(1), cursor.getString(2))
                user.character = CharacterDatabase(context).getCharList()[user.userId.toInt()]
                result.add(user)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return result
    }
}