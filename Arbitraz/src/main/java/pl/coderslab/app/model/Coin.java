package pl.coderslab.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coins")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String coinName;

    @OneToMany(mappedBy = "coin")
    private List<ExchangeCoin> exchangeCoins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
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
        if (!(o instanceof Coin)) return false;
        Coin coin = (Coin) o;
        return Objects.equals(getId(), coin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
