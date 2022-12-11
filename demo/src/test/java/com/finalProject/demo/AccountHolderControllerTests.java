package com.finalProject.demo;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.finalProject.demo.models.Accounts.Account;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.finalProject.demo.Controllers.Users.AccountHolderController;
import com.finalProject.demo.models.Accounts.Checking;
import com.finalProject.demo.models.Accounts.StudentChecking;
import com.finalProject.demo.models.DTO.TransferDTO;
import com.finalProject.demo.models.Users.AccountHolder;
import com.finalProject.demo.models.Users.Address;
import com.finalProject.demo.repositories.Accounts.*;
import com.finalProject.demo.repositories.Users.AccountHolderRepository;
import com.finalProject.demo.repositories.Users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    ChekingRepository chekingRepository;

    @Autowired
    private UserRepository userRepository;
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

    String body;
    MvcResult mvcResult;
    TransferDTO transferDTO;

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
                new StudentChecking(accountHolder4, accountHolder5, "12345")

        ));
    }

    @AfterEach
    void tearDown(){
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldGetAccountById_WhenGetIsPerformed_OK() throws Exception{
        mvcResult = mockMvc.perform(get("/accountHolder/{accountId}/{ownerId}", 1, 1)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Joan"));
    }

    @Test
    void shouldGetAccountById_WhenGetIsPerformed_FAIL() throws Exception {
        mvcResult = mockMvc.perform(get("/accountHolder/{accountId}/{ownerId}", 1, 2)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Joan"));
    }

    @Test
    void shouldGetAccountBalanceById_WhenGetIsPerformed_OK() throws Exception {
        mvcResult = mockMvc.perform(get("/accountHolder/{accountId}/{ownerId}", 1, 1)).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("0.00"));
    }

    @Test
    void shouldMakeTransference_WhenPostIsPerformed_OK() throws Exception{
        transferDTO = new TransferDTO(new BigDecimal(100), 1L, "Joan", 2L);
        body = objectMapper.writeValueAsString(transferDTO);

        //Setting an amount of money in the 1L account
        Account account = accountRepository.findById(1L).get();
        account.setBalance(new BigDecimal(600));
        accountRepository.save(account);

        //Testing if the trans from 1L to 2L is done
        mvcResult = mockMvc.perform(post("/account-holder/transfer-money").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("100"));
        assertEquals(new BigDecimal("100.00"), accountRepository.findById(2L).get().getBalance());
    }
}
