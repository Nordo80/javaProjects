package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() >= 100) {
            bonusPoints += Integer.valueOf(Double.valueOf(order.getTotal() * 1.5).intValue());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints);
    }

    @Override
    public String asString() {

        return "GOLD Customer " + name + " with " + bonusPoints + " points";
    }

    @Override
    public LocalDate getLastOrderDate() {
        return null;
    }

}