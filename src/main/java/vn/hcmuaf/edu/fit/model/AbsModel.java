package vn.hcmuaf.edu.fit.model;

public abstract class AbsModel implements IModel{
    public abstract String getBeforeData();
    public abstract  String getAfterData();

    public String infoLevel(){
        return "info";
    }

    public String alertLevel(){
        return "alert";
    }

    public String dangerLevel(){
        return "danger";
    }


}
