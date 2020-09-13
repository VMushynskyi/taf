package taf.service;

import java.util.List;
import java.util.Map;
import net.serenitybdd.core.Serenity;

public class UserService {

  public static void storeNextIdByExistingOrder() {
    List<Map<String, Object>> usersList = Serenity.sessionVariableCalled("usersList");
    System.out.println(usersList.size());
    Map<String, Object> lastUser = usersList.stream().skip(usersList.size() - 1).findFirst().get();
    int id = (int) lastUser.get("id");
    ++id;
    Serenity.setSessionVariable("id").to(id);
  }
}
