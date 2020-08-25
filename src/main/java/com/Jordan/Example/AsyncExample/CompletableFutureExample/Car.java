package com.Jordan.Example.AsyncExample.CompletableFutureExample;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Car {
    int id;
    int manufacturerId;
    String model;
    int year;
    float rating;

    void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Car (id=" + id + ", manufacturerId=" + manufacturerId + ", model=" + model + ", year=" + year
                + ", rating=" + rating;
    }
}