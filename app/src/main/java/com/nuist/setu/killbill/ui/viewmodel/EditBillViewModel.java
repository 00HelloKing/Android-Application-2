package com.nuist.setu.killbill.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nuist.setu.killbill.data.Bill;
import com.nuist.setu.killbill.data.BillRepository;

/**
 * ViewModel for Add/Edit screen.
 */
public class EditBillViewModel extends AndroidViewModel {

    private final BillRepository repository;

    public EditBillViewModel(@NonNull Application application) {
        super(application);
        repository = BillRepository.getInstance(application);
    }

    public LiveData<Bill> loadBill(long id) {
        return repository.getBillById(id);
    }

    public void insert(Bill bill) {
        repository.insert(bill);
    }

    public void update(Bill bill) {
        repository.update(bill);
    }

    public void delete(Bill bill) {
        repository.delete(bill);
    }
}
