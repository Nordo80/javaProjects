package inheritance.analyser;

import java.util.List;

non-sealed class DifferentiatedTaxSalesAnalyser extends AbstractTaxSalesAnalyser {

    protected DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }
    @Override
    protected double getTaxRate() {
        return 1.2;
    }
    @Override
    protected Double getTotalSales() {
        Double totalSales = 0.0;
        for (SalesRecord i : records) {
            if(i.hasReducedRate()){
                totalSales += (i.getItemsSold() * i.getProductPrice()) / 1.1;
            }else{
                totalSales += (i.getItemsSold() * i.getProductPrice()) / getTaxRate();
            }
        }
        return totalSales;
    }
}
