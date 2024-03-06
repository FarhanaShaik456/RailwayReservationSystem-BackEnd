package com.cg.railwayreservation.admin.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.admin.model.LoginModel;

@SpringBootTest
public class LoginRepositoryTest {

	@Autowired
    private LoginRepository loginRepository;

    @BeforeEach
    public void setUp() {
        loginRepository = mock(LoginRepository.class);
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        LoginModel expectedLogin = new LoginModel();
        expectedLogin.setUsername(username); 

        when(loginRepository.findByUsername(username)).thenReturn(expectedLogin);

        LoginModel result = loginRepository.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }
}
