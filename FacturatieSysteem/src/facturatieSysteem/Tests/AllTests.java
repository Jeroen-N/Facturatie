package facturatieSysteem.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BehandelCodeTest.class, FacturatieManagerImplTest.class,
		KlantManagerImplTest.class, KlantTest.class,
		VerzekeringPolisTest.class,
		VerzekeringsmaatschappijManagerImplTest.class,
		VerzekeringsmaatschappijTest.class, VerzekeringstypeTest.class })
public class AllTests {

}
