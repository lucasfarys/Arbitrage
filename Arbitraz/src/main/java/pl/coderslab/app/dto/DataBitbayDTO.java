package pl.coderslab.app.dto;

import java.util.Objects;

public class DataBitbayDTO {
    private Double max;
    private Double min;
    private Double last;
    private Double bid;
    private Double ask;
    private Double vwap;
    private Double average;
    private Double volume;

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getVwap() {
        return vwap;
    }

    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataBitbayDTO)) return false;
        DataBitbayDTO that = (DataBitbayDTO) o;
        return getMax().equals(that.getMax()) &&
                getMin().equals(that.getMin()) &&
                getLast().equals(that.getLast()) &&
                getBid().equals(that.getBid()) &&
                getAsk().equals(that.getAsk()) &&
                getVwap().equals(that.getVwap()) &&
                getAverage().equals(that.getAverage()) &&
                getVolume().equals(that.getVolume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMax(), getMin(), getLast(), getBid(), getAsk(), getVwap(), getAverage(), getVolume());
    }

    @Override
    public String toString() {
        return "DataBitbayDTO{" +
                "max=" + max +
                ", min=" + min +
                ", last=" + last +
                ", bid=" + bid +
                ", ask=" + ask +
                ", vwap=" + vwap +
                ", average=" + average +
                ", volume=" + volume +
                '}';
    }
}