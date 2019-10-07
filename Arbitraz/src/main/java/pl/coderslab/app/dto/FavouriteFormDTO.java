package pl.coderslab.app.dto;

import pl.coderslab.app.model.Coin;
import pl.coderslab.app.model.Exchange;

import java.util.Objects;

public class FavouriteFormDTO {
    private Long id;
    private String login;
    private Exchange exchangeFirst;
    private Exchange ExchangeSecond;
    private Coin coin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Exchange getExchangeFirst() {
        return exchangeFirst;
    }

    public void setExchangeFirst(Exchange exchangeFirst) {
        this.exchangeFirst = exchangeFirst;
    }

    public Exchange getExchangeSecond() {
        return ExchangeSecond;
    }

    public void setExchangeSecond(Exchange exchangeSecond) {
        ExchangeSecond = exchangeSecond;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavouriteFormDTO)) return false;
        FavouriteFormDTO that = (FavouriteFormDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
