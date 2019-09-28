package pl.coderslab.app.model.exchangeModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "bitbay")
public class Bitbay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double bidBTCPLN;
    private Double askBTCPLN;
    private Double bidBTCETH;
    private Double askBTCETH;
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
        return bidBTCETH;
    }

    public void setBidBTCETH(Double bidBTCETH) {
        this.bidBTCETH = bidBTCETH;
    }

    public Double getAskBTCETH() {
        return askBTCETH;
    }

    public void setAskBTCETH(Double askBTCETH) {
        this.askBTCETH = askBTCETH;
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