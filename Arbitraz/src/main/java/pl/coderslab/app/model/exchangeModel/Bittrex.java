package pl.coderslab.app.model.exchangeModel;


import javax.persistence.*;

@Entity
@Table(name = "bittrex")
public class Bittrex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double bidETHBTC;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBidETHBTC() {
        return bidETHBTC;
    }

    public void setBidETHBTC(Double bidETHBTC) {
        this.bidETHBTC = bidETHBTC;
    }

    public Double getAskETHBTC() {
        return askETHBTC;
    }

    public void setAskETHBTC(Double askETHBTC) {
        this.askETHBTC = askETHBTC;
    }

    private Double askETHBTC;
}