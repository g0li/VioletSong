package forevtechnologies.alegriauiux.models;

/**
 * Created by goli on 29/11/17.
 */

public enum EntryFees {
    pachas(50),//13events
    sau(100),//26events
    dedsau(150),//16events
    dosau(200),//12events
    dhaisau(250),//4events
    teensau(300),//8events
    teensaupachas(350),//1events csgo
    paachsau(500),//2 events group folk, volleyball
    chesau(600),//1 events kabadi
    hajar(1000),//3 events freestyle futsal box cricket
    dojarpaachsau(2500),//1 event fashionshow
    fukat(0);//1 event hackathon

    public int getEntryFee() {
        return EntryFee;
    }

    private final int EntryFee;

    EntryFees(int i) {

        EntryFee = i;
    }
}
