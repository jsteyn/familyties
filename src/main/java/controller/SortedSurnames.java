package controller;

import java.util.Comparator;

//https://www.geeksforgeeks.org/collections-sort-java-examples/
public class SortedSurnames implements Comparator<SurnameListEntry> {
    @Override
    public int compare(SurnameListEntry o1, SurnameListEntry o2) {
        return o1.getCount() - o2.getCount();
    }
}
