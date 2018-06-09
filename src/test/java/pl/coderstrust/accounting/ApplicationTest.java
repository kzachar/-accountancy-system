package pl.coderstrust.accounting;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnInvoiceWhichWasEarlierAdded() throws Exception {

   String postResponse = mockMvc
        .perform(post("/invoices")
            .content(
                "{\"id\":5,\"identifier\":\"TestIdentifier5\",\"issuedDate\":\"2018-02-01\",\""
                    + "buyer\":{\"name\":\"CompanyBuyerTest5\",\"taxId\":\"5555555555\",\""
                    + "streetAndNumber\":\"Test Buyer Street 5\",\"postalCode\":\"55-555\",\""
                    + "location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\""
                    + "CompanySellerTest5\",\"taxId\":\"5555555555\",\"streetAndNumber\":\""
                    + "Test Seller Street 5\",\"postalCode\":\"55-555\",\"location\":\""
                    + "TestLocationSeller4\"},\"entries\":[]}")
            .contentType(
                MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
       .andReturn()
       .getResponse()
       .getContentAsString();

    mockMvc
        .perform(get("/invoices"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].identifier", is("TestIdentifier5")))
        .andExpect(jsonPath("$[0].id", is(Integer.valueOf(postResponse))))
        .andExpect(
            content().string(containsString("[]")
            ));
  }

  @Test
  public void shouldReturnEmptyArrayWhenNothingWasAdded() throws Exception {
    mockMvc
        .perform(get("/invoices"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)))
        .andExpect(
            content().string(containsString("[]")
            ));
  }
}