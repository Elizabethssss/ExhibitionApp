package com.exhibition.app.injector;

import com.exhibition.app.command.Command;
import com.exhibition.app.command.exhibition.ExhibitionCommand;
import com.exhibition.app.command.exhibition.IndexCommand;
import com.exhibition.app.command.exhibition.ProfileCommand;
import com.exhibition.app.command.purchase.BuyingCommand;
import com.exhibition.app.command.purchase.PayingCommand;
import com.exhibition.app.command.purchase.PurchaseCommand;
import com.exhibition.app.command.purchase.RemoveTicketCommand;
import com.exhibition.app.command.user.LoginCommand;
import com.exhibition.app.command.user.LogoutCommand;
import com.exhibition.app.command.user.SignUpCommand;
import com.exhibition.app.dao.ExhibitionDao;
import com.exhibition.app.dao.ExpositionDao;
import com.exhibition.app.dao.TicketDao;
import com.exhibition.app.dao.connection.HikariCPManager;
import com.exhibition.app.dao.UserDao;
import com.exhibition.app.dao.impl.ExhibitionDaoImpl;
import com.exhibition.app.dao.impl.ExpositionDaoImpl;
import com.exhibition.app.dao.impl.TicketDaoImpl;
import com.exhibition.app.dao.impl.UserDaoImpl;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.entity.ExpositionEntity;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.*;
import com.exhibition.app.service.impl.ExhibitionServiceImpl;
import com.exhibition.app.service.impl.ExpositionServiceImpl;
import com.exhibition.app.service.impl.TicketServiceImpl;
import com.exhibition.app.service.impl.UserServiceImpl;
import com.exhibition.app.service.mapper.*;
import com.exhibition.app.service.validator.CreditCardValidator;
import com.exhibition.app.service.validator.impl.CreditCardValidatorImpl;
import com.exhibition.app.service.validator.impl.UserValidatorImpl;
import com.exhibition.app.service.validator.UserValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationInjector {
    private static final HikariCPManager DB_CONNECTOR = new HikariCPManager("db");
    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);
    private static final ExhibitionDao EXHIBITION_DAO = new ExhibitionDaoImpl(DB_CONNECTOR);
    private static final ExpositionDao EXPOSITION_DAO = new ExpositionDaoImpl(DB_CONNECTOR);
    private static final TicketDao TICKET_DAO = new TicketDaoImpl(DB_CONNECTOR);

    private static final UserValidator USER_USER_VALIDATOR = new UserValidatorImpl();
    private static final CreditCardValidator CREDIT_CARD_VALIDATOR= new CreditCardValidatorImpl();

    private static final Mapper<UserEntity, User> USER_MAPPER = new UserMapper(PASSWORD_ENCRYPTOR);
    private static final Mapper<ExhibitionEntity, Exhibition> EXHIBITION_MAPPER = new ExhibitionMapper();
    private static final Mapper<ExpositionEntity, Exposition> EXPOSITION_MAPPER = new ExpositionMapper();
    private static final Mapper<TicketEntity, Ticket> TICKET_MAPPER = new TicketMapper();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER);
    private static final ExhibitionService EXHIBITION_SERVICE = new ExhibitionServiceImpl(EXHIBITION_DAO, EXHIBITION_MAPPER);
    private static final ExpositionService EXPOSITION_SERVICE = new ExpositionServiceImpl(EXPOSITION_DAO, EXPOSITION_MAPPER);
    private static final TicketService TICKET_SERVICE = new TicketServiceImpl(TICKET_DAO, TICKET_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command SIGN_UP_COMMAND = new SignUpCommand(USER_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand(TICKET_SERVICE);
    private static final Command INDEX_COMMAND = new IndexCommand(EXHIBITION_SERVICE);
    private static final Command EXHIBITION_COMMAND = new ExhibitionCommand(EXHIBITION_SERVICE, EXPOSITION_SERVICE);
    private static final Command BUYING_COMMAND = new BuyingCommand(TICKET_SERVICE);
    private static final Command PURCHASE_COMMAND = new PurchaseCommand(TICKET_SERVICE, EXHIBITION_SERVICE);
    private static final Command REMOVE_TICKET_COMMAND = new RemoveTicketCommand(TICKET_SERVICE);
    private static final Command PAYING_COMMAND = new PayingCommand(TICKET_SERVICE);
    private static final Command PROFILE_COMMAND = new ProfileCommand(TICKET_SERVICE, EXHIBITION_SERVICE);

    private static final Map<String, Command> COMMANDS = initCommands();

    private static Map<String, Command> initCommands() {
        Map<String, Command> authorizationCommands = new HashMap<>();
        authorizationCommands.put("/login", LOGIN_COMMAND);
        authorizationCommands.put("/logout", LOGOUT_COMMAND);
        authorizationCommands.put("/signUp", SIGN_UP_COMMAND);
        authorizationCommands.put("/index", INDEX_COMMAND);
        authorizationCommands.put("/exhibition", EXHIBITION_COMMAND);
        authorizationCommands.put("/buying", BUYING_COMMAND);
        authorizationCommands.put("/purchase", PURCHASE_COMMAND);
        authorizationCommands.put("/removeTicket", REMOVE_TICKET_COMMAND);
        authorizationCommands.put("/pay", PAYING_COMMAND);
        authorizationCommands.put("/profile", PROFILE_COMMAND);

        return Collections.unmodifiableMap(authorizationCommands);
    }

    private static ApplicationInjector applicationInjector;

    private ApplicationInjector() { }

    public static ApplicationInjector getInstance() {
        if(applicationInjector == null) {
            synchronized (ApplicationInjector.class) {
                if (applicationInjector == null) {
                    applicationInjector = new ApplicationInjector();
                }
            }
        }
        return applicationInjector;
    }

    public static Map<String, Command> getCommands() {
        return COMMANDS;
    }

    public static UserValidator getUserValidator() {
        return USER_USER_VALIDATOR;
    }
    public static CreditCardValidator getCreditCardValidator() { return CREDIT_CARD_VALIDATOR; }

}
