package edu.elpeanuto.tms.model;

/**
 * Discount model class, contains information about discount
 */
public class Discount extends Entity<Discount>{
    Long id;
    Integer step;
    Integer max;

    public Discount(Long id, Integer step, Integer max) {
        this.id = id;
        this.step = step;
        this.max = max;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", step=" + step +
                ", max=" + max +
                '}';
    }

    @Override
    public int compareTo(Discount o) {
        return 0;
    }
}
