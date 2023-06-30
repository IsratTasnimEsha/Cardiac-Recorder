package com.example.gitproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordList {
    ArrayList<UserClass>arrayList = new ArrayList<>();

    public ArrayList<UserClass> getRecords() {
        ArrayList<UserClass> recordList = arrayList;
        return recordList;
    }

    public void add(UserClass userClass) {
        if(arrayList.contains(userClass))
        {
            throw new IllegalArgumentException();
        }
        arrayList.add(userClass);
    }

    public void delete(UserClass userClass)
    {
        if(!arrayList.contains(userClass))
        {
            throw new IllegalArgumentException();
        }
        arrayList.remove(userClass);
    }

    public void update(int position, UserClass userClass)
    {
        arrayList.set(position, userClass);
    }
}
