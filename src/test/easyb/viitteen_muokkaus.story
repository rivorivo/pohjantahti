import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'Käyttäjä voi muokata viitettä'

scenario "käyttäjä voi muokata viitettä", {
    given 'Uusi Viite luotu, sitä yritetään muokata', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "aseta-kentta", "Tunniste", "title", "uusi",
                    "tulosta-viite", "tunniste")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite luotu, sitä muokattu, katsotaan viitteen tiedot', {
                kysely.run()
	}
    then 'viite on muokattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Author: Testi1\nTitle: uusi\nJournal: Testi3")
	}
}

scenario "käyttäjä voi muokata viitettä lyhyellä notaatiolla", {
    given 'Uusi Viite luotu, sitä yritetään muokata', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","tt",
                    "aseta-kentta tt title uusi2",
                    "tulosta-viite", "tt")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite luotu, sitä muokattu, katsotaan viitteen tiedot', {
                kysely.run()
	}
    then 'viite on muokattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Author: Testi1\nTitle: uusi2\nJournal: Testi3")
	}
}

scenario "käyttäjä voi muokata viitettä lyhyen notaation ja pitkän notaation yhdisteellä", {
    given 'Uusi Viite luotu, sitä yritetään muokata', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "aseta-kentta Tunniste title", "uusi",
                    "tulosta-viite", "tunniste")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite luotu, sitä muokattu, katsotaan viitteen tiedot', {
                kysely.run()
	}
    then 'viite on muokattu ja näkyy oikein' ,{
		io.getPrints().shouldHave("Author: Testi1\nTitle: uusi\nJournal: Testi3")
	}
}

scenario "Olemassaoleva viite ei muokkaannu jos olematonta viitettä muokataan", {
    given 'Uusi Viite luotu, sitä yritetään muokata mutta tunniste typotetaan', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "aseta-kentta", "Tunniste2", "title", "uusi",
                    "tulosta-viite", "tunniste")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite luotu, sitä yritetty muokata, katsotaan viitteen tiedot', {
                kysely.run()
	}
    then 'viite säilynyt ennallaan, näkyy oikein, ja virheilmoitus annettu virheellisestä tunnisteesta' ,{
		io.getPrints().shouldHave("Tunnistetta ei ","Author: Testi1\nTitle: Testi2\nJournal: Testi3\nYear: Testi4")
	}
}

scenario "Olemassaoleva viite ei muokkaannu jos olematonta kenttää muokataan", {
    given 'Uusi Viite luotu, sitä yritetään muokata mutta kentan nimi typotetaan', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "aseta-kentta", "Tunniste", "otsikko", "uusi",
                    "tulosta-viite", "tunniste")
                kysely = new Kysely(io)			
    	}
    when 'uusi viite luotu, sitä yritetty muokata, katsotaan viitteen tiedot', {
                kysely.run()
	}
    then 'viite säilynyt ennallaan, näkyy oikein, ja virheilmoitus annettu virheellisestä kentästä' ,{
		io.getPrints().shouldHave("Asettaminen ei onnistunut, ","Author: Testi1\nTitle: Testi2\nJournal: Testi3\nYear: Testi4\nVolume: Testi5\nNumber: Testi6\nPages: Testi7\nMonth: Testi8\nNote: Testi9\nKey: Testi10")
	}
}

scenario "Käyttäjälle näytetään edellinen arvo muokatessa kenttien arvoja", {
    given 'Uusi Viite luotu, sitä muokataan ja annetaan oikea tunniste ja kenttä', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                    "aseta-kentta", "Tunniste", "Author")
                kysely = new Kysely(io)			
    	}
    when 'Uusi viite luotu, sitä muokataan', {
                kysely.run()
	}
    then 'viitteen edellinen arvo tulostetaan nähtäville' ,{
		io.getPrints().shouldHave(" Author arvo on \"Testi1\"")
	}
}
scenario "Käyttäjä voi muokata Author/Editor kentän avainta antamalla kentän nimeksi Editor", {
    given 'Uusi kirjaviite luotu, siitä asetettu Author-kenttä, ja Editor-kenttää muokattu', {		
                io = new StubIO("luo-viite","book","Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10", "Testi11","Tunniste",
                    "aseta-kentta", "Tunniste", "Editor", "uusi",
                    "tulosta-bibtex", "Tunniste")
                kysely = new Kysely(io)	
    	}
    when 'Uusi viite luotu, sitä muokataan', {
                kysely.run()
	}
    then 'Editor-kentän tulisi olla oikeanlainen' ,{
		io.getPrints().shouldHave("Editor = {uusi}")
	}
}

scenario "Käyttäjä voi muokata Author/Editor kentän avainta antamalla kentän nimeksi Author/Editor", {
    given 'Uusi kirjaviite luotu, siitä asetettu Editor-kenttä, ja Editor-kenttää muokattu', {		
                io = new StubIO("luo-viite","book", "", "Testi1","Testi2","Testi3","Testi4", "Testi5",
                    "Testi6","Testi7","Testi8","Testi9","Testi10", "Testi11","Tunniste",
                    "aseta-kentta", "Tunniste", "Author/Editor", "uusi",
                    "tulosta-bibtex", "Tunniste")
                kysely = new Kysely(io)	
    	}
    when 'Uusi viite luotu, sitä muokataan', {
                kysely.run()
	}
    then 'Editor-kentän tulisi olla oikeanlainen' ,{
		io.getPrints().shouldHave("Editor = {uusi}")
	}
}