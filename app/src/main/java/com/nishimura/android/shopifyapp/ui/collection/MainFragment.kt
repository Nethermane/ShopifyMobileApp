package com.nishimura.android.shopifyapp.ui.collection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import com.nishimura.android.shopifyapp.R
import com.nishimura.android.shopifyapp.data.network.CollectionDataSourceImpl
import com.nishimura.android.shopifyapp.data.network.ShopifyApiService
import com.nishimura.android.shopifyapp.data.network.ConnectivityInterceptorImpl
import com.nishimura.android.shopifyapp.ui.base.ScopedFragment

class MainFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val kodein by closestKodein()
    private val collectionViewModelFactory: CollectionViewModelFactory by instance()
    private lateinit var viewModel: CollectionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, collectionViewModelFactory)
            .get(CollectionViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val adapter = CollectionAdapter(context!!)
        val collections = viewModel.collection.await()

        collections_list.layoutManager = LinearLayoutManager(context!!)
        collections_list.adapter = adapter
        collections.observe(this@MainFragment, Observer {
            adapter.setData(it)
        })
    }
}
