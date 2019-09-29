package pl.coderslab.app.model.exchangeModel;

import javax.persistence.*;

@Entity
@Table(name = "bitbay")
public class Bitbay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double bidBTCPLN;
    private Double askBTCPLN;
    private Double bidETHBTC;
    private Double askETHBTC;
    private Double bidETHPLN;
    private Double askETHPLN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBidBTCPLN() {
        return bidBTCPLN;
    }

    public void setBidBTCPLN(Double bidBTCPLN) {
        this.bidBTCPLN = bidBTCPLN;
    }

    public Double getAskBTCPLN() {
        return askBTCPLN;
    }

    public void setAskBTCPLN(Double askBTCPLN) {
        this.askBTCPLN = askBTCPLN;
    }

    public Double getBidBTCETH() {
        return bidETHBTC;
    }

    public void setBidBTCETH(Double bidBTCETH) {
        this.bidETHBTC = bidBTCETH;
    }

    public Double getAskETHBTC() {
        return askETHBTC;
    }

    public void setAskETHBTC(Double askETHBTC) {
        this.askETHBTC = askETHBTC;
    }

    public Double getBidETHPLN() {
        return bidETHPLN;
    }

    public void setBidETHPLN(Double bidETHPLN) {
        this.bidETHPLN = bidETHPLN;
    }

    public Double getAskETHPLN() {
        return askETHPLN;
    }

    public void setAskETHPLN(Double askETHPLN) {
        this.askETHPLN = askETHPLN;
    }

    public Bitbay() {
    }


}