package com.example.tasela.model.modelVO;

import com.example.tasela.model.entity.User;
import lombok.Data;

/**
 * @author jinmos
 * @date 2019-09-16 15:53
 */
@Data
public class UserLogin extends User {

    private String token;

}
