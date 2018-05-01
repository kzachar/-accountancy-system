package pl.coderstrust.accounting.model;

import org.junit.Test;

public class CompanyValidatorTest {

  @Test(expected = CompanyValidatorException.class)
  public void shouldCheckIfFieldCompanyNameFilled() throws CompanyValidatorException {

    Company testCompany = new Company(null, 1010101010, "streetAndNumber", "00-000", "location");

    CompanyValidator.validate(testCompany);
  }

  @Test(expected = CompanyValidatorException.class)
  public void shouldCheckIfFieldTaxIdFilled() throws CompanyValidatorException {

    Company testCompany = new Company("name", null, "streetAndNumber", "00-000", "location");

    CompanyValidator.validate(testCompany);
  }

  @Test(expected = CompanyValidatorException.class)
  public void shouldCheckIfFieldTaxIdInRightAmountOfDigits() throws CompanyValidatorException {

    Company testCompany = new Company("name", 10101010, "streetAndNumber", "00-000", "location");

    CompanyValidator.validate(testCompany);
  }

  @Test(expected = CompanyValidatorException.class)
  public void shouldCheckIfFieldPostalCodeInRightFormat() throws CompanyValidatorException {

    Company testCompany = new Company("name", 1010101010, "streetAndNumber", "00-00", "location");

    CompanyValidator.validate(testCompany);
  }
}
