package pl.coderslab.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exchange_coins")
public class ExchangeCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String uniqueName;

    @ManyToOne
    private Exchange exchange;

    @ManyToOne
    private Coin coin;

    @OneToMany(mappedBy = "exchangeCoin")
    private List<DataCoin> dataCoins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public List<DataCoin> getDataCoins() {
        return dataCoins;
    }

    public void setDataCoins(List<DataCoin> dataCoins) {
        this.dataCoins = dataCoins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExchangeCoin)) return false;
        ExchangeCoin that = (ExchangeCoin) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}