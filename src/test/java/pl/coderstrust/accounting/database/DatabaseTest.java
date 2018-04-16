package pl.coderstrust.accounting.database;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

public abstract class DatabaseTest {

  protected abstract Database getDataBase();

  @Test
  public void shouldReturn2InvoicesWhenAdded(){
    Database db = getDataBase();

    db.saveInvoice(new Invoice());

  }

}