package com.lx.entity;

public class OrderItemDetailResponse extends Product {
    private int count;//订单项内商品的购买数量
    private double subtotal;//订单项小计

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
