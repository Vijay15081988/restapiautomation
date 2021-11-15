package net.vijay.amlosafe.API.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OneKosmosUtil extends APISetup {

    public static String TESTDATA_SHEET_PATH = "com/promotions/web/TestData/TestData.xlsx";

    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }

    public static void waitFor(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void archiveTestReport() {

        String reportName = configProperty.getTestReportName();

        String lastTestReportFilePath = System.getProperty("user.dir") + "/src/test/resources/testReports/";
        String archiveReportPath = System.getProperty("user.dir") + "/src/test/resources/archivedTestReport/";

        Date date = new Date();
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String formatedDate = dateFormate.format(date);
        String archiveTestReportName = formatedDate + "_" + reportName;

        File oldReport = new File(lastTestReportFilePath + reportName);

        File newFile = new File(archiveReportPath + archiveTestReportName);

        System.out.println(oldReport.exists());

        if (oldReport.exists()) {
            System.out.println("inside if");
            oldReport.renameTo(newFile);
            oldReport.delete();
        }

    }

    public static String decodeString(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        String decodedString = new String(decodedBytes);
        return decodedString;

    }

    public static String encodeString(String str) {
        String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
        return encodedString;

    }

    public Object[][] readTestDataJSON(String path) {
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject jsonData = gson.fromJson(br, JsonObject.class);
        JsonArray testcaseArray = jsonData.getAsJsonArray("testcases");
        Object[][] obj = new Object[testcaseArray.size()][1];
        int index = 0;
        for (Object[] testcase : obj) {
            testcase[0] = testcaseArray.get(index++);
        }
        return obj;
    }

}
