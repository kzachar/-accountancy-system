package pl.coderstrust.accounting.helpers;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;

@RunWith(JUnitParamsRunner.class)
public class JsonConverterTest {

  @Parameters(method = "ParametersForShouldCheckIfInvoiceConvertedToJson")
  @Test
  public void ShouldCheckIfInvoiceConvertedToJson(String expected, Invoice given)
      throws IOException {

    String toJson = JsonConverter.toJson(given);

    Assert.assertThat(expected, is(equalTo(toJson)));
  }

  private Object[] ParametersForShouldCheckIfInvoiceConvertedToJson() {
    return $(
        $("{\"id\":5,\"identifier\":\"TestIdentifier5\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest5\",\"taxId\":\"555555555\","
                + "\"streetAndNumber\":\"Test Buyer Street 5\",\"postalCode\":\"55555\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest5\","
                + "\"taxId\":\"555555555\",\"streetAndNumber\":\"Test Seller Street 5\","
                + "\"postalCode\":\"55555\",\"location\":\"TestLocationSeller4\"},\"entries\":[]}",
            InvoiceHelper.getSampleInvoiceWithId5()),
        $("{\"id\":6,\"identifier\":\"TestIdentifier6\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest6\",\"taxId\":\"666666666\","
                + "\"streetAndNumber\":\"Test Buyer Street 6\",\"postalCode\":\"66666\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest6\","
                + "\"taxId\":\"666666666\",\"streetAndNumber\":\"Test Seller Street 4\","
                + "\"postalCode\":\"66666\",\"location\":\"TestLocationSeller4\"},"
                + "\"entries\":[{\"description\":\"Test Entry #4\",\"price\":10,\"vat\":8}]}",
            InvoiceHelper.getSampleInvoiceWithId6()),
        $("{\"id\":7,\"identifier\":\"TestIdentifier7\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest7\",\"taxId\":\"777777777\","
                + "\"streetAndNumber\":\"Test Buyer Street 7\",\"postalCode\":\"77777\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest7\","
                + "\"taxId\":\"777777777\",\"streetAndNumber\":\"Test Seller Street 7\","
                + "\"postalCode\":\"77777\",\"location\":\"TestLocationSeller4\"},"
                + "\"entries\":[{\"description\":\"Test Entry #1\",\"price\":10,\"vat\":23},"
                + "{\"description\":\"Test Entry #2\",\"price\":10,\"vat\":8},"
                + "{\"description\":\"Test Entry #3\",\"price\":10,\"vat\":5},"
                + "{\"description\":\"Test Entry #4\",\"price\":10,\"vat\":0}]}",
            InvoiceHelper.getSampleInvoiceWithId7())
    );
  }

  @Parameters(method = "ParametersForShouldCheckIfInvoiceConvertedFromJson")
  @Test
  public void ShouldCheckIfInvoiceConvertedFromJson(Invoice expected, String json)
      throws IOException {

    Invoice fromJson = JsonConverter.fromJson(json);

    Assert.assertThat(expected, is(equalTo(fromJson)));
  }

  private Object[] ParametersForShouldCheckIfInvoiceConvertedFromJson() {
    return $(
        $(InvoiceHelper.getSampleInvoiceWithId5(),
            "{\"id\":5,\"identifier\":\"TestIdentifier5\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest5\",\"taxId\":\"555555555\","
                + "\"streetAndNumber\":\"Test Buyer Street 5\",\"postalCode\":\"55555\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest5\","
                + "\"taxId\":\"555555555\",\"streetAndNumber\":\"Test Seller Street 5\","
                + "\"postalCode\":\"55555\",\"location\":\"TestLocationSeller4\"},\"entries\":[]}"),
        $(InvoiceHelper.getSampleInvoiceWithId6(),
            "{\"id\":6,\"identifier\":\"TestIdentifier6\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest6\",\"taxId\":\"666666666\","
                + "\"streetAndNumber\":\"Test Buyer Street 6\",\"postalCode\":\"66666\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest6\","
                + "\"taxId\":\"666666666\",\"streetAndNumber\":\"Test Seller Street 4\","
                + "\"postalCode\":\"66666\",\"location\":\"TestLocationSeller4\"},"
                + "\"entries\":[{\"description\":\"Test Entry #4\",\"price\":10,\"vat\":8}]}"),
        $(InvoiceHelper.getSampleInvoiceWithId7(),
            "{\"id\":7,\"identifier\":\"TestIdentifier7\",\"issuedDate\":\"2018-05-15\","
                + "\"buyer\":{\"name\":\"CompanyBuyerTest7\",\"taxId\":\"777777777\","
                + "\"streetAndNumber\":\"Test Buyer Street 7\",\"postalCode\":\"77777\","
                + "\"location\":\"TestLocationBuyer4\"},\"seller\":{\"name\":\"CompanySellerTest7\","
                + "\"taxId\":\"777777777\",\"streetAndNumber\":\"Test Seller Street 7\","
                + "\"postalCode\":\"77777\",\"location\":\"TestLocationSeller4\"},"
                + "\"entries\":[{\"description\":\"Test Entry #1\",\"price\":10,\"vat\":23},"
                + "{\"description\":\"Test Entry #2\",\"price\":10,\"vat\":8},"
                + "{\"description\":\"Test Entry #3\",\"price\":10,\"vat\":5},"
                + "{\"description\":\"Test Entry #4\",\"price\":10,\"vat\":0}]}\",\n")
    );
  }

  @Parameters(method = "ParametersForShouldCheckIfInvoiceConvertedToAndFromJson")
  @Test
  public void ShouldCheckIfInvoiceConvertedToAndFromJson(Invoice expected)
      throws IOException {

    String toJson = JsonConverter.toJson(expected);
    Invoice fromJson = JsonConverter.fromJson(toJson);

    Assert.assertThat(expected, is(equalTo(fromJson)));
  }

  private Object[] ParametersForShouldCheckIfInvoiceConvertedToAndFromJson() throws IOException {
    return $(
        $(InvoiceHelper.getSampleInvoiceWithId5()),
        $(InvoiceHelper.getSampleInvoiceWithId6()),
        $(InvoiceHelper.getSampleInvoiceWithId7())
    );

  }
}

