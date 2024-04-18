package com.NTT.NTTTeam.Command.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    String emailAddress;

    int otp;
}
