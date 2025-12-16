package uz.test.marketplacetest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.test.marketplacetest.data.remote.service.ProductService
import uz.test.marketplacetest.data.repository.ProductRepositoryImpl
import uz.test.marketplacetest.domain.repository.ProductsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(service: ProductService): ProductsRepository {
        return ProductRepositoryImpl(service)
    }


}