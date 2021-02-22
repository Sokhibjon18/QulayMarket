package uz.triples.qulaymarket.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RecentlyUsed(
    @PrimaryKey
    val value: String
)