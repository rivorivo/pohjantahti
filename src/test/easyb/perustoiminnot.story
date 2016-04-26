import viitteet.*
import ohtumini.*
import ohtumini.UI.*
import ohtumini.io.*
import java.io.File

description 'ohjelma käynnistyy, kertoo komennot ja sulkeutuu'

scenario"ohjelma käynnistyy"{
	 	
	 	given 'mainin komennot suoritetaan', {
		 	IO io = new StubIO("");
	        Kysely kysely = new Kysely(io);        
        }
        when 'kysely alkaa', {
        	kysely.run();
        }
        then'ohjelma on käynnissä', {

        }
}

scenario "ohjelma sulkeutuu"{

		given 'kysely aloitetaan', {
			IO io = new StubIO("lopeta");
	        Kysely kysely = new Kysely(io);   
		}
		when 'lopeta-komento annettu', {
			kysely.run();
		}
		then'ohjelma sulkeutuu', {

		}

}

