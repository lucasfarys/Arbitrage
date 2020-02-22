package pl.coderslab.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.coderslab.app.model.ExchangeCoin;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCoinDTO {
    private Long id;
    @JsonAlias({"ask","result:Ask"})
    private Double ask;
    private Double bid;
    private ExchangeCoin exchangeCoin;

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

    public ExchangeCoin getExchangeCoin() {
        return exchangeCoin;
    }

    public void setExchangeCoin(ExchangeCoin exchangeCoin) {
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
