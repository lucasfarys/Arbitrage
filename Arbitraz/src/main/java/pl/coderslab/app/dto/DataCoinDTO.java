package pl.coderslab.app.dto;

import java.util.Objects;

public class DataCoinDTO {
    private Long id;
    private Double ask;
    private Double bid;
    private Long exchangeCoin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Long getExchangeCoin() {
        return exchangeCoin;
    }

    public void setExchangeCoin(Long exchangeCoin) {
        this.exchangeCoin = exchangeCoin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataCoinDTO)) return false;
        DataCoinDTO that = (DataCoinDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
