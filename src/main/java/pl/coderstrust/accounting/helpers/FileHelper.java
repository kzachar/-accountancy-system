package pl.coderstrust.accounting.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  public static void writeToFile(List<String> lines, String filePath) throws IOException {
    if (lines == null) {
      throw new IllegalArgumentException("Parameter lines may not be null");
    }
    if (filePath == null) {
      throw new IllegalArgumentException("Parameter filePath may not be null");
    }
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
      for (String line : lines) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
    }
  }

  public static List<String> readFromFile(String file) throws IOException {
    ArrayList<String> lines = new ArrayList<>();
    if (new File(file).exists()) {
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          lines.add(line);
        }
      }
    }
    return lines;
  }

  public static List<String> getInvoiceFromFileById(String file, int id) throws IOException {
    boolean idFound = false;
    ArrayList<String> lines = new ArrayList<>();
    if (new File(file).length() == 0) {
      throw new IllegalArgumentException("List of invoices is empty");
    } else if (new File(file).exists()) {
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          if ((JsonConverter.fromJson(line).getId() == id)) {
            lines.add(line);
            idFound = true;
          }
        }
      }
      if (!idFound) {
        throw new IllegalArgumentException("No such id in the list");
      }
    }
    return lines;
  }

  public static void appendToFile(String line, String filePath)
      throws IOException {
    if (line == null) {
      throw new IllegalArgumentException("Parameter line may not be null");
    }
    if (filePath == null) {
      throw new IllegalArgumentException("Parameter filePath may not be null");
    }
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
      bufferedWriter.append(line);
      bufferedWriter.newLine();
    }
  }

  public static List<String> removeFromFile(String file, int id) throws IOException {
    boolean idFound = false;
    ArrayList<String> lines = new ArrayList<>();
    if (new File(file).length() == 0) {
      throw new IllegalArgumentException("List of invoices is empty");
    } else if (new File(file).exists()) {
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          lines.add(line);
          if ((JsonConverter.fromJson(line).getId() == id)) {
            lines.remove(line);
            idFound = true;
          }
        }
      }
      if (!idFound) {
        throw new IllegalArgumentException("No such id in the list");
      }
    }
    return lines;
  }
}