package pl.coderslab.app.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favourites")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;

    @ManyToOne
    private Coin coin;
    @ManyToOne
    private Exchange exchangeFirst;
    @ManyToOne
    private Exchange exchangeSecond;

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

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Exchange getExchangeFirst() {
        return exchangeFirst;
    }

    public void setExchangeFirst(Exchange exchangeFirst) {
        this.exchangeFirst = exchangeFirst;
    }

    public Exchange getExchangeSecond() {
        return exchangeSecond;
    }

    public void setExchangeSecond(Exchange exchangeSecond) {
        this.exchangeSecond = exchangeSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favourite)) return false;
        Favourite favourite = (Favourite) o;
        return Objects.equals(getId(), favourite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
