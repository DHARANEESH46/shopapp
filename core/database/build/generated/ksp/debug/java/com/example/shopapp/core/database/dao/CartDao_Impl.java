package com.example.shopapp.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.shopapp.core.database.entity.CartEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class CartDao_Impl implements CartDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CartEntity> __insertionAdapterOfCartEntity;

  private final EntityDeletionOrUpdateAdapter<CartEntity> __updateAdapterOfCartEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCartItem;

  private final SharedSQLiteStatement __preparedStmtOfClearCartForUser;

  public CartDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCartEntity = new EntityInsertionAdapter<CartEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cart_items` (`id`,`userId`,`productId`,`productTitle`,`productPrice`,`productThumbnail`,`quantity`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getProductId());
        statement.bindString(4, entity.getProductTitle());
        statement.bindDouble(5, entity.getProductPrice());
        statement.bindString(6, entity.getProductThumbnail());
        statement.bindLong(7, entity.getQuantity());
      }
    };
    this.__updateAdapterOfCartEntity = new EntityDeletionOrUpdateAdapter<CartEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cart_items` SET `id` = ?,`userId` = ?,`productId` = ?,`productTitle` = ?,`productPrice` = ?,`productThumbnail` = ?,`quantity` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getProductId());
        statement.bindString(4, entity.getProductTitle());
        statement.bindDouble(5, entity.getProductPrice());
        statement.bindString(6, entity.getProductThumbnail());
        statement.bindLong(7, entity.getQuantity());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteCartItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items WHERE userId = ? AND productId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearCartForUser = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCartItem(final CartEntity cartItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCartEntity.insert(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCartItem(final CartEntity cartItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCartEntity.handle(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCartItem(final int userId, final int productId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCartItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, productId);
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
          __preparedStmtOfDeleteCartItem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearCartForUser(final int userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCartForUser.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
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
          __preparedStmtOfClearCartForUser.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CartEntity>> getCartItemsByUser(final int userId) {
    final String _sql = "SELECT * FROM cart_items WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cart_items"}, new Callable<List<CartEntity>>() {
      @Override
      @NonNull
      public List<CartEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "productTitle");
          final int _cursorIndexOfProductPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "productPrice");
          final int _cursorIndexOfProductThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "productThumbnail");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final List<CartEntity> _result = new ArrayList<CartEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CartEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            final String _tmpProductTitle;
            _tmpProductTitle = _cursor.getString(_cursorIndexOfProductTitle);
            final double _tmpProductPrice;
            _tmpProductPrice = _cursor.getDouble(_cursorIndexOfProductPrice);
            final String _tmpProductThumbnail;
            _tmpProductThumbnail = _cursor.getString(_cursorIndexOfProductThumbnail);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            _item = new CartEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpProductTitle,_tmpProductPrice,_tmpProductThumbnail,_tmpQuantity);
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
  public Object getCartItem(final int userId, final int productId,
      final Continuation<? super CartEntity> $completion) {
    final String _sql = "SELECT * FROM cart_items WHERE userId = ? AND productId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CartEntity>() {
      @Override
      @Nullable
      public CartEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "productTitle");
          final int _cursorIndexOfProductPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "productPrice");
          final int _cursorIndexOfProductThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "productThumbnail");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final CartEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            final String _tmpProductTitle;
            _tmpProductTitle = _cursor.getString(_cursorIndexOfProductTitle);
            final double _tmpProductPrice;
            _tmpProductPrice = _cursor.getDouble(_cursorIndexOfProductPrice);
            final String _tmpProductThumbnail;
            _tmpProductThumbnail = _cursor.getString(_cursorIndexOfProductThumbnail);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            _result = new CartEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpProductTitle,_tmpProductPrice,_tmpProductThumbnail,_tmpQuantity);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getCartCountForUser(final int userId) {
    final String _sql = "SELECT SUM(quantity) FROM cart_items WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cart_items"}, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
