package uz.triples.qulaymarket.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.dialog_language.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import uz.triples.qulaymarket.models.RecentlyUsed
import uz.triples.qulaymarket.R
import uz.triples.qulaymarket.database.AppDatabase
import uz.triples.qulaymarket.hideKeyboard
import uz.triples.qulaymarket.showToast

class MainFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireContext().hideKeyboard(searchEditText)

        searchEditText.onFocusChangeListener =
            View.OnFocusChangeListener { _, p1 ->
                if (!p1){
                    requireContext().hideKeyboard(searchEditText)
                    if (searchEditText.text!!.isNotEmpty())
                    CoroutineScope(Dispatchers.IO).launch{
                        AppDatabase.getInstance(requireContext())!!.searchDao()
                            .insertRecentlyUsed(RecentlyUsed(searchEditText.text.toString()))
                    }
                    fragmentContainerOfHomeFragment
                        .findNavController().navigate(R.id.notFoundFragment)
                }
            }

        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.isNotBlank()){
                    fragmentContainerOfHomeFragment
                        .findNavController().navigate(R.id.recentlySearchedFragment)
                } else {
                    fragmentContainerOfHomeFragment
                        .findNavController().navigate(R.id.allGoodsFragment)
                }
            }
        })

        changeLanguage.setOnClickListener {
            showDialog(requireContext())
        }

        searchEditText.clearFocus()
    }

    private fun showDialog(context: Context) {
        var dialog: AlertDialog? = null
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_language, null, false)
        view.uzbek.setOnClickListener {
            dialog?.dismiss()
            showToast("O'zbek tili tanlandi")
        }
        view.rassian.setOnClickListener {
            dialog?.dismiss()
            showToast("Rus tili")
        }
        view.english.setOnClickListener {
            dialog?.dismiss()
            showToast("Ingliz tili")
        }
        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()
    }
}