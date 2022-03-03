package tut01;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.jws.WebService;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.FileOutputStream;

import java.nio.file.Path;
import java.nio.file.Paths;


@WebService(endpointInterface = "tut01.Receipt")
public class ReceiptImpl implements Receipt {
    public String makePDF(int orderNumber) {

        Document document = new Document();
        String fileName = "objednavka" + orderNumber + ".pdf";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            PdfWriter.getInstance(document, fos);


        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
            Font font2 = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
            Paragraph chapterTitle = new Paragraph("Objednávka " + orderNumber, font);
            Chapter chapter1 = new Chapter(chapterTitle, 1);
            chapter1.setNumberDepth(0);

        document.add(chapter1);

            Paragraph paragraphOne = new Paragraph("Shrnutí objednávky", font2);
            document.add(paragraphOne);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100); // Width 100%
            table.setSpacingBefore(10f); // Space before table
            table.setSpacingAfter(10f); // Space after table
            addTableHeader(table);
            addRows(table);
            document.add(table);


        document.close();
        saveDocument(document, fos);

        return fileName;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Název produktu", "Barva", "Cena")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        table.addCell("Svetr Bea");
        table.addCell("béžová");
        table.addCell("349");
    }


    public  void saveDocument(Document document, FileOutputStream fos) throws IOException {

        fos.flush();
        fos.close();

    }

    private int readPriceFromFile(int orderNumber){
        Map<Integer, Integer> map = new HashMap<>();
        try {
            File myObj = new File("orders.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String str[] = line.split(";");
                for (int i = 1; i < str.length; i++) {
                    map.put(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map.get(orderNumber);
    }

    public double priceDPH(int orderNumber, int dph){
        dph = dph == 0 ? 15 : dph;
        int price = readPriceFromFile(orderNumber);
        return price * dph * 0.01;
    }
}