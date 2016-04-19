import ohtumini.io.*
import viitteet.*
import ohtumini.*


description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi lisätä artikkeli-viitteen", {
    given 'käsky lisää uusi viite valittu', {
		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5","Testi6","Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste")
                app = new App(io)			
    	}
    when 'uusi viite- ja oikea artikkelityyppi -käskyt annettu', {
                app.run()
	}
    then 'viite luodaan' ,{
		io.getPrints().shouldHave("Viite luotu","1");
	}
}

