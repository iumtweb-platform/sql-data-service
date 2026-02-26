package com.sqldataservice.api.feature.appuser.detail;

import java.sql.Date;

public record DetailAppUserResponse(String username, String gender, String country, Date birthday, Date joinedDate) {

}
