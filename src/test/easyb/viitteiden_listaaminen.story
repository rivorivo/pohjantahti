import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'Käyttäjä voi listata viitteet'

scenario "käyttäjä voi listata viiteitä lyhyesti", {
    given 'Uusi Viite luotu, se listataan lyhyesti', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "listaa-viitteet", "Y")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite listattu, katsotaan viitteen tiedot lyhyesti', {
                kysely.run()
	}
    then 'viite on listattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Tunniste (Article)")
	}
}

scenario "käyttäjä voi listata koko viitteen", {
    given 'Uusi Viite luotu, se listataan kokonaan', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "listaa-viitteet", "n")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite listattu, katsotaan viitteen kaikki tiedot', {
                kysely.run()
	}
    then 'viite on listattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Key: Testi10")
	}
}

scenario "käyttäjä antaa virheellisen syötteen listauksen kyselyssä", {
    given 'Uusi Viite luotu, ja listaukseen annettu väärä syöte', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "listaa-viitteet", "", "y")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite listattu, ja väärä syöte annettu', {
                kysely.run()
	}
    then 'kysytty uudelleen, viite on listattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Tunniste (Article)")
	}
}

