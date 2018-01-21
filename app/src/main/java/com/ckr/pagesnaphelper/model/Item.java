package com.ckr.pagesnaphelper.model;

/**
 * Created by PC大佬 on 2018/1/13.
 */

public class Item implements Cloneable{
	private String name;
	private int position;

	public Item() {
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
