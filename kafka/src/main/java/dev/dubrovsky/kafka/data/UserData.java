package dev.dubrovsky.kafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private Integer id;

    private String username;

    private String email;

}
