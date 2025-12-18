package com.nuist.setu.killbill.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.nuist.setu.killbill.data.Bill;
import com.nuist.setu.killbill.data.BillRepository;
import com.nuist.setu.killbill.util.DateTimeUtils;

import java.util.List;

/**
 * ViewModel for daily bills.
 */
public class DailyViewModel extends AndroidViewModel {

    private final BillRepository repository;

    private final MutableLiveData<Long> selectedDayStart = new MutableLiveData<>();

    private final LiveData<List<Bill>> bills;
    private final LiveData<Double> totalAmount;

    public DailyViewModel(@NonNull Application application) {
        super(application);
        repository = BillRepository.getInstance(application);

        long now = System.currentTimeMillis();
        selectedDayStart.setValue(DateTimeUtils.startOfDay(now));

        bills = Transformations.switchMap(selectedDayStart, start ->
                repository.getBillsBetween(start, DateTimeUtils.endExclusiveOfDay(start))
        );

        totalAmount = Transformations.map(bills, list -> {
            double sum = 0;
            if (list != null) {
                for (Bill b : list) {
                    sum += b.amount;
                }
            }
            return sum;
        });
    }

    public LiveData<Long> getSelectedDayStart() {
        return selectedDayStart;
    }

    public void setSelectedDate(long timestamp) {
        selectedDayStart.setValue(DateTimeUtils.startOfDay(timestamp));
    }

    public LiveData<List<Bill>> getBills() {
        return bills;
    }

    public LiveData<Double> getTotalAmount() {
        return totalAmount;
    }

    public void insert(Bill bill) {
        repository.insert(bill);
    }

    public void delete(Bill bill) {
        repository.delete(bill);
    }
}
