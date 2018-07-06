package pl.coderstrust.accounting.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;

public class JsonConverter {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public static String toJson(Invoice invoice) throws IOException {
    return mapper.writeValueAsString(invoice);
  }

  public static Invoice fromJson(String json) throws IOException {
    return mapper.readValue(json, Invoice.class);
  }
}