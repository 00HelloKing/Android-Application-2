package com.nuist.setu.killbill.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.nuist.setu.killbill.data.BillRepository;
import com.nuist.setu.killbill.data.CategoryTotal;
import com.nuist.setu.killbill.util.DateTimeUtils;

import java.util.List;

/**
 * ViewModel for monthly statistics.
 */
public class StatsViewModel extends AndroidViewModel {

    private final BillRepository repository;

    private final MutableLiveData<Long> selectedMonthStart = new MutableLiveData<>();
    private final LiveData<List<CategoryTotal>> categoryTotals;

    public StatsViewModel(@NonNull Application application) {
        super(application);
        repository = BillRepository.getInstance(application);

        long now = System.currentTimeMillis();
        selectedMonthStart.setValue(DateTimeUtils.startOfMonth(now));

        categoryTotals = Transformations.switchMap(selectedMonthStart, start ->
                repository.getCategoryTotalsBetween(start, DateTimeUtils.endExclusiveOfMonth(start))
        );
    }

    public LiveData<Long> getSelectedMonthStart() {
        return selectedMonthStart;
    }

    public void setSelectedMonth(long timestamp) {
        selectedMonthStart.setValue(DateTimeUtils.startOfMonth(timestamp));
    }

    public LiveData<List<CategoryTotal>> getCategoryTotals() {
        return categoryTotals;
    }
}
