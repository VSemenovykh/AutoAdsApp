package ru.ncedu.services;

import ru.ncedu.entity.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UploadBaseDataService {

    Map<String, List> createTables() throws IOException, ParseException;

    void uploadBaseData() throws IOException, ParseException;

    List<User> getUserList();

    List<Role> getRoleList();

    List<UserRole> getUserRoleList();

    List<Brand> getBrandList();

    List<Motor> getMotorList();

    List<PictureAuto> getPictureAuto();

    List<Auto> getAutoList() throws ParseException;

    List<Contact> getContactList();
}
