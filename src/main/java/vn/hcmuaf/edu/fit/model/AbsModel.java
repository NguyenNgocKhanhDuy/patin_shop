package vn.hcmuaf.edu.fit.model;

public abstract class AbsModel implements IModel{
    private String beforeData;
    private String afterData;

    public String getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }

    public String getAfterData() {
        return afterData;
    }

    public void setAfterData(String afterData) {
        this.afterData = afterData;
    }
}
