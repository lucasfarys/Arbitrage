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
    private Long exchangeFirstId;
    private Long exchangeSecondId;

    @ManyToOne
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


    public Long getExchangeFirstId() {
        return exchangeFirstId;
    }

    public void setExchangeFirstId(Long exchangeFirstId) {
        this.exchangeFirstId = exchangeFirstId;
    }

    public Long getExchangeSecondId() {
        return exchangeSecondId;
    }

    public void setExchangeSecondId(Long exchangeSecondId) {
        this.exchangeSecondId = exchangeSecondId;
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
        if (!(o instanceof Favourite)) return false;
        Favourite favourite = (Favourite) o;
        return Objects.equals(getId(), favourite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
