package pl.coderslab.app.dto;

import java.util.Objects;

public class FavouriteFormDTO {
    private Long id;
    private String login;
    private String exchange_first;
    private String exchange_second;
    private String coin;

    public FavouriteFormDTO() {
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
        if (!(o instanceof FavouriteFormDTO)) return false;
        FavouriteFormDTO that = (FavouriteFormDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getExchange_first(), that.getExchange_first()) &&
                Objects.equals(getExchange_second(), that.getExchange_second()) &&
                Objects.equals(getCoin(), that.getCoin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getExchange_first(), getExchange_second(), getCoin());
    }

    @Override
    public String toString() {
        return "FavouriteFormDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", exchange_first='" + exchange_first + '\'' +
                ", exchange_second='" + exchange_second + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
