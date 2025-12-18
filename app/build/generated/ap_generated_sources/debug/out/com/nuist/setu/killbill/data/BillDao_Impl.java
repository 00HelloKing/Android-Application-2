package com.nuist.setu.killbill.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BillDao_Impl implements BillDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Bill> __insertionAdapterOfBill;

  private final EntityDeletionOrUpdateAdapter<Bill> __deletionAdapterOfBill;

  private final EntityDeletionOrUpdateAdapter<Bill> __updateAdapterOfBill;

  public BillDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBill = new EntityInsertionAdapter<Bill>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bills` (`id`,`amount`,`category`,`note`,`timestamp`,`receiptUri`,`source`,`paymentApp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Bill entity) {
        statement.bindLong(1, entity.id);
        statement.bindDouble(2, entity.amount);
        if (entity.category == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.category);
        }
        if (entity.note == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.note);
        }
        statement.bindLong(5, entity.timestamp);
        if (entity.receiptUri == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.receiptUri);
        }
        if (entity.source == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.source);
        }
        if (entity.paymentApp == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.paymentApp);
        }
      }
    };
    this.__deletionAdapterOfBill = new EntityDeletionOrUpdateAdapter<Bill>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bills` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Bill entity) {
        statement.bindLong(1, entity.id);
      }
    };
    this.__updateAdapterOfBill = new EntityDeletionOrUpdateAdapter<Bill>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `bills` SET `id` = ?,`amount` = ?,`category` = ?,`note` = ?,`timestamp` = ?,`receiptUri` = ?,`source` = ?,`paymentApp` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Bill entity) {
        statement.bindLong(1, entity.id);
        statement.bindDouble(2, entity.amount);
        if (entity.category == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.category);
        }
        if (entity.note == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.note);
        }
        statement.bindLong(5, entity.timestamp);
        if (entity.receiptUri == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.receiptUri);
        }
        if (entity.source == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.source);
        }
        if (entity.paymentApp == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.paymentApp);
        }
        statement.bindLong(9, entity.id);
      }
    };
  }

  @Override
  public long insert(final Bill bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfBill.insertAndReturnId(bill);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Bill bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBill.handle(bill);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Bill bill) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfBill.handle(bill);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Bill>> getAllBills() {
    final String _sql = "SELECT * FROM bills ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"bills"}, false, new Callable<List<Bill>>() {
      @Override
      @Nullable
      public List<Bill> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfReceiptUri = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptUri");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPaymentApp = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentApp");
          final List<Bill> _result = new ArrayList<Bill>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Bill _item;
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpReceiptUri;
            if (_cursor.isNull(_cursorIndexOfReceiptUri)) {
              _tmpReceiptUri = null;
            } else {
              _tmpReceiptUri = _cursor.getString(_cursorIndexOfReceiptUri);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpPaymentApp;
            if (_cursor.isNull(_cursorIndexOfPaymentApp)) {
              _tmpPaymentApp = null;
            } else {
              _tmpPaymentApp = _cursor.getString(_cursorIndexOfPaymentApp);
            }
            _item = new Bill(_tmpAmount,_tmpCategory,_tmpNote,_tmpTimestamp,_tmpReceiptUri,_tmpSource,_tmpPaymentApp);
            _item.id = _cursor.getLong(_cursorIndexOfId);
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
  public List<Bill> getAllBillsOnce() {
    final String _sql = "SELECT * FROM bills ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfReceiptUri = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptUri");
      final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
      final int _cursorIndexOfPaymentApp = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentApp");
      final List<Bill> _result = new ArrayList<Bill>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Bill _item;
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        final String _tmpNote;
        if (_cursor.isNull(_cursorIndexOfNote)) {
          _tmpNote = null;
        } else {
          _tmpNote = _cursor.getString(_cursorIndexOfNote);
        }
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        final String _tmpReceiptUri;
        if (_cursor.isNull(_cursorIndexOfReceiptUri)) {
          _tmpReceiptUri = null;
        } else {
          _tmpReceiptUri = _cursor.getString(_cursorIndexOfReceiptUri);
        }
        final String _tmpSource;
        if (_cursor.isNull(_cursorIndexOfSource)) {
          _tmpSource = null;
        } else {
          _tmpSource = _cursor.getString(_cursorIndexOfSource);
        }
        final String _tmpPaymentApp;
        if (_cursor.isNull(_cursorIndexOfPaymentApp)) {
          _tmpPaymentApp = null;
        } else {
          _tmpPaymentApp = _cursor.getString(_cursorIndexOfPaymentApp);
        }
        _item = new Bill(_tmpAmount,_tmpCategory,_tmpNote,_tmpTimestamp,_tmpReceiptUri,_tmpSource,_tmpPaymentApp);
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Bill>> getBillsBetween(final long start, final long endExclusive) {
    final String _sql = "SELECT * FROM bills WHERE timestamp >= ? AND timestamp < ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endExclusive);
    return __db.getInvalidationTracker().createLiveData(new String[] {"bills"}, false, new Callable<List<Bill>>() {
      @Override
      @Nullable
      public List<Bill> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfReceiptUri = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptUri");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPaymentApp = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentApp");
          final List<Bill> _result = new ArrayList<Bill>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Bill _item;
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpReceiptUri;
            if (_cursor.isNull(_cursorIndexOfReceiptUri)) {
              _tmpReceiptUri = null;
            } else {
              _tmpReceiptUri = _cursor.getString(_cursorIndexOfReceiptUri);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpPaymentApp;
            if (_cursor.isNull(_cursorIndexOfPaymentApp)) {
              _tmpPaymentApp = null;
            } else {
              _tmpPaymentApp = _cursor.getString(_cursorIndexOfPaymentApp);
            }
            _item = new Bill(_tmpAmount,_tmpCategory,_tmpNote,_tmpTimestamp,_tmpReceiptUri,_tmpSource,_tmpPaymentApp);
            _item.id = _cursor.getLong(_cursorIndexOfId);
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
  public LiveData<List<CategoryTotal>> getCategoryTotalsBetween(final long start,
      final long endExclusive) {
    final String _sql = "SELECT category AS category, SUM(amount) AS total FROM bills WHERE timestamp >= ? AND timestamp < ? GROUP BY category ORDER BY total DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endExclusive);
    return __db.getInvalidationTracker().createLiveData(new String[] {"bills"}, false, new Callable<List<CategoryTotal>>() {
      @Override
      @Nullable
      public List<CategoryTotal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfTotal = 1;
          final List<CategoryTotal> _result = new ArrayList<CategoryTotal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryTotal _item;
            _item = new CategoryTotal();
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _item.category = null;
            } else {
              _item.category = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.total = _cursor.getDouble(_cursorIndexOfTotal);
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
  public LiveData<Bill> getBillById(final long id) {
    final String _sql = "SELECT * FROM bills WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[] {"bills"}, false, new Callable<Bill>() {
      @Override
      @Nullable
      public Bill call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfReceiptUri = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptUri");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPaymentApp = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentApp");
          final Bill _result;
          if (_cursor.moveToFirst()) {
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpReceiptUri;
            if (_cursor.isNull(_cursorIndexOfReceiptUri)) {
              _tmpReceiptUri = null;
            } else {
              _tmpReceiptUri = _cursor.getString(_cursorIndexOfReceiptUri);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpPaymentApp;
            if (_cursor.isNull(_cursorIndexOfPaymentApp)) {
              _tmpPaymentApp = null;
            } else {
              _tmpPaymentApp = _cursor.getString(_cursorIndexOfPaymentApp);
            }
            _result = new Bill(_tmpAmount,_tmpCategory,_tmpNote,_tmpTimestamp,_tmpReceiptUri,_tmpSource,_tmpPaymentApp);
            _result.id = _cursor.getLong(_cursorIndexOfId);
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
