package exceptions.price;

public class PriceCalculator {

    public double calculatePrice() {
        try{
            return calculateBasePrice() * (1 + getVat());

        } catch(MissingConstantException e){
            return -1D;
        }
    }

    private double getVat() {
        return 0.2;
    }

    private double calculateBasePrice() {
        // some complex calculation that produces 100 as net cost
        @SuppressWarnings("PMD.PrematureDeclaration")
        double netCost = 100D;

        int profitConstant = readProfitConstant();

        if (profitConstant == -1) {
            return -1D;
        }

        return netCost + (0.1 * profitConstant * netCost);
    }

    private int readProfitConstant() {
        int constant;
        try{
            constant = MyFileResource.read();
        }catch (Exception e){
            throw new MissingConstantException();
        }
        if (constant == -1){
            throw new MissingConstantException();
        }
        return constant;
    }

}
