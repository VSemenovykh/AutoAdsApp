package ru.ncedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoadBaseDataService {

    Map<String, List> createTables() throws IOException;

    void uploadBaseData() throws IOException;
}
