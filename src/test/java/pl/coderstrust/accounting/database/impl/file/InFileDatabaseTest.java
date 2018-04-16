package pl.coderstrust.accounting.database.impl.file;

import org.junit.Test;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

public class InFileDatabaseTest extends DatabaseTest {


  @Override
  protected Database getDataBase() {
    return new InFileDatabase();
  }


  @Test
  public void shouldCreateFileWhenInvoiceAdded() {
    new InFileDatabase();
  }
}