package com.finalProject.demo;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.finalProject.demo.models.Accounts.*;
import com.finalProject.demo.models.DTO.AccountDTO;
import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Address;
import com.finalProject.demo.models.Users.ThirdParty;
import com.finalProject.demo.repositories.Accounts.*;
import com.finalProject.demo.repositories.Users.AccountHolderRepository;
import com.finalProject.demo.repositories.Users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    ChekingRepository chekingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Module module = new JavaTimeModule();
    private MockMvc mockMvc;

    AccountHolder accountHolder1;
    AccountHolder accountHolder2;
    AccountHolder accountHolder3;
    AccountHolder accountHolder4;
    AccountHolder accountHolder5;

    AccountDTO accountDTO;
    String body;
    MvcResult mvcResult;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        accountHolder1 = new AccountHolder("Joan", LocalDate.of(1989,2,15), new Address("Calle falsa 123", "82345", "Barcelona", "España"), "joan1@gmail.com");
        accountHolder2 = new AccountHolder("Rosa", LocalDate.of(2071, 4, 23), new Address("Calle Mexico 23", "85438", "Sabadell", "España"), "rosa2@gmail.com");
        accountHolder3 = new AccountHolder("Miguel", LocalDate.of(1987, 7, 17), new Address("Calle Napoles 182", "25478", "Girona", "España"), "miguel3@gmail.com");
        accountHolder4 = new AccountHolder("Arturo", LocalDate.of(2008, 9, 28), new Address("Calle Segovia 02", "68534", "Murcia", "España"), "arturo4@gmail.com");
        accountHolder5 = new AccountHolder("Maia", LocalDate.of(2002, 11, 2), new Address("Calle Madrid 87", "54786", "Gijon", "España"), "maia5@gmail.com");
        accountHolderRepository.saveAll(List.of(
                accountHolder1,
                accountHolder2,
                accountHolder3,
                accountHolder4,
                accountHolder5
        ));

        accountRepository.saveAll(List.of(
                new Checking(accountHolder1,accountHolder2, "12345"),
                new Checking(accountHolder3,null, "12345"),
                new StudentChecking(accountHolder4, accountHolder5, "12345"),
                new Savings(accountHolder1, null, "12345"),
                new CreditCard(accountHolder1,accountHolder2)
        ));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldAddNewAccountHolder_whenPostIsPerformed_OK() throws Exception {
        AccountHolder accountHolder = new AccountHolder("Anna", LocalDate.of(2003, 11, 16), new Address("Calle Lucrecia 128", "64557", "Pontevedra", "España"), "anna6@gmail.com");
        objectMapper.registerModule(module);
        body = objectMapper.writeValueAsString(accountHolder);
        mvcResult = mockMvc.perform(post("/admin/addAccountHolder").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Anna"));
    }

    @Test
    void shouldAddNewThirdParty_whenPostIsPerformed_OK() throws Exception {
        ThirdParty thirdParty = new ThirdParty("12345", "cajero1");
        body = objectMapper.writeValueAsString(thirdParty);
        mvcResult = mockMvc.perform(post("/admin/addThirdParty").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Cafeteria"));
    }

    @Test
    void shouldAddNewCheckingAccount_whenPostIsPerformed_OK() throws Exception{
        Checking checking1 = new Checking(accountHolder1,accountHolder2, "12345");
        body = objectMapper.writeValueAsString(accountDTO);
        mvcResult = mockMvc.perform(post("/admin/addChecking").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("La Rosalia"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("The Weekend"));
    }

    @Test
    void shouldAddNewSavingsAccount_whenPostIsPerformed_OK() throws Exception{
        Savings savings1 = new Savings(accountHolder1, null, "12345");
        body = objectMapper.writeValueAsString(accountDTO);
        mvcResult = mockMvc.perform(post("/admin/addSavings").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1000"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("0.0025"));
    }

    @Test
    void shouldAddNewCreditCardAccount_whenPostIsPerformed_OK() throws Exception{
        CreditCard creditCard1 = new CreditCard(accountHolder1,accountHolder2);
        body = objectMapper.writeValueAsString(accountDTO);
        mvcResult = mockMvc.perform(post("/admin/addCreditCard").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("0.2"));
    }

    @Test
    void shouldShowAccount_whenGetIsPerformed_OK() throws Exception{
        Long id = 1L;
        mvcResult = mockMvc.perform(get("/admin/clientAccount/{accountId}", id)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Joan"));
    }

    @Test
    void shouldDeleteAccount_whenDeleteIsPerformed_OK() throws Exception{
        Long id = 1L;
        mvcResult = mockMvc.perform(delete("/admin/deleteAccount/{accountId}", id)).andExpect(status().isOk()).andReturn();
        assertFalse(accountRepository.findById(1L).isPresent());
    }

}
