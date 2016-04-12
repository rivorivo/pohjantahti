import ohtumini.*
import viitteet.*
import io.*


description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi lisätä artikkeli-viitteen", {
    given 'käsky lisää uusi viite valittu', {
		
                io = new StubIO("luo-viite","article")
                app = new App(io)			
    	}
    when 'uusi viite- ja oikea artikkelityyppi -käskyt annettu', {
                app.run()
	}
    then 'viite luodaan' ,{
		io.getPrints().shouldHave("1");	
	}
}

