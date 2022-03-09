package inheritance.analyser;

import java.util.List;

non-sealed class FlatTaxSalesAnalyser extends AbstractTaxSalesAnalyser {
    @Override
    protected double getTaxRate() {
        return 1.2;
    }

    protected FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

}
