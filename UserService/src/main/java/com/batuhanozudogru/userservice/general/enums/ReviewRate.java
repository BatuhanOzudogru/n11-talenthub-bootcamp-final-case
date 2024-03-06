package com.batuhanozudogru.userservice.general.enums;

public enum ReviewRate {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int rate;

    ReviewRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
