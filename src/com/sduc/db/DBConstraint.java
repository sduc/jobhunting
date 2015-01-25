package com.sduc.db;

public class DBConstraint {

	private DBConstraintType cType;
	
	public DBConstraint(DBConstraintType type) {
		cType = type;
	}

	@Override
	public String toString() {
		return cType.toString();
	}
	
}

class ConstraintWithValue extends DBConstraint {

	private String value;
	
	public ConstraintWithValue(
			DBConstraintType type,
			String value) {
		super(type);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.value; 
	}
	
}

class ForeignKey extends DBConstraint {
	
	private String fkCol;
	private String tableRef;
	private String colRef;
	
	public ForeignKey(String fkCol, String tableRef, String colRef) {
		super(DBConstraintType.F_KEY);
		this.fkCol = fkCol;
		this.tableRef = tableRef;
		this.colRef = colRef;
	}

	@Override
	public String toString() {
		return super.toString() + ", " 
				+ "FOREIGN_KEY(" + this.fkCol + ") REFERENCES " 
				+ tableRef + "(" + colRef + ")";
	}
	
}
