package com.beiwu.ch5.bq;

/**
 * @author zhoubing
 * @date 2021-01-04 22:33
 */
public class Ticket {
    private String tickId;
    private int id;

    public Ticket() {
    }

    public Ticket(String tickId, int id) {
        this.tickId = tickId;
        this.id = id;
    }

    public String getTickId() {
        return tickId;
    }

    public void setTickId(String tickId) {
        this.tickId = tickId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tickId='" + tickId + '\'' +
                ", id=" + id +
                '}';
    }
}
