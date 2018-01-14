package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ubuntu on 18-1-14.
 */
@Service
@Transactional
public class CrmAdminServiceImpl {

    @Autowired
    private AdminMapper adminMapper;


    public String login(String adminName,String adminPassword){

        return null;

    }


//    /**
//     * 加密
//     *
//     * @param password
//     *            包括userPassword,UserPayPwd,明码
//     * @param passwordSalt
//     *            盐值 user.getPasswordSalt()
//     * @return 密文
//     */
//    public static String encrypt(String password, String passwordSalt) {
//        return DigestUtils.md5Hex(DigestUtils.md5Hex(password) + passwordSalt);
//    }
//
//    /**
//     * 校验
//     *
//     * @param plaintext
//     *            明文
//     * @param ciphertext
//     * @param passwordSalt
//     * @return
//     */
//    public static boolean check(String plaintext, String ciphertext, String passwordSalt) {
//        return StringUtils.equals(ciphertext, encrypt(plaintext, passwordSalt));
//    }

}
