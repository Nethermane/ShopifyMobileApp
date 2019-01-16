package com.nishimura.android.shopifyapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import com.nishimura.android.shopifyapp.data.db.CollectionDatabase
import com.nishimura.android.shopifyapp.data.network.*
import com.nishimura.android.shopifyapp.data.repository.CollectionRepository
import com.nishimura.android.shopifyapp.data.repository.CollectionRepositoryImpl
import com.nishimura.android.shopifyapp.ui.collection.CollectionViewModelFactory
import com.nishimura.android.shopifyapp.ui.products.ProductViewModelFactory

class ShopifyApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ShopifyApplication))
        bind() from singleton { CollectionDatabase(instance()) }
        bind() from singleton { instance<CollectionDatabase>().collectionDao() }
        bind() from singleton { instance<CollectionDatabase>().productDao() }
        bind() from singleton { instance<CollectionDatabase>().productIdDao() }
        bind<ConnectivityInterceptor>() with singleton {
            ConnectivityInterceptorImpl(
                instance()
            )
        }
        bind() from singleton { ShopifyApiService(instance()) }
        bind<CollectionDataSource>() with singleton {
            CollectionDataSourceImpl(
                instance()
            )
        }
        bind<CollectionRepository>() with singleton { CollectionRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind() from provider { CollectionViewModelFactory(instance()) }
        bind() from provider { ProductViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}