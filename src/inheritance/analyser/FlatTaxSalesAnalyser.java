package inheritance.analyser;

import java.util.List;

non-sealed class FlatTaxSalesAnalyser extends AbstractTaxSalesAnalyser {

    protected FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

}
