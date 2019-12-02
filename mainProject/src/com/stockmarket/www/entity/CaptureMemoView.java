package com.stockmarket.www.entity;

import java.util.Date;

public class CaptureMemoView extends CaptureMemo {
	private String codeNumName;

	public CaptureMemoView(String name, String title, Date regdate) {
		super(title, regdate);
		this.codeNumName = name;
	}
	
	@Override
	public String toString() {
		return super.toString() + "["+ codeNumName + "]";
	}
}
