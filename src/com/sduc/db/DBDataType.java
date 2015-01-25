package com.sduc.db;

public enum DBDataType {
	
	INTEGER("INTEGER"),
	TEXT("TEXT"),
	REAL("REAL"),
	NULL("NULL"),
	BLOB("BLOB");
	
	private String repr;
	
	private DBDataType(String repr) {
		this.repr = repr;
	}
	
	@Override
	public String toString()
	{
		return repr;
	}

}
