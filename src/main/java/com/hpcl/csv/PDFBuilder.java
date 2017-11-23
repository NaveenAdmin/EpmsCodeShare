package com.hpcl.csv;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author :Prasad.G
 *
 */
public class PDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<PdfParameters> listdata = (List<PdfParameters>) model.get("listdata");
		
		//List<AppParameters> listdata = threeFieldDao.getOnlyDasboardSearchfilters(tableName, twoFieldPersistence.getLocationID(), twoFieldPersistence.getDeviceId(), twoFieldPersistence.getFromdatetime(), twoFieldPersistence.getTodatetime(), twoFieldPersistence);
		
		doc.add(new Paragraph("HPCL Dashboard Report"));
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 1.0f, 2.0f});
		table.setSpacingBefore(10);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);
		
		// write table header 
		cell.setPhrase(new Phrase("Location Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Device Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Earthpit Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("voltage", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("ReceivedDate", font));
		table.addCell(cell);
		
		// write table row data
		for (PdfParameters dashboardData : listdata) {
			table.addCell(dashboardData.getLocationName());
			table.addCell(dashboardData.getDeviceName());
			table.addCell(dashboardData.getEarthpitName());
			table.addCell(dashboardData.getVoltage());
			table.addCell(dashboardData.getReceivedDate());
		}
		
		doc.add(table);
		
	}

}