package com.nishimura.android.shopifyapp.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishimura.android.shopifyapp.R
import com.nishimura.android.shopifyapp.ui.base.ScopedFragment
import com.nishimura.android.shopifyapp.ui.collection.CollectionAdapter
import com.nishimura.android.shopifyapp.ui.collection.CollectionViewModel
import com.nishimura.android.shopifyapp.ui.collection.CollectionViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class ProductFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = ProductFragment()
    }

    override val kodein by closestKodein()
    private val productViewModelFactory: ProductViewModelFactory by instance()
    private lateinit var viewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, productViewModelFactory).get(ProductViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val adapter = CollectionAdapter(context!!)
        viewModel.collectionId = 68424466488
        val collections = viewModel.collection.await()

        collections_list.layoutManager = LinearLayoutManager(context!!)
        collections_list.adapter = adapter
        collections.observe(this@ProductFragment, Observer {

        })
    }
}
