package com.sduc.db;

public class DBColumn
{
	
	private String name;
	private DBDataType type;
	private DBConstraint constraint;
	
	/**
	 * @param name
	 * @param type
	 * @param constraint
	 */
	public DBColumn(
			String 			name, 
			DBDataType 		type,
			DBConstraint 	constraint) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret = name + " " + type;
		if(constraint != null)
			ret += " " + constraint;
		return ret;
	}
	
	public String getName() {
		return name;
	}
	
}

class DBColumnPrimaryKey extends DBColumn
{
	
	/**
	 * @param name
	 */
	public DBColumnPrimaryKey(String name) {
		super(
			name, 
			DBDataType.INTEGER, 
			new DBConstraint(DBConstraintType.P_KEY)
		);
	}
	
}

class DBColumnForeignKey extends DBColumn
{
	
	/**
	 * @param colName
	 * @param tableRef
	 * @param colRef
	 */
	public DBColumnForeignKey(
			String colName, 
			String tableRef, 
			String colRef) {
		super(
			colName, 
			DBDataType.INTEGER, 
			new ForeignKey(colName, tableRef, colRef)
		);
	}
	
}