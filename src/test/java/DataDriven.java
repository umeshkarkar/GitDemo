import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String testcaseName) throws IOException {
		
		ArrayList<String> list= new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\UKarkar2\\Umesh\\dataDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				// Identify testcases column by scanning the entire 1st row
				
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstRow = rows.next();

				Iterator<Cell> cell = firstRow.cellIterator();// row is collection of cells
				
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);

				// once column testcase is identified then scan entire testcase
				// column to identify purchase test case row
				
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						// after you grab the purchase testcase row = pull all
						// the data of that row and feed into test
						
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType()==CellType.STRING) {

								list.add(c.getStringCellValue());
							} else {

								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}
				}

			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {

	}

}
