package ru.ncedu.services;

import ru.ncedu.entity.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UploadBaseDataService {

    Map<String, List> createTables() throws IOException;

    void uploadBaseData() throws IOException;

    List<User> getUserList();

    List<Role> getRoleList();

    List<UserRole> getUserRoleList();

    List<Brand> getBrandList();

    List<Motor> getMotorList();

    List<PictureAuto> getPictureAuto();

    List<Auto> getAutoList();

    List<Contact> getContactList();
}
