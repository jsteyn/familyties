package controller;

public class SurnameListEntry {

    private String surname;
    private int count;

    public SurnameListEntry() {

    }

    public SurnameListEntry(String surname, int count) {
        this.surname = surname;
        this.count = count;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
