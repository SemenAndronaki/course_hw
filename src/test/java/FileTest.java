import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void downloadTest() throws IOException {
        open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File file = $("#raw-url").download();
        try (InputStream is = new FileInputStream(file)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .contains("Eclipse Public License - v 2.0");
        }
    }

    @Test
    void uploadTest() throws IOException {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("upload.txt");
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(text("upload.txt"));
    }

    @Test
    void parsePdfTest() throws IOException {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File filePdf = $(byText("PDF download")).download();
        PDF parsed = new PDF(filePdf);
        assertThat(parsed.author).contains("Marc Philipp");
    }

    @Test
    void parseXslTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("file.xlsx")) {
            XLS parsed = new XLS(is);
            assertThat(parsed.excel.getSheetAt(0)
                    .getRow(0).getCell(0)
                    .getStringCellValue())
                    .isEqualTo("имя");
        }
    }

    @Test
    void parseCSVTest() throws IOException, CsvException {
        try (InputStream is = cl.getResourceAsStream("file.csv")) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(is));
            List<String[]> list = csvReader.readAll();
            assertThat(list)
                    .hasSize(4)
                    .contains(new String[]{"иван;100"});
        }
    }

    @Test
    void parseZipTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("file.zip");
             ZipInputStream zipInputStream = new ZipInputStream(is)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                assertThat(zipEntry.getName()).isEqualTo("file.txt");
            }
        }
    }
}
