package org.example.contactsbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.contacts.book.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class DataTransformer {



    public void writeToXls(List<Contact> contacts, OutputStream outputStream) throws IOException {
        try {
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet();
        for (int i=0;i<contacts.size();i++) {
            Contact c = contacts.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(c.getName());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(c.getEmail());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(c.getNumber());

        }

            Row row0=sheet.createRow(0);
            Cell cell01=row0.createCell(0);
            cell01.setCellValue("Name");
            Cell cell02=row0.createCell(1);
            cell02.setCellValue("Mail");
            Cell cell03=row0.createCell(2);
            cell03.setCellValue("Number");


            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contact> readFromXls(InputStream inputStream) throws IOException {

        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet=workbook.getSheetAt(0);
            List<Contact> contacts=new ArrayList<>();
            int i=0;
            for (Row row:sheet){
                if (i==0){
                    i++;
                    continue;
                }
                Cell nameCell=row.getCell(0);
                String name=nameCell.getStringCellValue();
                Cell emailCell=row.getCell(1);
                String mail=emailCell.getStringCellValue();
                Cell numberCell=row.getCell(2);
                String number=numberCell.getStringCellValue();


                Contact contact=new Contact();
                contact.setName(name);
                contact.setEmail(mail);
                contact.setNumber(number);
                contacts.add(contact);
            }

            return contacts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
