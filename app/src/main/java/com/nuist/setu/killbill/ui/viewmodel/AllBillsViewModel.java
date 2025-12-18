package com.nuist.setu.killbill.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nuist.setu.killbill.data.Bill;
import com.nuist.setu.killbill.data.BillRepository;

import java.util.List;

/**
 * ViewModel for all bills list.
 */
public class AllBillsViewModel extends AndroidViewModel {

    private final BillRepository repository;

    private final LiveData<List<Bill>> allBills;

    public AllBillsViewModel(@NonNull Application application) {
        super(application);
        repository = BillRepository.getInstance(application);
        allBills = repository.getAllBills();
    }

    public LiveData<List<Bill>> getAllBills() {
        return allBills;
    }

    public void insert(Bill bill) {
        repository.insert(bill);
    }

    public void delete(Bill bill) {
        repository.delete(bill);
    }
}
