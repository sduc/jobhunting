package com.sduc.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import android.provider.BaseColumns;

public final class DBContract
{

	private DBContract() {}
	
	public  static final String DB_NAME 			= "db.main";
    public  static final int    DB_VERSION		   	= 1;
    private static final String COMMA_SEP          	= ",";
    
	private static String SQL_DELETE_TABLE(String TABLE_NAME) {
	    return "DROP TABLE IF EXISTS " + TABLE_NAME;
	}
	
	private static String SQL_CREATE_TABLE(String TABLE_NAME, Collection<DBColumn> Columns) {
		String ret = "CREATE TABLE " +
                TABLE_NAME + " (";
		for (Iterator<DBColumn> it = Columns.iterator(); it.hasNext();) {
			DBColumn col = (DBColumn) it.next();
			ret += col.toString() + COMMA_SEP;
		}
		ret += ")";
		return ret;
	}
	
	
	/**
	 * @author sduc
	 * 
	 * This is all the tables in the Database
	 *
	 */
	public static enum Table
	{
		
		JobAppTable(
				JobAppTableDescr.TABLE_NAME,
				new DBColumn [] {
						new DBColumnPrimaryKey(
								JobAppTableDescr._ID),
						new DBColumnForeignKey(
								JobAppTableDescr.COLUMN_NAME_COMPANY_FK, 
								CompaniesTableDescr.TABLE_NAME,
								CompaniesTableDescr._ID),
						new DBColumnForeignKey(
								JobAppTableDescr.COLUMN_NAME_CONTACT_FK,
								ContactsTableDescr.TABLE_NAME,
								ContactsTableDescr._ID),
						new DBColumn(
								JobAppTableDescr.COLUMN_NAME_DESCRITPTION,
								DBDataType.TEXT,
								null),
						new DBColumn(
								JobAppTableDescr.COLUMN_NAME_JOB_TITLE,
								DBDataType.TEXT,
								null),
						new DBColumnForeignKey(
								JobAppTableDescr.COLUMN_NAME_LOCATION_FK,
								LocationsTableDescr.TABLE_NAME,
								LocationsTableDescr._ID),
						new DBColumn(
								JobAppTableDescr.COLUMN_NAME_STATUS,
								DBDataType.TEXT,
								null),
				}),
		EventsTable(
				EventsTableDescr.TABLE_NAME,
				new DBColumn[] {
						new DBColumnPrimaryKey(
								EventsTableDescr._ID),
						new DBColumnForeignKey(
								EventsTableDescr.COLUMN_NAME_JOB_FK,
								JobAppTableDescr.TABLE_NAME,
								JobAppTableDescr._ID),
						new DBColumn(
								EventsTableDescr.COLUMN_NAME_DATE,
								DBDataType.TEXT,
								null),
						new DBColumn(
								EventsTableDescr.COLUMN_NAME_TYPE,
								DBDataType.TEXT,
								null)
				}),
		NotesTable(
				NotesTableDescr.TABLE_NAME,
				new DBColumn[] {
						new DBColumnPrimaryKey(
								NotesTableDescr._ID),
						new DBColumnForeignKey(
								NotesTableDescr.COLUMN_NAME_JOB_ID_FK,
								JobAppTableDescr.TABLE_NAME,
								JobAppTableDescr._ID),
						new DBColumn(
								NotesTableDescr.COLUMN_NAME_TITLE,
								DBDataType.TEXT,
								null),
						new DBColumn(
								NotesTableDescr.COLUMN_NAME_CONTENT,
								DBDataType.TEXT,
								null)
				}),
		CompaniesTable(
				CompaniesTableDescr.TABLE_NAME,
				new DBColumn[] {
						new DBColumnPrimaryKey(
								CompaniesTableDescr._ID),
						new DBColumn(
								CompaniesTableDescr.COLUMN_NAME_COMPANY_NAME,
								DBDataType.TEXT,
								null),
						new DBColumn(
								CompaniesTableDescr.COLUMN_NAME_INDUSTRY,
								DBDataType.TEXT,
								null)
				}),
		LocationsTable(
				LocationsTableDescr.TABLE_NAME,
				new DBColumn[] {
						new DBColumnPrimaryKey(
								LocationsTableDescr._ID),
						new DBColumn(
								LocationsTableDescr.COLUMN_NAMES_ADDRESS,
								DBDataType.TEXT,
								null),
						new DBColumn(
								LocationsTableDescr.COLUMN_NAMES_COUNTRY, 
								DBDataType.TEXT, 
								null),
						new DBColumn(
								LocationsTableDescr.COLUMN_NAMES_TOWN,
								DBDataType.TEXT,
								null)
				}),
		ContactsTable(
				ContactsTableDescr.TABLE_NAME,
				new DBColumn[] {
						new DBColumnPrimaryKey(
								ContactsTableDescr._ID),
						new DBColumn(
								ContactsTableDescr.COLUMN_NAMES_CONACT_NAME,
								DBDataType.TEXT,
								null),
						new DBColumn(
								ContactsTableDescr.COLUMN_NAMES_EMAIL,
								DBDataType.TEXT,
								null),
						new DBColumn(
								ContactsTableDescr.COLUMN_NAMES_PHONE,
								DBDataType.TEXT,
								null)
				});
		
		private String tableName;
		private HashMap<String, DBColumn> cols;
		
		private Table(String tName, DBColumn [] cols) {
			this.tableName = tName;
			this.cols = new HashMap<String, DBColumn>();
			for (DBColumn col : cols) {
				this.cols.put(col.getName(), col);
			}
		}
		
		public String getSQLCreate() {
			return SQL_CREATE_TABLE(this.tableName, this.cols.values());
		}
		
		public String getSQLDelete() {
			return SQL_DELETE_TABLE(this.tableName);
		}
		
	}
    
    public static abstract class JobAppTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME       		= "jobapp";
        public static final String COLUMN_NAME_JOB_TITLE 	= "jobTitle";
        public static final String COLUMN_NAME_STATUS 		= "status";
        public static final String COLUMN_NAME_COMPANY_FK	= "company_fk";
        public static final String COLUMN_NAME_LOCATION_FK	= "location_fk";
        public static final String COLUMN_NAME_DESCRITPTION = "description";
        public static final String COLUMN_NAME_CONTACT_FK	= "contact_fk";
    }
    
    public static abstract class EventsTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME       		= "events";
        public static final String COLUMN_NAME_JOB_FK 		= "job_fk";
        public static final String COLUMN_NAME_DATE 		= "date";
        public static final String COLUMN_NAME_TYPE 		= "type";       
    }
    
    public static abstract class NotesTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME				= "notes";
    	public static final String COLUMN_NAME_JOB_ID_FK	= "job_id_fk";
    	public static final String COLUMN_NAME_TITLE		= "title";
    	public static final String COLUMN_NAME_CONTENT		= "content";
    }
    
    public static abstract class CompaniesTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME				= "companies";
    	public static final String COLUMN_NAME_INDUSTRY		= "industry";
    	public static final String COLUMN_NAME_COMPANY_NAME = "name";
    }
    
    public static abstract class LocationsTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME			= "locations";
    	public static final String COLUMN_NAMES_COUNTRY = "country";
    	public static final String COLUMN_NAMES_TOWN	= "town";
    	public static final String COLUMN_NAMES_ADDRESS	= "address";
    }
    
    public static abstract class ContactsTableDescr implements BaseColumns
    {
    	public static final String TABLE_NAME				= "contacts";
    	public static final String COLUMN_NAMES_CONACT_NAME	= "name";
    	public static final String COLUMN_NAMES_EMAIL		= "email";
    	public static final String COLUMN_NAMES_PHONE		= "phone";
    }
    
}