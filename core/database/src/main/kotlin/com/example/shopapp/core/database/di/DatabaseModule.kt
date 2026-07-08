package com.example.shopapp.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shopapp.core.database.ShopDatabase
import com.example.shopapp.core.database.dao.CartDao
import com.example.shopapp.core.database.dao.ProductDao
import com.example.shopapp.core.database.dao.WishlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS cart_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId INTEGER NOT NULL,
                productId INTEGER NOT NULL,
                productTitle TEXT NOT NULL,
                productPrice REAL NOT NULL,
                productThumbnail TEXT NOT NULL,
                quantity INTEGER NOT NULL
            )
        """.trimIndent())
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS wishlist_items (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                userId INTEGER NOT NULL,
                productId INTEGER NOT NULL,
                productTitle TEXT NOT NULL,
                productPrice REAL NOT NULL,
                productThumbnail TEXT NOT NULL
            )
        """.trimIndent())
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides @Singleton
    fun provideShopDatabase(@ApplicationContext context: Context): ShopDatabase {
        return Room.databaseBuilder(context, ShopDatabase::class.java, "shop_database")
            .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
            .build()
    }

    @Provides @Singleton
    fun provideProductDao(database: ShopDatabase): ProductDao = database.productDao()

    @Provides @Singleton
    fun provideCartDao(database: ShopDatabase): CartDao = database.cartDao()

    @Provides @Singleton
    fun provideWishlistDao(database: ShopDatabase): WishlistDao = database.wishlistDao()
}