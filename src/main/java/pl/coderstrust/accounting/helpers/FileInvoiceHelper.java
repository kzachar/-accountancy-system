package pl.coderstrust.accounting.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileInvoiceHelper {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public static void writeInvoiceToFile(Invoice invoiceToWrite, String filePath)
      throws IOException {
    String convertedInvoice = mapper.writeValueAsString(invoiceToWrite);
    FileHelper.appendToFile(convertedInvoice, filePath);
  }

  public static int getAndIncrementLastId(String filePath) throws IOException {
    List<String> lines = null;
    lines = FileHelper.readFromFile(filePath);
    if (lines.isEmpty()) {
      FileHelper.writeToFile(Collections.singletonList("0"), filePath);//  liste jednoelemtnwoa
      return 0;
    }
    if (lines.size() > 1) {
      throw new IllegalStateException(
          "File can't included more than 1 id");
    }
    int id = Integer.valueOf(lines.get(0)) + 1;
    List<String> idToSave = Arrays.asList(String.valueOf(id));
    FileHelper.writeToFile(idToSave, filePath);
    return id;
  }
}