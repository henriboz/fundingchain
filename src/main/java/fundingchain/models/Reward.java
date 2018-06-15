package fundingchain.models;

import javax.persistence.*;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double lowervalue;

    @Column(nullable = false)
    private double midvalue;

    @Column(nullable = false)
    private double uppervalue;

    @Column(nullable = false)
    private String lowerDesc;

    @Column(nullable = false)
    private String midDesc;

    @Column(nullable = false)
    private String upperDesc;

    public String getLowerDesc() {
        return lowerDesc;
    }

    public void setLowerDesc(String lowerDesc) {
        this.lowerDesc = lowerDesc;
    }

    public String getMidDesc() {
        return midDesc;
    }

    public void setMidDesc(String midDesc) {
        this.midDesc = midDesc;
    }

    public String getUpperDesc() {
        return upperDesc;
    }

    public void setUpperDesc(String upperDesc) {
        this.upperDesc = upperDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLowervalue() {
        return lowervalue;
    }

    public void setLowervalue(double lowervalue) {
        this.lowervalue = lowervalue;
    }

    public double getMidvalue() {
        return midvalue;
    }

    public void setMidvalue(double midvalue) {
        this.midvalue = midvalue;
    }

    public double getUppervalue() {
        return uppervalue;
    }

    public void setUppervalue(double uppervalue) {
        this.uppervalue = uppervalue;
    }

    public Reward(){}
}
