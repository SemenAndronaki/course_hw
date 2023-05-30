import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipFileTest {
    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void parseZipCSVContentTest() throws URISyntaxException, IOException, CsvException {
        ZipFile zipFile = new ZipFile(new File(cl.getResource("Desktop.zip").toURI()));
        ZipEntry zipCsvEntry = zipFile.getEntry("file.csv");
        try (InputStream is = zipFile.getInputStream(zipCsvEntry)) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(is));
            List<String[]> list = csvReader.readAll();
            assertThat(list)
                    .hasSize(4)
                    .contains(new String[]{"иван;100"});
        }
    }

    @Test
    void parseZipPDFContentTest() throws URISyntaxException, IOException, CsvException {
        ZipFile zipFile = new ZipFile(new File(cl.getResource("Desktop.zip").toURI()));
        ZipEntry zipPdfEntry = zipFile.getEntry("hello.pdf");
        try (InputStream is = zipFile.getInputStream(zipPdfEntry)) {
            PDF parsed = new PDF(is);
            assertThat(parsed.author).isEqualTo("Semen Andronaki");
        }
    }

//    @Test
//    void parseZipXLSXContentTest() throws URISyntaxException, IOException, CsvException {
//        ZipFile zipFile = new ZipFile(new File(cl.getResource("Desktop.zip").toURI()));
//        ZipEntry zipXlsxEntry = zipFile.getEntry("Book1.xlsx");
//        try (InputStream is = zipFile.getInputStream(zipXlsxEntry)) {
//            XLS parsed = new XLS(is);
//            assertThat(parsed.excel.getSheetAt(0)
//                    .getRow(0).getCell(0)
//                    .getStringCellValue())
//                    .isEqualTo("имя");
//        }
//    }
}
