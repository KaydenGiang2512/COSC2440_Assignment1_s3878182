package Main;

import HelperPrograms.MenuDisplay;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        MenuDisplay m = new MenuDisplay();
        m.selectCSVFileAsDatabase();
        m.userInterface();


    }
}
