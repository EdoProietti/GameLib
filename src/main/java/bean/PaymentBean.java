package bean;

public class PaymentBean {
    private String nameSurname;
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public PaymentBean() {
        // utilizziamo i setter per controllare il formato dei dati
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public void setCardNumber(String cardNumber) {
        if(!cardNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid card number");
        }
        this.cardNumber = cardNumber;
    }

    public void setExpirationDate(String expirationDate) throws IllegalArgumentException {
        if(expirationDate.length() != 7) {
            throw new IllegalArgumentException("Invalid expiration date");
        }
        this.expirationDate = expirationDate;
    }

    public void setCvv(String cvv) throws IllegalArgumentException {
        if(cvv.length() != 3){
            throw new IllegalArgumentException("Invalid cvv");
        }
    }
}
