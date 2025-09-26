package io.loop.utilities;

public class TestExcel {

    public static void main(String[] args) {


        ExcelUtils excelUtils = new ExcelUtils("C:\\Users\\Shelby\\IdeaProjects\\cucumber-project-b5\\src\\test\\resources\\Excel1.xlsx", "Sheet1");

        System.out.println("excelUtils.getCellData(0,0) = " + excelUtils.getCellData(0, 0));
        System.out.println("excelUtils.getCellData(0,0) = " + excelUtils.getCellData(0, 1));

        excelUtils.setCellData("Owner", 0, 2);
        excelUtils.setCellData("Feyruz", 1, 2);


    }

}