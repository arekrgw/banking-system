package com.bank.sys.utils;

public class Pair<T, E> {
    private T first;
    private E second;

    public Pair() {

    }

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }
}
