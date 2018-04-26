package pl.coderstrust.accounting.model;

import javax.xml.bind.ValidationException;

public class CompanyValidator {

  public static void validate(Company company) throws ValidationException {
    if (company.getName() == null) {
      throw new ValidationException(
          "Validation of company name field failed. Expected name field filled up, got: ",
          String.valueOf(company.getName()));
    }
    if (company.getTaxID() == null) {
      throw new ValidationException(
          "Validation of company tax id field failed. Expected tax id field filled up, got: ",
          String.valueOf(company.getTaxID()));
    }

    if (company.getTaxID().toString().length() != 10) {
      throw new ValidationException(
          "Validation of company tax id field failed. Expected tax id should consist of 10 digits, got: ",
          String.valueOf(company.getTaxID()));
    }

    if (!company.getPostalCode().toString().matches("\\d{2}-\\d{3}")) {
      throw new ValidationException(
          "Validation of company tax id field failed. Expected tax id should consist of 10 digits, got: ",
          String.valueOf(company.getPostalCode()));
    }

  }

}