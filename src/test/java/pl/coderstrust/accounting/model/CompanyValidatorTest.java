package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.accounting.model.validator.CompanyValidator;
import pl.coderstrust.accounting.model.validator.exception.CompanyValidationException;

import java.util.Collection;


public class CompanyValidatorTest {

  private CompanyValidator companyValidator;

  @Before
  public void setUp() {
    companyValidator = new CompanyValidator();
  }

  @Test
  public void shouldCheckIfFieldCompanyNameFilled() {
    Company testCompany = new Company(null, "1010101010", "streetAndNumber", "00-000", "location");

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("name"));
  }

  @Test
  public void shouldCheckIfFieldTaxIdFilled() {

    Company testCompany = new Company("name", null, "streetAndNumber", "00-000", "location");

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("tax"));
  }

  @Test
  public void shouldCheckIfFieldTaxIdInRightAmountOfDigits() {

    Company testCompany = new Company("name", "101010101", "streetAndNumber", "00-000", "location");

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("tax"));
  }

  @Test
  public void shouldCheckIfFieldPostalCodeInRightFormat() {

    Company testCompany = new Company("name", "1010101010", "streetAndNumber", "00-00", "location");

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("code"));
  }

  @Test
  public void shouldCheckIfFieldStreetAndNumberFilled() {

    Company testCompany = new Company("name", "1010101010", null, "00-000", "location");

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("street"));
  }

  @Test
  public void shouldCheckIfFieldLocationFilled() {

    Company testCompany = new Company("name", "1010101010", "streetAndNumber", "00-000", null);

    Collection<CompanyValidationException> result = companyValidator
        .validate(testCompany);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertTrue(result.iterator().next().getMessage().contains("location"));
  }
}