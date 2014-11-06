package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;
import facturatieSysteem.main.dataTableModel.DataTableModel;

/**
 * The Class DataTableModelChangeType.
 */
public class DataTableModelChangeType extends AbstractTableModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cache. */
	private  Vector<String[]> cache; // will hold String[] objects . . .
	
	/** The col count. */
	private  int colCount;
	
	/** The headers. */
	private  String[] headers;

	// Get a logger instance for the current class
	 /** The logger. */
	Logger logger = Logger.getLogger(DataTableModel.class);

	/**
	 * Constructor.
	 */
	public DataTableModelChangeType() {
		logger.debug("Constructor");

		// colcount is hardcoded here; change if you want to display more columns.
		colCount = 3;
		headers = new String[colCount];
		cache = new Vector<String[]>();
	}

	/**
	 * Set the values of the data table model.
	 * 
	 * @param values String[][] containing the values to be set.
	 */
	public void setValues(String[][] values) {
		logger.debug("setValues String[][]");
	
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
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Set the values of the data table model.
	 *
	 * @param verType the new values
	 */
	public void setValues(ArrayList<Verzekeringstype> verType) {
		
		logger.debug("setValues verzekeringen");
		int colcount = getColumnCount();		int rowcount = verType.size();
		String[][] values = new String[rowcount][colcount];
		
		for(int i = 0; i < verType.size(); i++) 
		{
			Verzekeringstype type = verType.get(i);
			
			String TypeNr = type.getNr();
			String Naam = type.getNaam();
			String eigenRisico = String.valueOf(type.getEigenRisico());

			
			String[] value = { TypeNr, Naam, eigenRisico};
	
			values[i] = value;
		}
		
		setValues(values);
	}

	/**
	 * Set the header row of the table. The header row displays the column names in the table.
	 * 
	 * @param header Array of strings containing the column names.
	 */
	public void setTableHeader(String[] header) {
		logger.debug("setTableHeader");
	
		headers = new String[colCount];
		for (int h = 0; h < colCount; h++) {
			headers[h] = header[h];
		}
	}

	/**
	 * Get the name of column [i].
	 *
	 * @param i the i
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
