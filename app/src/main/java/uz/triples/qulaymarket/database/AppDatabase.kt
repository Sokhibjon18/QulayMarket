package uz.triples.qulaymarket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.triples.qulaymarket.models.RecentlyUsed

@Database(entities = [RecentlyUsed::class],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if (instance == null){
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "QM.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

    }

}