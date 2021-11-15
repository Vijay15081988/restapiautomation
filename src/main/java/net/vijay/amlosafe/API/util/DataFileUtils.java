package net.vijay.amlosafe.API.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class DataFileUtils {


    public static JSONObject getDataFromJSONFile(String path) {
        Object obj;
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {

            File file = new File(path);
            obj = parser.parse(new FileReader(file.getAbsolutePath()));
            jsonObject = (JSONObject) obj;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return (JSONObject) jsonObject.get("data");
    }


    static void writeRaw(List records, String fileName) {
        File file = new File("src\\main\\resources\\UserManagementAPIData\\AuthModule\\CreateAuthModule\\" + fileName + ".txt");


        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            System.out.print("Writing raw... ");
            write(records, writer);
        } catch (Exception w) {
            w.printStackTrace();
        }


        //Create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet(" Request Info ");

        //Create row object
        XSSFRow row;
        int rowid = 0;

        for (Object key : APISetup.requestInfo) {

            row = spreadsheet.createRow(rowid++);

            Object[] objectArr = (Object[]) key;

            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);

                if(obj instanceof JSONObject) {
                    cell.setCellValue( obj.toString());
                }else if(obj instanceof Integer) {
                    cell.setCellValue(""+obj);
                }else
                {
                    cell.setCellValue((String) obj);

                }
            }
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(
                    new File("src\\main\\resources\\UserManagementAPIData\\AuthModule\\CreateAuthModule\\" + fileName + ".xlsx"));
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        APISetup.fileWriteList.add("Status code ");

        // DataFileUtils.writeRaw(APISetup.fileWriteList);

    }


    static void write(List<String> records, Writer writer) throws IOException {
        long start = System.currentTimeMillis();
        for (String record : records) {
            writer.write(record);
        }
        // writer.flush(); // close() should take care of this
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f + " seconds");
    }


}
