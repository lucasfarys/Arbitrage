package pl.coderslab.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exchanges")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    String addressUrlPrefix;
    @NotBlank
    String addressUrlSuffix;

    @OneToMany(mappedBy = "exchange")
    private List<ExchangeCoin> exchangeCoins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressUrlPrefix() {
        return addressUrlPrefix;
    }

    public void setAddressUrlPrefix(String addressUrlPrefix) {
        this.addressUrlPrefix = addressUrlPrefix;
    }

    public String getAddressUrlSuffix() {
        return addressUrlSuffix;
    }

    public void setAddressUrlSuffix(String addressUrlSuffix) {
        this.addressUrlSuffix = addressUrlSuffix;
    }

    public List<ExchangeCoin> getExchangeCoins() {
        return exchangeCoins;
    }

    public void setExchangeCoins(List<ExchangeCoin> exchangeCoins) {
        this.exchangeCoins = exchangeCoins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exchange)) return false;
        Exchange exchange = (Exchange) o;
        return Objects.equals(getId(), exchange.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}