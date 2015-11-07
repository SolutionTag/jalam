package com.meshyog.dao;

import java.util.List;

public interface DBObserver{
    public List<String> notifyNewRecordInserted(String message);
   
}