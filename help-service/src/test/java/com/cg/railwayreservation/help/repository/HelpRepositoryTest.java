package com.cg.railwayreservation.help.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.help.model.HelpModel;

@SpringBootTest
public class HelpRepositoryTest {

    private HelpRepository helpRepository;

    @BeforeEach
    public void setUp() {
        helpRepository = mock(HelpRepository.class);
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        HelpModel expectedHelp = new HelpModel();
        expectedHelp.setUsername(username);

        when(helpRepository.findByUsername(username)).thenReturn(expectedHelp);

        HelpModel result = helpRepository.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testFindByIssue() {
        String issue = "Test issue";
        HelpModel expectedHelp = new HelpModel();
        expectedHelp.setIssue(issue); 

        when(helpRepository.findByIssue(issue)).thenReturn(expectedHelp);

        HelpModel result = helpRepository.findByIssue(issue);

        assertNotNull(result);
        assertEquals(issue, result.getIssue());
    }
}
