package ExcelIntegration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excell {

	public static ArrayList<String> testData(String sheetname, String testcasename) throws IOException {
		ArrayList<String> ar = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Users\\SRIKANTH RAMAGIRI\\OneDrive\\Desktop\\ApiTestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetcount = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetcount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetname)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Identify Testcases coloum by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);
				/// once coloumn is identified then scan entire testcase coloum to identify
				/// purcjhase testcase row

				while(rows.hasNext())
				{
					
					Row r=rows.next();
					
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						
						////after you grab purchase testcase row = pull all the data of that row and feed into test
						
						Iterator<Cell>  cv=r.cellIterator();
						while(cv.hasNext())
						{
						Cell c=	cv.next();
						if(c.getCellType()==CellType.STRING)
						{
							
						ar.add(c.getStringCellValue());
						}
						else{
							
							ar.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						
						}
						}
					}
					
				
				}
			}
		}
		return ar;

	}

	public static void main(String[] args) throws IOException {

		// System.out.println(testData("TestData","purchase"));
		ArrayList<String> list = testData("TestData", "PURCHASE");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));

		System.out.println(list.get(3));
	}

}
