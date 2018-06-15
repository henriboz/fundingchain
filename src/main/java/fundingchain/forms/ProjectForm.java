package fundingchain.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ProjectForm {

    @NotNull
    @Size(min=1, max=50)
    private String title;

    @NotNull
    @Size(min=1, max=100)
    private String description;

    @NotNull
    @Size(min=1, max=2000)
    private String about;

    @NotNull
    private String dueDate;

    @NotNull
    private double targetValue;

    @NotNull
    private double lowerReward;

    @NotNull
    private String lowerRewardDesc;

    @NotNull
    private double midReward;

    @NotNull
    private String midRewardDesc;

    @NotNull
    private double upperReward;

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public String getLowerRewardDesc() {
        return lowerRewardDesc;
    }

    public void setLowerRewardDesc(String lowerRewardDesc) {
        this.lowerRewardDesc = lowerRewardDesc;
    }

    public String getMidRewardDesc() {
        return midRewardDesc;
    }

    public void setMidRewardDesc(String midRewardDesc) {
        this.midRewardDesc = midRewardDesc;
    }

    public String getUpperRewardDesc() {
        return upperRewardDesc;
    }

    public void setUpperRewardDesc(String upperRewardDesc) {
        this.upperRewardDesc = upperRewardDesc;
    }

    @NotNull
    private String upperRewardDesc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getLowerReward() {
        return lowerReward;
    }

    public void setLowerReward(double lowerReward) {
        this.lowerReward = lowerReward;
    }

    public double getMidReward() {
        return midReward;
    }

    public void setMidReward(double midReward) {
        this.midReward = midReward;
    }

    public double getUpperReward() {
        return upperReward;
    }

    public void setUpperReward(double upperReward) {
        this.upperReward = upperReward;
    }
}
