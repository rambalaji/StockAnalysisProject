package com.stock.analysis;

import com.stock.analysis.dto.StockDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by rsampathkumar on 3/15/2019.
 */

public class StockPricingTest {

    @Test
    public void getLowestPriceTest() throws Exception {
        int[] stocks = {21,22,26,30,47,120,8,76,110};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);
        assertTrue("Result must be 8",result.getLowestStockPriceBought()==8);
        assertEquals(110,result.getHighestStockPriceSold());
        assertEquals(102,result.getMaxProfitForTheDay());

    }

    @Test
    public void getLowestPriceBigNumbersTest() throws Exception {
        int[] stocks = {145,140,150,151,149,146,145,138,134,130,128,110,111,109,120,115,120,121,130};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);

        assertEquals(130,result.getHighestStockPriceSold());
        assertEquals(21,result.getMaxProfitForTheDay());

    }

    @Test
    public void getBestMaxPricesTest() throws Exception {
        int[] stocks = {2,10,1,20,16};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);
        assertTrue("Result must be 2",result.getLowestStockPriceBought()==2);
        assertTrue("Result must be 18",result.getMaxProfitForTheDay()==18);
        assertTrue("Result must be 20",result.getHighestStockPriceSold()==20);

    }


    /*
     *
     **/
    @Test
    public void getLowestPriceAsLastTest() throws Exception {
        int[] stocks = {21,22,26,30,47,120,8,76,110,7};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);
        assertTrue("Result must be 8",result.getLowestStockPriceBought()==8);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getExceptionWithEmptyArray() throws Exception {
        int[] stocks = {};

        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);

    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getExceptionWithOneElementArray() throws Exception {
        int[] stocks = {1,2};
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("At least 2 items should exist in the array");

        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);

    }

    @Test
    public void getExceptionWithNegativeStockValuesTest() throws Exception {
        int[] stocks = {-2,-10,-1,-20,-16};

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Array should contain only positive values");

        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);

    }
    @Test
    public void getHighestPriceAsFirstTest() throws Exception {
        int[] stocks = {130,21,22,26,30,47,100,8,76,110};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);
        assertTrue("Result must be 110",result.getHighestStockPriceSold()==110);

    }

    @Test
    public void getHighestPriceTestAfterBuy() throws Exception {
        int[] stocks = {130,21,22,26,30,47,100,8,76,110};
        StockPricing st = new StockPricing();
        StockDTO result = st.getMaxProfit(stocks);
        assertTrue("Result must be 110",result.getHighestStockPriceSold()==110);

    }

    @Test
    public void getHighestPriceTestAfterImmediateBuy() throws Exception {
        int[] stocks = {21,140,22,26,30,47,100,8,130,76,110};
        StockPricing st = new StockPricing();
        StockDTO result= st.getMaxProfit(stocks);
        assertTrue("Result must be 110",result.getHighestStockPriceSold()==130);
        assertTrue("Result must be 8",result.getLowestStockPriceBought()==21);
        assertTrue("Result must be 8",result.getMaxProfitForTheDay()==109);

    }

    @Test
    public void getHighestPriceTestAfterAMinuteBuy() throws Exception {
        int[] stocks = {21,140,22,26,30,47,100,8,76,130,9,110};
        StockPricing st = new StockPricing();
        StockDTO result= st.getMaxProfit(stocks);

        assertTrue("Result must be 130",result.getHighestStockPriceSold()==130);
        //assertTrue("Result must be 130",result.getHighestStockPriceSold()==130);
        assertTrue("Result must be 8",result.getLowestStockPriceBought()==8);

    }


    @Test
    public void getMaxProfitValueTest() throws Exception {
        int[] stocks = {10,7,5,8,11,9};
        StockPricing st = new StockPricing();
        StockDTO result= st.getMaxProfit(stocks);

        assertEquals(11,result.getHighestStockPriceSold());
        assertEquals(5,result.getLowestStockPriceBought());
        assertEquals(6,result.getMaxProfitForTheDay());

    }



}
