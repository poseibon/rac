package com.zwedu.rac.interfaces;

import com.zwedu.rac.sdk.rdo.UserSession;
import com.zwedu.rac.application.service.UserSessionAppService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserSessionTest {

    @Autowired
    private UserSessionAppService userSessionAppService;

    @Test
    public void queryUserSession() {
        UserSession userSession = userSessionAppService.getSession(1L, "ZBAdmin");
    }
}
