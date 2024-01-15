package objects;

import exceptions.UitleenException;
import util.Utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UitleenObject {
    public Boek boek;
    public Instant inleverDatum;
    private Integer aantalKerenVerlengt;

    public UitleenObject(final Boek boek, final Instant inleverDatum){
        this.boek = boek;
        this.inleverDatum = inleverDatum;
        this.aantalKerenVerlengt = 0;
    }

    public Integer getAantalKerenVerlengt() {
        return aantalKerenVerlengt;
    }

    private void setAantalKerenVerlengt(Integer aantalKerenVerlengt) throws UitleenException {
        if(this.aantalKerenVerlengt <= 2) {
            this.aantalKerenVerlengt = aantalKerenVerlengt;
        } else {
            throw new UitleenException("Er mag maar 2x verlengt worden.");
        }
    }

    public void verlengBoek() throws UitleenException {
        try{
            setAantalKerenVerlengt(++aantalKerenVerlengt);
            inleverDatum = Utils.addDate(inleverDatum,10);
        } catch (UitleenException ule){
            throw ule;
        }
    }
}
