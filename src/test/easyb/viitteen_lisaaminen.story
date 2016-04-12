import ohtumini.*
import viitteet.*


description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi lisätä artikkeli-viitteen", {
    given 'käsky lisää uusi viite valittu'{
		artikkeli = new Article()                
			
    	}
    when 'pakolliset kentät täytetty validisti ja lisätty mahdolliset valinnaiset kentät'{
               
              artikkeli.lisaaTieto("Author","Janne Kallio")
              artikkeli.lisaaTieto("Title","Kirjani")
                artikkeli.lisaaTieto("Journal","Journal")
                artikkeli.lisaaTieto("Year","2016")
                artikkeli.lisaaTieto("Volume","12")
	}
    then 'avaimet löytyvät viitteestä'{
		artikkeli.getAvaimet()	
	}
}

