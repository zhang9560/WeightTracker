package app.weight.tracker;

public class Weight {
    public Weight() {

    }

    public Weight(long dateInMilliseconds, float weight) {
        this.dateInMilliseconds = dateInMilliseconds;
        this.weight = weight;
    }

    public long dateInMilliseconds;
    public float weight;
}
