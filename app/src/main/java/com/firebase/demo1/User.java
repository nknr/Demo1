package com.firebase.demo1;

import com.google.firebase.firestore.PropertyName;

public class User {

    @PropertyName("first")
    private String first;
    @PropertyName("last")
    private String last;
    private long born;

    public User() {
    }

    public User(String first, String last, long born) {
        this.first = first;
        this.last = last;
        this.born = born;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public long getBorn() {
        return born;
    }

    public void setBorn(long born) {
        this.born = born;
    }
}
