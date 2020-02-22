package pl.coderslab.app.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "dataCoins")
public class DataCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double ask;
    private Double bid;
    private LocalDateTime created;


    @ManyToOne
    private ExchangeCoin exchangeCoin;


    @PrePersist
    public  void onCreated(){
        created = LocalDateTime.now();
    }


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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
        if (!(o instanceof DataCoin)) return false;
        DataCoin dataCoin = (DataCoin) o;
        return Objects.equals(getId(), dataCoin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
