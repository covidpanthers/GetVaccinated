import com.sweng894.GetVaccinated.Tier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TierTests {
  @Test
  void checkTier (){
    Tier tier = new Tier();
    tier.setId(1);
    tier.setDescription("Guidelines, decisions, country");
    tier.setTitle("Phase-1");
    tier.setEndDate(java.sql.Date.valueOf("2021-02-06"));
    tier.setStartDate(java.sql.Date.valueOf("2021-02-12"));
    tier.setVaccineId(1);

    assertEquals(tier.getId(), 1);
    assertEquals(tier.getDescription(), "Guidelines, decisions, country");
    assertEquals(tier.getTitle(), "Phase-1");
    assertEquals(tier.getEndDate().toString(), java.sql.Date.valueOf("2021-02-06").toString());
    assertEquals(tier.getStartDate().toString(), java.sql.Date.valueOf("2021-02-12").toString());
    assertEquals(tier.getVaccineId(), 1);
  }
}
