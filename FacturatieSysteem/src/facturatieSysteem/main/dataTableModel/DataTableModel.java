package facturatieSysteem.main.dataTableModel;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

/**
 * This class provides a data model for the JTable component in the UI. Using this data model
 * enables us to quickly perform operations on the table containing member information. The contents of 
 * this data model are automatically displayed in the corresponding JTable. 
 * 
 * @author IVH5B2
 */
@SuppressWarnings("serial")
public class DataTableModel extends AbstractTableModel {

	/** The cache. */
	private Vector<String[]> cache; // will hold String[] objects . . .
	
	/** The col count. */
	private int colCount;
	
	/** The headers. */
	private String[] headers;

	/**
	 * Constructor.
	 */
	public DataTableModel() {
		colCount = 4;
		headers = new String[colCount];
		cache = new Vector<String[]>();
	}

	/**
	 * Set the values of the data table model using the String[][]. This is to be able to set the values of the table when
	 * using this dataTableModel in an other class
	 * 
	 * @param values String[][] containing the values to be set.
	 */
	public void setValues(String[][] values) {	
		cache = new Vector<String[]>();
		try {
			int i = 0;
			while (i < values.length) {
				String[] row = new String[colCount];
				for (int j = 0; j < colCount; j++) {
					row[j] = values[i][j];
				}
				cache.addElement(row);
				i++;
			}
			// notify everyone that we have a new table.
			fireTableChanged(null); 
		} catch (Exception e) {
			cache = new Vector<String[]>();
			//logger.error(e.getMessage());
		}
	}
	
	/**
	 * Set the values of the data table model using the arrayList of Klant. The table is filled by looping through the
	 * klanten arrayList, so the klant his name, bsn, date of birth, adres can be shown.
	 *
	 * @param klanten the new values
	 */
	public void setValues(ArrayList<Klant> klanten) {
		
		//logger.debug("setValues klanten");
		int colcount = getColumnCount();
		int rowcount = klanten.size();
		String[][] values = new String[rowcount][colcount];
		
		for(int i = 0; i < klanten.size(); i++) 
		{
			Klant klant = klanten.get(i);
			
			String memberNaam = klant.getNaam();
			String bsn = klant.getBSN();
			String gebDatum = klant.getGeboortedatum();
			String adres = klant.getAdres();
			
			String[] value = { memberNaam, bsn, gebDatum, adres};
	
			values[i] = value;
		}
		
		setValues(values);
	}

	/**
	 * Set the header row of the table. 
	 * The header row displays the column names in the table. 
	 * 
	 * @param header Array of strings containing the column names.
	 */
	public void setTableHeader(String[] header) {	
		headers = new String[colCount];
		for (int h = 0; h < colCount; h++) {
			headers[h] = header[h];
		}
	}

	/**
	 * Get the name of column located at [i].
	 *
	 * @param i of type int
	 * @return the column name
	 */
	public String getColumnName(int i) { 
		return headers[i];
	}

	/**
	 * Get the number of columns in the model.
	 *
	 * @return the column count
	 */
	public int getColumnCount() {
		return colCount;
	}

	/**
	 * Get the number of rows.
	 *
	 * @return the row count
	 */
	public int getRowCount() {
		return cache.size();
	}

	/**
	 * Get the value at [row,col].
	 *
	 * @param row the row
	 * @param col the col
	 * @return the value at
	 */
	public Object getValueAt(int row, int col) {
		return ((String[]) cache.elementAt(row))[col];
	}
}