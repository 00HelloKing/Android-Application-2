package com.nuist.setu.killbill.data;

/**
 * Projection for "category -> sum(amount)" query.
 */
public class CategoryTotal {
    public String category;
    public double total;
}
