package uz.triples.qulaymarket.database

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.triples.qulaymarket.models.RecentlyUsed

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentlyUsed(recentlyUsed: RecentlyUsed)

    @Query("SELECT * FROM RecentlyUsed")
    fun getAllWords(): LiveData<List<RecentlyUsed>>

    @Delete
    fun deleteSearch(recentlyUsed: RecentlyUsed)
}