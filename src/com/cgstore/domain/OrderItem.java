package com.cgstore.domain;
/**
 * 订单项实体
 * @author 王
 *
 */
public class OrderItem {
	//订单项ID
	private String itemid;
	
	//商品数量
	private Integer count;
	
	//消费金额（小计）
	private Double subtotal;
	
	//订单项所包含的商品
	private Product product;
	
	//属于哪个订单
	private Order order;
	
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	
}
