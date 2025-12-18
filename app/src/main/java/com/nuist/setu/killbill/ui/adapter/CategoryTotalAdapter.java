package com.nuist.setu.killbill.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nuist.setu.killbill.data.CategoryTotal;
import com.nuist.setu.killbill.databinding.ItemCategoryTotalBinding;
import com.nuist.setu.killbill.util.MoneyUtils;

public class CategoryTotalAdapter extends ListAdapter<CategoryTotal, CategoryTotalAdapter.VH> {

    public CategoryTotalAdapter() {
        super(DIFF);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryTotalBinding binding = ItemCategoryTotalBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CategoryTotal ct = getItem(position);
        holder.binding.tvCategory.setText(ct.category);
        holder.binding.tvTotal.setText(MoneyUtils.formatCny(ct.total));
    }

    static class VH extends RecyclerView.ViewHolder {
        final ItemCategoryTotalBinding binding;

        VH(ItemCategoryTotalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static final DiffUtil.ItemCallback<CategoryTotal> DIFF = new DiffUtil.ItemCallback<CategoryTotal>() {
        @Override
        public boolean areItemsTheSame(@NonNull CategoryTotal oldItem, @NonNull CategoryTotal newItem) {
            if (oldItem.category == null) return newItem.category == null;
            return oldItem.category.equals(newItem.category);
        }

        @Override
        public boolean areContentsTheSame(@NonNull CategoryTotal oldItem, @NonNull CategoryTotal newItem) {
            return oldItem.total == newItem.total
                    && ((oldItem.category == null && newItem.category == null)
                    || (oldItem.category != null && oldItem.category.equals(newItem.category)));
        }
    };
}
