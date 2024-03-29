package com.cgstore.domain;
/**
 * 购物车项实体
 * @author 王
 *
 */
public class CartItem {
	private Product product;//商品对象
	private Integer	count;//购买数量
	private Double	subtotal=0.0;//小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	
	public CartItem(Product product, Integer count) {
		this.product = product;
		this.count = count;
	}
	public CartItem() { }
}
