/**
 * Created by trot on 27.12.16.
 */
public class RecordStruct {

    public int count;
    public Double avgValue;
    public Double minValue;
    public Double maxValue;

    public RecordStruct(int count, Double avgValue, Double minValue, Double maxValue) {
        this.count = count;
        this.avgValue = avgValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public RecordStruct() {
        this.count = 0;
        this.avgValue = 0d;
        this.maxValue = 0d;
        this.minValue = 0d;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAvgValue(Double avgValue) {
        this.avgValue = avgValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public int getCount() {
        return count;
    }

    public Double getAvgValue() {
        return avgValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

}




/*
Group, Size, Avg. Value, Min. Value, Max. Value
 */