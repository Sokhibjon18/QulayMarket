package uz.triples.qulaymarket.`interface`

import uz.triples.qulaymarket.network.pojo_objects.Announcement

interface ProductClicked {
    fun itemClicked(announcement: Announcement)
}