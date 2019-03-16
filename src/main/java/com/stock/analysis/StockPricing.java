package com.stock.analysis;

import com.stock.analysis.dto.StockDTO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * Class to find out best and maximum profit for the day of the stocks.
 * Created by rsampathkumar on 3/15/2019.
 */
public class StockPricing {


    /**
     * This method is to validate the input array of stock prices.
     *
     * @param stocks
     * @return boolean
     */
    public boolean validate(int[] stocks) throws Exception {
        if (stocks.length > 2) {

            for (int stock : stocks) {
                if (stock < 0) {
                    throw new IllegalArgumentException("Array should contain only positive values");
                }
            }
            return true;
        } else {
            throw new IllegalArgumentException("At least 2 items should exist in the array");
        }

    }

    /**
     * This method <code>getMaxProfit</code> will take an array of int as an input and identify the lowest possible
     * prices and highest possible prices as an <code>HashMap</code> based on the following conditions:
     * First buy before you sell
     * Buy and sell in the same time step (at least 1 minute must pass).
     * <p>
     * Once we got the Map of lowest prices and highest prices (which will look like as below:
     * Note : Map will have the index (time) as key for each values/prices.
     * eg: int[] stocks ={2,10,1,20}
     * Lowest Map :{0=2, 2=1,}
     * Highest Map :  { 1=10, 3=20}
     *
     * Then loop the lowest map price and for each lowest price loop the highest price and do the
     * following:
     * 1. Find the difference(Max profit) and store the values in <code>StockDTO</code> Make sure the
     * 2. Create a Tree map<code>This will sort in descending order with highest profit in tope</code>,
     *    and store the difference value(max profit) as key and StockDTO as object
     *    eg : TreeMap = {18 : StockDTO@4090435, -1=StockDTO@7ng43gg}
     * 3. Since the tree map is sorted by desc, get the first element which should be the MAX PROFIT.
     *
     * @param stocks
     * @return StockDTO
     * @throws Exception
     */
    public StockDTO getMaxProfit(int[] stocks) throws Exception {

        try {
            //Validate the input stocks
            validate(stocks);

            //first to get the list of loweset prices and highest valid prices
            final StockDTO stockDTO = getLowestAndHighestPriceMap(stocks);

            //This is a treeMap to sort the StockDTO based on the best price on top.
            TreeMap<Integer, StockDTO> maxProfitMap = new TreeMap<Integer, StockDTO>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });


            //Iterate the LowestPricesMap first to get the keys
            //No need to check for null, as in the method getLowestAndHighestPriceMap the map are created.
            stockDTO.getLowestStockPriceMap().keySet().forEach(lowestPriceIndex ->
                    {
                        stockDTO.getHighestStockPriceMap().keySet().forEach(highestPriceIndex ->
                        {
                            //Make sure the lowestPriceKey is less than highest and
                            //highestPriceKey one is not followed not immd. after lowest.
                            //eg: 8,130 is not a valid buy and sell.
                            //WE have to retreive the lowest and highest index only if they both are not same
                            // and atleast it should have positive difference > 1.
                            if (lowestPriceIndex < highestPriceIndex && lowestPriceIndex + 1 != highestPriceIndex) {
                                //get the smallest value from the map and highest value from the map
                                int maxProfit = stockDTO.getHighestStockPriceMap().get(highestPriceIndex)
                                        - stockDTO.getLowestStockPriceMap().get(lowestPriceIndex);

                                StockDTO newStockDTO = new StockDTO();
                                newStockDTO.setLowestStockPriceBought(stockDTO.getLowestStockPriceMap().get(lowestPriceIndex));
                                newStockDTO.setHighestStockPriceSold(stockDTO.getHighestStockPriceMap().get(highestPriceIndex));
                                newStockDTO.setMaxProfitForTheDay(maxProfit);
                                //create a map with max profit as the key.
                                maxProfitMap.put(maxProfit, newStockDTO);
                            }

                        });

                    }
            );


            System.out.println("Final Map " + maxProfitMap);
            //make sure maps are not null.
            if (!maxProfitMap.isEmpty()) {
                StockDTO finalStockDTO = maxProfitMap.firstEntry().getValue();
                return finalStockDTO;
            }

            return stockDTO;

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * This method is get the list of lowest prices and list of highest prices not close,
     * but in sequential order.
     *
     * @param stocks
     * @return StockDTO
     * @throws Exception
     */
    public StockDTO getLowestAndHighestPriceMap(int[] stocks) throws Exception
    {
        //Validate the input stocks
        validate(stocks);
        HashMap<Integer, StockDTO> priceMap = new HashMap<Integer, StockDTO>();
        int ctr = 0, idx = 0, lowestStockBought = 0, highestStockSold = 0;
        boolean buy = false, purchase = false;
        StockDTO stockDTO = new StockDTO();

        HashMap lowestStockMap = new HashMap<Integer, Integer>();
        HashMap highestStockMap = new HashMap<Integer, Integer>();


        //loop through all the stock prices
        for (int price : stocks) {

            //The first index (0) will be assigned as lowest price as default.
            if (idx == 0) {
                lowestStockBought = price;
                ctr = idx;
                buy = true;
                lowestStockMap.put(idx, price);

            }//check the stock price is less the assigned ,and also make sure it is not the last one !.
            //eg :{4,15,13,2) in this case 2 is not valid to store as we dont have any option to sell.
            else if (price < lowestStockBought && ctr + 1 != stocks.length) {
                lowestStockBought = price;
                ctr = idx;
                System.out.println("Ctr " + ctr);
                buy = true;
                highestStockSold = 0;
                lowestStockMap.put(idx, price);

            }

            //By default highestStockSold is set to zero, and first price will be assigned as highest if buy is true.
            //The condition is you should sell only after you buy a stock with lowest possible price.
            if (price > highestStockSold && buy) {
                //System.out.println("Highest Prices "+price+"idx >= ctr+1 "+idx+" >= "+(ctr+1));
                highestStockSold = price;
                purchase = true;
                highestStockMap.put(idx, price);

            }
            //incremenet the index
            idx++;
        }

        //Always will have a map
        stockDTO.setHighestStockPriceMap(highestStockMap);
        stockDTO.setLowestStockPriceMap(lowestStockMap);
        System.out.println("Smallest number Map" + lowestStockMap + "  and Highest Map   " + highestStockMap);
        return stockDTO;
    }

    public static void main(String[] args) {
        StockPricing st = new StockPricing();
        // int[] stocks = {21,140,22,26,30,47,100,8,76,130,9,110};
        int[] stocks = {2, 10, 1, 20, 16};

        try {
            StockDTO stockDTO = st.getMaxProfit(stocks);
            System.out.println("Max Profit" + stockDTO.getMaxProfitForTheDay());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
