package com.batuhanozudogru.userservice.dto.request;


import java.time.LocalDate;

public record UserSaveRequest(String firstName,
                              String lastName,
                              String turkishRepublicIdNumber,
                              String username,
                              LocalDate birthDate

                              ) {
}
