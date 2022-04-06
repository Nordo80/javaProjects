package poly.customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {
    private LocalDate lastOrderDate;
    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;

    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if(order.getTotal() >= 100) {
            Period age = Period.between(lastOrderDate, order.getDate());
            int months = age.getMonths();
            if (months >= 1) {
                bonusPoints += order.getTotal();
            }else{
                bonusPoints += order.getTotal() * 1.5;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public String asString() {
        return "REGULAR Customer " + name + " with " + bonusPoints + " points";
    }

    @Override
    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }
}