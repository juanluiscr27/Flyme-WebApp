package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable {
    private long id;
    private String cardNumber;
    private String nameOnCard;
    private LocalDate expiryDate;
    private int securityCode;

    public Payment(String cardNumber, String nameOnCard, LocalDate expiryDate, int securityCode) {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }
    public Payment(long paymentId, String cardNumber, String nameOnCard, LocalDate expiryDate, int securityCode) {
        this.id = paymentId;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
    }
    public Payment(Payment payment) {
        this.id = payment.getId();
        this.cardNumber = payment.getCardNumber();
        this.nameOnCard = payment.getNameOnCard();
        this.expiryDate = payment.getExpiryDate();
        this.securityCode = payment.getSecurityCode();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "Payment {" +
                "cardNumber='" + cardNumber + '\'' +
                ", nameOnCard='" + nameOnCard + '\'' +
                ", expiryDate=" + expiryDate +
                ", securityCode=" + securityCode +
                '}';
    }
}
