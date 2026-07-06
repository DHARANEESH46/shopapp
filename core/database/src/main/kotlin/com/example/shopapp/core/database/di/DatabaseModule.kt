package com.example.shopapp.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shopapp.core.database.ShopDatabase
import com.example.shopapp.core.database.dao.CartDao
import com.example.shopapp.core.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS cart_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId INTEGER NOT NULL,
                productId INTEGER NOT NULL,
                productTitle TEXT NOT NULL,
                productPrice REAL NOT NULL,
                productThumbnail TEXT NOT NULL,
                quantity INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideShopDatabase(
        @ApplicationContext context: Context
    ): ShopDatabase {
        return Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            "shop_database"
        )
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: ShopDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: ShopDatabase): CartDao {
        return database.cartDao()
    }
}