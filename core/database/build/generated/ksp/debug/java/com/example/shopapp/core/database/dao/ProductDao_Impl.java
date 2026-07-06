package com.example.shopapp.core.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.shopapp.core.database.entity.ProductEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductEntity> __insertionAdapterOfProductEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAllProducts;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductEntity = new EntityInsertionAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `products` (`id`,`title`,`description`,`price`,`discountPercentage`,`rating`,`stock`,`brand`,`category`,`thumbnail`,`images`,`reviews`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindDouble(4, entity.getPrice());
        statement.bindDouble(5, entity.getDiscountPercentage());
        statement.bindDouble(6, entity.getRating());
        statement.bindLong(7, entity.getStock());
        statement.bindString(8, entity.getBrand());
        statement.bindString(9, entity.getCategory());
        statement.bindString(10, entity.getThumbnail());
        statement.bindString(11, entity.getImages());
        statement.bindString(12, entity.getReviews());
      }
    };
    this.__preparedStmtOfClearAllProducts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM products";
        return _query;
      }
    };
  }

  @Override
  public Object insertProducts(final List<ProductEntity> products,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(products);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertProduct(final ProductEntity product,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllProducts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllProducts.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAllProducts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ProductEntity>> getAllProducts() {
    final String _sql = "SELECT * FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfReviews = CursorUtil.getColumnIndexOrThrow(_cursor, "reviews");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpDiscountPercentage;
            _tmpDiscountPercentage = _cursor.getDouble(_cursorIndexOfDiscountPercentage);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            final String _tmpBrand;
            _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpReviews;
            _tmpReviews = _cursor.getString(_cursorIndexOfReviews);
            _item = new ProductEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpPrice,_tmpDiscountPercentage,_tmpRating,_tmpStock,_tmpBrand,_tmpCategory,_tmpThumbnail,_tmpImages,_tmpReviews);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<ProductEntity> getProductById(final int id) {
    final String _sql = "SELECT * FROM products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<ProductEntity>() {
      @Override
      @Nullable
      public ProductEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfReviews = CursorUtil.getColumnIndexOrThrow(_cursor, "reviews");
          final ProductEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpDiscountPercentage;
            _tmpDiscountPercentage = _cursor.getDouble(_cursorIndexOfDiscountPercentage);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            final String _tmpBrand;
            _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpReviews;
            _tmpReviews = _cursor.getString(_cursorIndexOfReviews);
            _result = new ProductEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpPrice,_tmpDiscountPercentage,_tmpRating,_tmpStock,_tmpBrand,_tmpCategory,_tmpThumbnail,_tmpImages,_tmpReviews);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> getProductsByCategory(final String category) {
    final String _sql = "SELECT * FROM products WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfReviews = CursorUtil.getColumnIndexOrThrow(_cursor, "reviews");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpDiscountPercentage;
            _tmpDiscountPercentage = _cursor.getDouble(_cursorIndexOfDiscountPercentage);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            final String _tmpBrand;
            _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpThumbnail;
            _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpReviews;
            _tmpReviews = _cursor.getString(_cursorIndexOfReviews);
            _item = new ProductEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpPrice,_tmpDiscountPercentage,_tmpRating,_tmpStock,_tmpBrand,_tmpCategory,_tmpThumbnail,_tmpImages,_tmpReviews);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
