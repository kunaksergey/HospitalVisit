package ua.shield.enum_;

/**
 * Created by sa on 30.11.17.
 */
public enum StatusTicket {
    NEW(1),PROCESED(2),DONE(3),CANCEL(4);
    private int value;

    StatusTicket(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
