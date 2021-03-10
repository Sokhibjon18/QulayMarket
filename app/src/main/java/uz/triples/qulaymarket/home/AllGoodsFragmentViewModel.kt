package uz.triples.qulaymarket.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.triples.qulaymarket.network.NetWorkInterface
import uz.triples.qulaymarket.network.Network
import uz.triples.qulaymarket.network.pojo_objects.Announcement
import uz.triples.qulaymarket.utils.Status
import java.lang.Exception

class AllGoodsFragmentViewModel: ViewModel() {

    private val _announcements = MutableLiveData<List<Announcement>>()
    val announcements: LiveData<List<Announcement>>
    get() = _announcements

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    init {
        loadDataFromApi()
    }

    private fun loadDataFromApi() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                val service = Network.getInstance().create(NetWorkInterface::class.java)
                _announcements.value = service.getAllAnnouncements().result
                _status.value = Status.DONE
            } catch (e: Exception){
                _status.value = Status.ERROR
                _announcements.value = arrayListOf()
            }
        }
    }
}