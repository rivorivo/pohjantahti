import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi lisätä artikkeli-viitteen", {
    given 'käsky lisää uusi viite valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite- ja oikea artikkelityyppi sekä parametrit annettu', {
                kysely.run()
	}
    then 'viite luodaan' ,{
		io.getPrints().shouldHave("Viite luotu","Viitteitä yhteensä: 1")
	}
}

scenario "käyttäjä voi lisätä Book-viitteen", {
    given 'käsky lisää uusi viite valittu', {		
                io = new StubIO("luo-viite","book","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste")
                kysely = new Kysely(io)			
    	}
 when 'uusi viite- ja oikea artikkelityyppi sekä parametrit annettu', {
                kysely.run()
	}
    then 'viite luodaan' ,{
		io.getPrints().shouldHave("Viite luotu","Viitteitä yhteensä: 1")
	}
}

scenario "käyttäjä voi lisätä Inproceedings-viitteen", {
    given 'käsky lisää uusi viite valittu', {		
              io = new StubIO("luo-viite","inproceedings","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Tunniste")
              kysely = new Kysely(io)			
    	}
 when 'uusi viite- ja oikea artikkelityyppi sekä parametrit annettu', {
              kysely.run()
	}
    then 'viite luodaan' ,{
              io.getPrints().shouldHave("Viite luotu","Viitteitä yhteensä: 1")
	}
}

scenario "käyttäjä voi lisätä 2viitettä", {
    given 'käsky lisää uusi viite valittu', {		
                io = new StubIO("luo-viite","book","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste2")
                kysely = new Kysely(io)	
           		
    	}
 when 'uusi viite- ja oikea artikkelityyppi sekä parametrit annettu', {
                kysely.run()
	}
    then 'viite luodaan' ,{
		io.getPrints().shouldHave("Viite luotu","Viitteitä yhteensä: 2")
	}
}

scenario "käyttäjä ei voi lisätä olematonta viitetyyppiä",{
    given 'käsky lisää uusi viite valittu', {		
              io = new StubIO("luo-viite","wikipedia")
              kysely = new Kysely(io)			
    	}
 when 'uusi viite- ja oikea artikkelityyppi sekä parametrit annettu', {
              kysely.run()
	}
    then 'viite luodaan' ,{
              io.getPrints().shouldHave(" ei luotu")
	}
}
