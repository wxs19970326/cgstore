package com.cgstore.domain;
/**
 * 所卖商品类型实体
 * @author 王
 *
 */
public class Category {
	private String cid;
	private String cname;
	
	public Category() {

	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}
	
	
	
}
