package taf.utils;

import com.github.javafaker.Faker;
import net.serenitybdd.core.Serenity;

public class FakeData {

  private static Faker faker = new Faker();

  public static String getFakeName() {
    return faker.name().fullName();
  }

  public static String getJobName() {
    String position = faker.job().position();
    Serenity.setSessionVariable("jobPosition").to(position);
    return position;
  }
}
