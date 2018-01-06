package hu.iit.uni.miskolc.webalk.dao;

import hu.iit.uni.miskolc.webalk.service.dao.exceptions.CreateDataBaseException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

    private static final String con = "jdbc:sqlite:./database/glassShop.db";

    public DataBase() throws CreateDataBaseException {
        createDataBase();
    }

    public static String getCon() {
        return con;
    }

    public void createDataBase() throws CreateDataBaseException {
        File f = new File("./database/glassShop.db");
        if (f.exists() && !f.isDirectory()) {
            return;
        }


        String sql = "CREATE TABLE IF NOT EXISTS `Shop` (\n" +
                "\t`NAME`\tCHAR ( 50 ) NOT NULL,\n" +
                "\t`LOCATION`\tCHAR ( 50 ) NOT NULL,\n" +
                "\tPRIMARY KEY(`NAME`));";

        try (Connection c = DriverManager.getConnection(con)) {
            c.setAutoCommit(false);

            PreparedStatement ps = c.prepareStatement(sql);
            ps.executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS `Employee` (\n" +
                    "\t`IDNUM`\tINT NOT NULL,\n" +
                    "\t`NAME`\tCHAR ( 50 ) NOT NULL,\n" +
                    "\t`GENDER`\tCHAR ( 10 ) NOT NULL,\n" +
                    "\t`SALARY`\tFLOAT NOT NULL,\n" +
                    "\t`POST`\tCHAR ( 20 ) NOT NULL,\n" +
                    "\t`ShopName`\tCHAR ( 50 ) NOT NULL,\n" +
                    "\tFOREIGN KEY(`ShopName`) REFERENCES `Shop`(`NAME`),\n" +
                    "\tPRIMARY KEY(`IDNUM`));";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS `Glasses` (\n" +
                    "\t`Brand`\tCHAR ( 30 ) NOT NULL,\n" +
                    "\t`Model`\tCHAR ( 30 ) NOT NULL,\n" +
                    "\t`Price`\tFLOAT NOT NULL,\n" +
                    "\t`Gender`\tCHAR ( 30 ) NOT NULL,\n" +
                    "\t`Sunglasses`\tINT NOT NULL,\n" +
                    "\t`AvailableAt`\tCHAR ( 50 ) NOT NULL,\n" +
                    "\tFOREIGN KEY(`AvailableAt`) REFERENCES `Shop`(`NAME`)\n);";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS `Accessories` (\n" +
                    "\t`Appellation`\tCHAR ( 30 ) NOT NULL,\n" +
                    "\t`Brand`\tCHAR ( 25 ) NOT NULL,\n" +
                    "\t`Price`\tFLOAT NOT NULL);";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();

            sql = "INSERT INTO `Shop` (NAME,LOCATION) VALUES ('Optiris','Tiszaujvaros'),\n" +
                    " ('Trend Optika','Miskolc');";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();

            sql = "INSERT INTO `Employee` (IDNUM,NAME,GENDER,SALARY,POST,ShopName) VALUES (1,'Horvat Rozi','FEMALE',100000.0,'Optician','Optiris'),\n" +
                    " (2,'Kubuk Matyi','MALE',135000.0,'Mechanist','Trend Optika'),\n" +
                    " (3,'Teszt Elek','MALE',90000.0,'Seller','Trend Optika'),\n" +
                    " (4,'Mester Eszter','FEMALE',85000.0,'Optician','Optiris');";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();

            sql = "INSERT INTO `Glasses` (Brand,Model,Price,Gender,Sunglasses,AvailableAt) VALUES ('Oakley','Batwolf',30000.0,'MALE',1,'Optiris'),\n" +
                    " ('Ray-Ban','Dinesh',25000.0,'FEMALE',0,'Trend Optika'),\n" +
                    " ('Levis','Eyewear',10000.0,'FEMALE',0,'Trend Optika');";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();

            sql = "INSERT INTO `Accessories` (Appellation,Brand,Price) VALUES ('Tok','Oakley',1000.0),\n" +
                    " ('Torlokendo','Hoya',800.0),\n" +
                    " ('Tok','Optiris',200.0);";
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
            c.commit();
            ps.close();
        } catch (SQLException e) {
            throw new CreateDataBaseException(e);
        }
    }
}
