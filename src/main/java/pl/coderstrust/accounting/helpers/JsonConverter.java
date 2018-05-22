package pl.coderstrust.accounting.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;

public class JsonConverter {

  public static String toJson(Invoice invoice) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String json = mapper.writeValueAsString(invoice);
    return json;
  }

  public static Invoice fromJson(String json) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    Invoice invoiceFromJson = mapper.readValue(json, Invoice.class);
    return invoiceFromJson;
  }
}