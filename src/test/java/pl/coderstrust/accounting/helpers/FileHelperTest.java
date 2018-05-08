package pl.coderstrust.accounting.helpers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelperTest {

  @Test
  public void shouldWriteLinesToFileAndCheckIfParametersNotNull() throws IOException {
    //given
    File file = File.createTempFile("123", "");
    file.deleteOnExit();
    List<String> lines = new ArrayList<>();
    lines.add("Hello Piotr!");
    lines.add("Hello Kamil!");
    lines.add("Hello Adam!");

    //when
    FileHelper.writeToFile(lines, file.toString());
    List<String> result = testReadFromFile(file);

    //then
    assertNotNull(file);
    assertNotNull(lines);
    assertThat(lines, is(equalTo(result)));
  }

  @Test(expected = NullPointerException.class)
  public void shouldWriteLinesToFileAndThrowNullPointerExceptionIfLInesParameterIsNull() throws IOException {
    //given
    File file = File.createTempFile("123", "");
    file.deleteOnExit();
    List<String> lines = new ArrayList<>();

    //when
    FileHelper.writeToFile(null, file.toString());

    //then
    assertThat(lines,is(equalTo(NullPointerException.class)));
  }

  @Test(expected = NullPointerException.class)
  public void shouldWriteLinesToFileAndThrowNullPointerExceptionIfFileParameterIsNull() throws IOException {
    //given
    File file = File.createTempFile("123", "");
    file.deleteOnExit();
    List<String> lines = new ArrayList<>();

    //when
    FileHelper.writeToFile(lines, null);

    //then
    assertThat(file,is(equalTo(NullPointerException.class)));
  }

  @Test
  public void shouldReadLinesFromFileAndCheckIfParametersNotNull() throws IOException {
    //given
    File file = File.createTempFile("123", "");
    file.deleteOnExit();
    List<String> lines = new ArrayList<>();
    lines.add("Hello Piotr!");
    lines.add("Hello Wojtek!");
    lines.add("Hello Ania!");
    testWriteToFile(lines, file);

    //when
    List<String> result = FileHelper.readFromFile(file.toString());

    //then
    assertNotNull(file);
    assertThat(lines, is(equalTo(result)));
  }

  private static List<String> testWriteToFile(List<String> lines, File file)
      throws IOException {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
      for (String line : lines) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
    }
    return lines;
  }

  private static List<String> testReadFromFile(File file) throws IOException {
    ArrayList<String> lines = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
    }
    return lines;
  }
}