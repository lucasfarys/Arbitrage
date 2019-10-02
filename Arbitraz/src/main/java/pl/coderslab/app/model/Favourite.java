package pl.coderslab.app.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favourite")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String exchange_first;
    private String exchange_second;
    private String coin;

    public Favourite() {
    }

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

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getExchange_first() {
        return exchange_first;
    }

    public void setExchange_first(String exchange_first) {
        this.exchange_first = exchange_first;
    }

    public String getExchange_second() {
        return exchange_second;
    }

    public void setExchange_second(String exchange_second) {
        this.exchange_second = exchange_second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favourite)) return false;
        Favourite favourite = (Favourite) o;
        return Objects.equals(getId(), favourite.getId()) &&
                Objects.equals(getLogin(), favourite.getLogin()) &&
                Objects.equals(getExchange_first(), favourite.getExchange_first()) &&
                Objects.equals(getExchange_second(), favourite.getExchange_second()) &&
                Objects.equals(getCoin(), favourite.getCoin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getExchange_first(), getExchange_second(), getCoin());
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", exchange_first='" + exchange_first + '\'' +
                ", exchange_second='" + exchange_second + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
