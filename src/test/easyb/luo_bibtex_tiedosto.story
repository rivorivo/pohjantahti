import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*
import java.io.File

description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi luoda bibtex tiedoston", {
    given 'käsky luo bibtec tiedosto valisttu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD", "luo-bibtex-tiedosto", "testiABC")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")
    	}
    when 'tiedoston nimi annettu', {
                kysely.run()
	}
    then 'viite luodaan' ,{
                file.delete()
		io.getPrints().shouldHave("tiedostosta testiABC.bib")
	}
}

scenario "käyttäjä voi tulostaa bibtex muodossa olevan viitteen jota ei ole olemassa", {
    given 'käsky lisää uusi viite valittu', {		
                io = new StubIO("tulosta-bibtex", "testi")
                kysely = new Kysely(io)			
    	}
    when 'tunniste annettu', {
                kysely.run()
	}
    then 'ilmoitetaan etta tunnistetta ei loytynyt' ,{
		io.getPrints().shouldHave("Tunnistetta ei")
	}
}

scenario "käyttäjä voi tulostaa bibtex muodossa olevan viitteen jota ei ole olemassa tapa2", {
    given 'käsky lisää uusi viite valittu', {		
                io = new StubIO("tulosta-bibtex testi")
                kysely = new Kysely(io)			
    	}
    when 'tunniste annettu', {
                kysely.run()
	}
    then 'ilmoitetaan etta tunnistetta ei loytynyt' ,{
		io.getPrints().shouldHave("Tunnistetta ei")
	}
}

scenario "käyttäjä voi tulostaa bibtex muodossa olevan viitteen", {
    given 'käsky lisää uusi viite valittu', {	
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD",
                    "tulosta-bibtex", "TunnisteABCD")              
                kysely = new Kysely(io)			
    	}
    when 'tunniste annettu', {
                kysely.run()
	}
    then 'tulostetaan viite bibtex muodossa' ,{
		io.getPrints().shouldHave("TunnisteABCD")
	}
}