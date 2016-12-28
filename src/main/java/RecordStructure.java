/**
 * Created by trot on 27.12.16.
 */
public class RecordStructure {

    public int count;
    public double avgValue;
    public Double minValue;
    public Double maxValue;

    public RecordStructure() {
        this.count = 0;
        this.avgValue = 0d;
        this.maxValue = null;
        this.minValue = null;
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