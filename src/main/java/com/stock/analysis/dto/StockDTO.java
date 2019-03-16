package com.stock.analysis.dto;

import java.util.HashMap;

/**
 * Created by rsampathkumar on 3/15/2019.
 */
public class StockDTO {

    private int lowestStockPriceBought;

    private int highestStockPriceSold;

    private int maxProfitForTheDay;

    private HashMap<Integer,Integer> lowestStockPriceMap;

    private HashMap<Integer,Integer> highestStockPriceMap;

    public HashMap<Integer, Integer> getLowestStockPriceMap() {
        return lowestStockPriceMap;
    }

    public void setLowestStockPriceMap(HashMap<Integer, Integer> lowestStockPriceMap) {
        this.lowestStockPriceMap = lowestStockPriceMap;
    }

    public HashMap<Integer, Integer> getHighestStockPriceMap() {
        return highestStockPriceMap;
    }

    public void setHighestStockPriceMap(HashMap<Integer, Integer> highestStockPriceMap) {
        this.highestStockPriceMap = highestStockPriceMap;
    }

    public int getLowestStockPriceBought() {
        return lowestStockPriceBought;
    }

    public void setLowestStockPriceBought(int lowestStockPriceBought) {
        this.lowestStockPriceBought = lowestStockPriceBought;
    }

    public int getHighestStockPriceSold() {
        return highestStockPriceSold;
    }

    public void setHighestStockPriceSold(int highestStockPriceSold) {
        this.highestStockPriceSold = highestStockPriceSold;
    }

    public int getMaxProfitForTheDay() {
        return maxProfitForTheDay;
    }

    public void setMaxProfitForTheDay(int maxProfitForTheDay) {
        this.maxProfitForTheDay = maxProfitForTheDay;
    }
}
