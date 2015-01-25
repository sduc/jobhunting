package com.sduc.db;

public enum DBConstraintType {
	
	P_KEY("PRIMARY KEY"),
	F_KEY(null),
	NOT_NULL("NOT NULL"),
	UNIQUE("UNIQUE"),
	CHECK("CHECK"),
	DEFAULT("DEFAULT");
	
	private String repr;
	
	private DBConstraintType(String repr) {
		this.repr = repr;
	}
	
	@Override
	public String toString() {
		return repr;
	}

}
