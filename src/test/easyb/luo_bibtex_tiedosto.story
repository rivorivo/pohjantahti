import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*
import java.io.File

description 'Käyttäjä voi luoda BibTex muotoisen tiedoston'

scenario "käyttäjä voi luoda bibtex tiedoston kaikista viitteistä", {
    given 'käsky luo bibtex tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD", "luo-bibtex-tiedosto", "testiABCD",
                     "luo-kaikista")
                kysely = new Kysely(io)	
                file = new File("testiABCD.bib")
    	}
    when 'tiedoston nimi annettu ja luo-kaikista valittu', {
                kysely.run()
	}
    then 'viitteet tulostetaan tiedostoon' ,{
                file.delete()
		io.getPrints().shouldHave("tiedostosta testiABCD.bib")
	}
}

scenario "käyttäjä voi luoda bibtex tiedoston osasta viitteistä", {
    given 'käsky luo bibtex tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "1", "TunnisteABCD", "", "luo-tiedosto")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")
    	}
    when 'valittu tulostettavat viitteet', {
                kysely.run()
	}
    then 'valitut viitteet tulostetaan tiedostoon' ,{
                file.delete()
		io.getPrints().shouldHave("tiedostosta testiABC.bib")
	}
}

scenario "käyttäjä voi luodessaan bibtex tiedostoa osasta listata tulostettavaksi lisätyt", {
    given 'Bibtex tiedoston tulostus ja tulostettavaksi lisätty viite', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "1", "TunnisteABCD", "", "3", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")
    	}
    when 'Lisättyjen tulostus valittu', {
                kysely.run()
	}
    then 'tulostetaan bibtextattavien viitteiden lista' ,{
                file.delete()
		io.getPrints().shouldHave("TunnisteABCD (Article)")
	}
}

scenario "käyttäjä voi luoda bibtex tiedoston osasta lisäämättömät listaus", {
    given 'Bibtex tiedoston tulostus ja tulostettavaksi lisätty viite', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "1", "TunnisteABCD", "", "2", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")               
    	}
    when 'lisätty yksi tunniste bibtextiedostoon tulostettavaksi', {
                kysely.run()
	}
    then 'tulostetaan lisäämättömien viitteiden lista' ,{
                file.delete()
		io.getPrints().shouldHave("Tunniste1234 (Article)")
	}
}

scenario "käyttäjä voi luoda bibtex tiedoston osasta listan tyhjennys", {
    given 'käsky luo bibtec tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "1", "TunnisteABCD", "", "4","y", "3", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")               
    	}
    when 'lisätty yksi tunniste bibtextiedostoon tulostettavaksi', {
                kysely.run()
	}
    then 'tulostetaan lisattyjen viitteiden lista ja se on tyhja' ,{
                file.delete()
		io.getPrints().shouldNotHave("TunnisteABCD (Article)")
	}
}

scenario "käyttäjä voi luoda bibtex tiedoston osasta listan tyhjennys ja ei varmistus", {
    given 'käsky luo bibtec tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "1", "TunnisteABCD", "","4","n","3", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")               
    	}
    when 'lisätty yksi tunniste bibtextiedostoon tulostettavaksi', {
                kysely.run()
	}
    then 'tulostetaan lisattyjen viitteiden lista ja se on tyhja' ,{
                file.delete()
		io.getPrints().shouldHave("TunnisteABCD (Article)")
	}
}

scenario "Käyttäjältä varmistetaan bibtex tiedoston ylikirjoitus", {
    given 'käsky luo bibtec tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "palaa","luo-bibtex-tiedosto", "testiABC", "y", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")               
    	}
    when 'käyttäjä hyväksyy ylikirjoituksen', {
                kysely.run()
	}
    then 'Bibtex tiedosto on luotu ylikirjoitettuna' ,{
                file.exists().shouldEqual(true)
                file.delete()
		
	}
}

scenario "Käyttäjältä varmistetaan bibtex tiedoston ylikirjoitus, mutta käyttäjä ei hyväksy sitä", {
    given 'käsky luo bibtec tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "palaa","luo-bibtex-tiedosto", "testiABC", "n", "testiABCD", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib") 
                file2 = new File("testiABCD.bib")              
    	}
    when 'käyttäjä ei hyväksy ylikirjoitusta vaan antaa uuden nimen', {
                kysely.run()
	}
    then 'Bibtex tiedosto on luotu ylikirjoitettuna' ,{
                file2.exists().shouldEqual(true)
                file.delete()
                file2.delete()
		
	}
}

scenario "Käyttäjältä varmistetaan bibtex tiedoston ylikirjoitus, mutta käyttäjä ei hyväksy sitä, antaa saman uudelleen", {
    given 'käsky luo bibtec tiedosto valittu', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","TunnisteABCD","luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1234", "luo-bibtex-tiedosto", "testiABC",
                     "palaa","luo-bibtex-tiedosto", "testiABC", "n", "testiABC")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")              
    	}
    when 'käyttäjä ei hyväksy ylikirjoitusta vaan antaa toistamiseen saman nimen', {
                kysely.run()
	}
    then 'Bibtex tiedosto on luotu ylikirjoitettuna' ,{
                file.delete()
                io.getPrints().shouldHave("Tiedoston luominen ei onnistu")
	}
}

scenario "Bibtex tiedoston luonnissa tyhjä syöte palauttaa ohjeen", {
    given 'käsky luo bibtex tiedosto valittu', {		
                io = new StubIO("luo-bibtex-tiedosto", "testiABC", "", "palaa")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")              
    	}
    when 'käyttäjä antaa tyhjän syötteen bibtex tiedoston luonnissa', {
                kysely.run()
	}
    then 'Ohje tulostetaan' ,{
                file.delete()
                io.getPrints().shouldHave("8 / komennot ")
	}
}

scenario "Bibtex tiedoston luonnissa tyhjä paluu palauttaa päävalikkoon", {
    given 'käsky luo bibtex tiedosto valittu', {		
                io = new StubIO("luo-bibtex-tiedosto", "testiABC", "palaa", "")
                kysely = new Kysely(io)	
                file = new File("testiABC.bib")              
    	}
    when 'käyttäjä antaa paluu syötteen', {
                kysely.run()
	}
    then 'ohjelma palaa päävalikkoon' ,{
                file.delete()
                io.getPrints().shouldHave("lopeta")
	}
}

scenario "käyttäjä ei voi tulostaa bibtex muodossa olevan viitteen jota ei ole olemassa", {
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

scenario "käyttäjä ei voi tulostaa bibtex muodossa olevan viitteen jota ei ole olemassa tapa2", {
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