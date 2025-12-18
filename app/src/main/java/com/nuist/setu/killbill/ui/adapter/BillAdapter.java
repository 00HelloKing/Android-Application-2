package com.nuist.setu.killbill.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nuist.setu.killbill.data.Bill;
import com.nuist.setu.killbill.databinding.ItemBillBinding;
import com.nuist.setu.killbill.util.DateTimeUtils;
import com.nuist.setu.killbill.util.MoneyUtils;

public class BillAdapter extends ListAdapter<Bill, BillAdapter.VH> {

    public interface OnBillClickListener {
        void onClick(@NonNull Bill bill);
    }

    private final OnBillClickListener listener;

    public BillAdapter(@NonNull OnBillClickListener listener) {
        super(DIFF);
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillBinding binding = ItemBillBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Bill bill = getItem(position);

        holder.binding.tvCategory.setText(bill.category);
        holder.binding.tvAmount.setText(MoneyUtils.formatCny(bill.amount));

        String note = bill.note;
        if (TextUtils.isEmpty(note)) {
            note = bill.source != null ? bill.source : "";
        }
        holder.binding.tvNote.setText(note);

        holder.binding.tvTime.setText(DateTimeUtils.formatTime(bill.timestamp));

        boolean hasReceipt = !TextUtils.isEmpty(bill.receiptUri);
        holder.binding.ivReceipt.setVisibility(hasReceipt ? android.view.View.VISIBLE : android.view.View.GONE);

        holder.binding.getRoot().setOnClickListener(v -> listener.onClick(bill));
    }

    static class VH extends RecyclerView.ViewHolder {
        final ItemBillBinding binding;

        VH(ItemBillBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static final DiffUtil.ItemCallback<Bill> DIFF = new DiffUtil.ItemCallback<Bill>() {
        @Override
        public boolean areItemsTheSame(@NonNull Bill oldItem, @NonNull Bill newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Bill oldItem, @NonNull Bill newItem) {
            return oldItem.amount == newItem.amount
                    && TextUtils.equals(oldItem.category, newItem.category)
                    && TextUtils.equals(oldItem.note, newItem.note)
                    && oldItem.timestamp == newItem.timestamp
                    && TextUtils.equals(oldItem.receiptUri, newItem.receiptUri)
                    && TextUtils.equals(oldItem.source, newItem.source)
                    && TextUtils.equals(oldItem.paymentApp, newItem.paymentApp);
        }
    };
}
