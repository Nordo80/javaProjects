package inheritance.analyser;

import java.util.List;

abstract sealed class AbstractTaxSalesAnalyser permits DifferentiatedTaxSalesAnalyser, FlatTaxSalesAnalyser, TaxFreeSalesAnalyser {
    public static final Double TAX_RATE = 1.2;
    public List<SalesRecord> records;

    protected AbstractTaxSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    protected Double getTotalSales() {
        Double totalSales = 0.0;
        for (SalesRecord i : records) {
            totalSales += i.getItemsSold() * i.getProductPrice();

        }
        return totalSales / getTaxRate();
    }

    public Double getTotalSalesByProductId(String id) {
        Double salesByProduct = 0.0;
        for (SalesRecord i : records) {
            if (id == i.getProductId()) {
                salesByProduct += i.getItemsSold() * i.getProductPrice();
            }
        }
        return salesByProduct / getTaxRate();
    }

    protected Double getTaxRate() {
        return TAX_RATE;
    }

    public String getIdOfMostPopularItem() {
        String popularItem = "";
        int counter = 0;
        for (SalesRecord i : records) {
            if (counter < i.getItemsSold()) {
                popularItem = i.getProductId();
                counter = i.getItemsSold();
            }
        }
        return popularItem;
    }

    public String getIdOfItemWithLargestTotalSales() {
        String largestSales = "";
        int counter = 0;
        for (SalesRecord i : records) {
            if (counter < i.getItemsSold() * i.getProductPrice()) {
                largestSales = i.getProductId();
                counter = i.getItemsSold() * i.getProductPrice();

            }
        }
        return largestSales;
    }
}
