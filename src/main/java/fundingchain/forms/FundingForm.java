package fundingchain.forms;

import javax.validation.constraints.NotNull;

public class FundingForm {

    @NotNull
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
