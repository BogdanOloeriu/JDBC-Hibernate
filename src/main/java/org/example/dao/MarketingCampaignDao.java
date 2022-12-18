package org.example.dao;

import org.example.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarketingCampaignDao {

    private final Connection connection;

    public MarketingCampaignDao(Connection connection) {
        this.connection = connection;
    }

    public void crateTable() {

        try {
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS marketing_campaign (" +
                    "id int primary key auto_increment," +
                    "name varchar(200)," + "start_date date," + "budget double);");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from marketing_campaign;");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                statement.execute("insert into marketing_campaign (name,start_date,budget) values " +
                        "('Name1','2022-12-18',1000),('Name2','2022-10-15',2000),('Name3','2021-10-20',500)");
                System.out.println("The table marketing_campaign was initialized!");

            } else {
                System.out.println("The tale was already initialized!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void create(MarketingCampaign marketingCampaign) {

        try {
            Statement statement = connection.createStatement();
            statement.execute("insert into marketing_campaign (name,start_date,budget) values " +
                    "('" + marketingCampaign.getName() + "','" + marketingCampaign.getStartDate() + "'," + marketingCampaign.getBudget() + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNew(MarketingCampaign marketingCampaign) {
        try {
            PreparedStatement createStatement = connection.prepareStatement("insert into marketing-campaign (name,start_date,budget) values (?,?,?);");
            createStatement.setString(1, marketingCampaign.getName());
            createStatement.setDate(2, marketingCampaign.getStartDate());
            createStatement.setDouble(3, marketingCampaign.getBudget());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //find all -  return a List/Set of MarketingCampaign

    public List<MarketingCampaign> findAll() {
        List<MarketingCampaign> returnList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from marketing_campaign;");
            while (resultSet.next()) {
                MarketingCampaign marketingCampaign = new MarketingCampaign(null, resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getDouble(4));
                returnList.add(marketingCampaign);
            }
            return returnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //search by name -> returneaza toate marketing campaign care includ numele dat de utilizator
    //searchByName("year") ->
    // folositi pt implementare PreparedStatement

    // search by date (before, after) -> sa returneaze toate MarketingCompaign care au startdate ul intre datele date de utilizator
    // searchBetween (2022-12-01,2022-12-31) -> returneaza toate marketingcomplaing intre datele alea
    // Cu PreparedStatement

    public List<MarketingCampaign> searchByName(String name) {
        List<MarketingCampaign> addList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from marketing_campaign where name like ?;");
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MarketingCampaign marketingCampaign = new MarketingCampaign(null, resultSet.getString(2), resultSet.getDate(3), resultSet.getDouble(4));
                addList.add(marketingCampaign);
            }
            return addList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MarketingCampaign> searchBetween(Date startDate, Date endDate) {
        List<MarketingCampaign> addDateList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from marketing_campaign where start_date >= ? and start_date <= ?;");
            //preparedstatemt, sa pun doar valoarea acolo, nu cu stringuri
           preparedStatement.setDate(1,startDate);
            preparedStatement.setDate(2,endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MarketingCampaign marketingCampaign = new MarketingCampaign(null, resultSet.getString(2), resultSet.getDate(3), resultSet.getDouble(4));
                addDateList.add(marketingCampaign);
            }
            return addDateList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
