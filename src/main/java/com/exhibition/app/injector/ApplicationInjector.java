package com.exhibition.app.injector;

import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.dao.UserDao;
import com.exhibition.app.dao.impl.UserDaoImpl;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.PasswordEncryptor;
import com.exhibition.app.service.UserService;
import com.exhibition.app.service.impl.UserServiceImpl;
import com.exhibition.app.service.validator.UserValidator;
import com.exhibition.app.service.validator.Validator;

public class ApplicationInjector {
    private static final HikariCPManager DB_CONNECTOR = new HikariCPManager("");

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);
    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();
    private static final Validator<UserEntity> USER_VALIDATOR = new UserValidator();
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_VALIDATOR);


    private static ApplicationInjector applicationInjector;

    private ApplicationInjector() { }

    public static ApplicationInjector getInstance() {
        if(applicationInjector == null) {
            synchronized (ApplicationInjector.class) {
                if(applicationInjector == null) {
                    applicationInjector = new ApplicationInjector();
                }
            }
        }
        return applicationInjector;
    }

    public UserService getUserService() { return USER_SERVICE; }



}
