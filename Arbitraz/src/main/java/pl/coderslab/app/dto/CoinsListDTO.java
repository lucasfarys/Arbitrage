package pl.coderslab.app.dto;

import jdk.nashorn.internal.objects.annotations.Getter;


public class CoinsListDTO {
    private String nameExOne;
    private String nameExSecond;
    private String coinName;
    private Double valueCoinOne;
    private Double valueCoinSecond;
    private Double profit;

    public String getNameExOne() {
        return nameExOne;
    }

    public String getNameExSecond() {
        return nameExSecond;
    }

    public String getCoinName() {
        return coinName;
    }

    public Double getValueCoinOne() {
        return valueCoinOne;
    }

    public Double getValueCoinSecond() {
        return valueCoinSecond;
    }

    public Double getProfit() {
        return profit;
    }

    public void setNameExOne(String nameExOne) {
        this.nameExOne = nameExOne;
    }

    public void setNameExSecond(String nameExSecond) {
        this.nameExSecond = nameExSecond;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public void setValueCoinOne(Double valueCoinOne) {
        this.valueCoinOne = valueCoinOne;
    }

    public void setValueCoinSecond(Double valueCoinSecond) {
        this.valueCoinSecond = valueCoinSecond;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "CoinsListDTO{" +
                "nameExOne='" + nameExOne + '\'' +
                ", nameExSecond='" + nameExSecond + '\'' +
                ", coinName='" + coinName + '\'' +
                ", valueCoinOne=" + valueCoinOne +
                ", valueCoinSecond=" + valueCoinSecond +
                ", profit=" + profit +
                '}';
    }
}
