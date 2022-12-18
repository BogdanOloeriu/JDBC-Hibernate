package org.example.dao;

import org.example.DataBaseConnection;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        MarketingCampaign marketingCampaign = new MarketingCampaign(null,"Name", Date.valueOf("2022-12-22"),5000.0);
        MarketingCampaignDao marketingCampaignDao = new MarketingCampaignDao(DataBaseConnection.getConnection());

        marketingCampaignDao.crateTable();
        marketingCampaignDao.initialize();
        marketingCampaignDao.create(marketingCampaign);

        MarketingCampaign marketingCampaign1 = new MarketingCampaign(null,"Name2",Date.valueOf("2022-10-09"),4000.0);
        marketingCampaignDao.create(marketingCampaign1);

        System.out.println(marketingCampaignDao.findAll());

        System.out.println(marketingCampaignDao.searchByName("Name"));

        System.out.println(marketingCampaignDao.searchBetween(Date.valueOf("2022-01-01"),Date.valueOf("2022-12-28")));


    }
}
