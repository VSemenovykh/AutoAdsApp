package ru.ncedu.service;

import java.util.List;
import java.util.Map;

public interface LoadBaseDataService {

    Map<String, List> createTables();

    void upload();
}
