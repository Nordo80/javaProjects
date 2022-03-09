package inheritance.analyser;

import java.util.List;

non-sealed class TaxFreeSalesAnalyser extends AbstractTaxSalesAnalyser {

    protected TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }
    @Override
    protected double getTaxRate() {
        return 1.0;
    }


}
