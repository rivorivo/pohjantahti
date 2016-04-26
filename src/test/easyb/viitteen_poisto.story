import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'Käyttäjä voi poistaa lisätyn viitteen'

scenario "käyttäjä luo viitteen ja poistaa sen ", {
    given 'käyttäjä päättää tuhota uuden viitteen', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                        "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                        "poista-viite", "tunniste", "y",
                        "listaa-viitteet", "y")
                kysely = new Kysely(io)
    	}
    when 'Uusi viite luotu, ja samalla tunnuksella luotu viite tuhotaan', {
                kysely.run()
	}
    then 'Viitettä ei enää löydy viitelistasta' ,{
		io.getPrints().shouldHave("0 viitett")
	}
}

scenario "käyttäjä luo viitteen ja poistaa sen ", {
    given 'käyttäjä päättää tuhota uuden viitteen', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4", "Testi5",
                        "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste",
                        "poista-viite", "tunniste", "y",
                        "listaa-viitteet", "y")
                kysely = new Kysely(io)
    	}
    when 'Uusi viite luotu, ja samalla tunnuksella luotu viite tuhotaan', {
                kysely.run()
	}
    then 'Viitettä ei enää löydy viitelistasta' ,{
		io.getPrints().shouldHave("0 viitett")
	}
}

scenario "Käyttäjän luo monta viitettä ja poistaa kaksi", {
        given 'Käyttäjä luo neljä viitettä ja poistaa kaksi', {		
                io = new StubIO("luo-viite","article","Testi1","Testi2","Testi3","Testi4","Testi5",
                            "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste1",
                            "luo-viite","article","Testi1","Testi2","Testi3","Testi4","Testi5",
                            "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste2",
                            "luo-viite","article","Testi1","Testi2","Testi3","Testi4","Testi5",
                            "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste3",
                            "luo-viite","article","Testi1","Testi2","Testi3","Testi4","Testi5",
                            "Testi6","Testi7","Testi8","Testi9","Testi10","Tunniste4",
                            "poista-viite","tunniste2","y",
                            "poista-viite","tunniste3","y",
                            "listaa-viitteet","y")
                kysely = new Kysely(io)		
    	}
        when 'Luodaan viitteet ja poistetaan kaksi', {
                kysely.run()
	}
        then 'Oikeat viitteet jäävät jäljelle' ,{
		io.getPrints().shouldHave("2 viitett","Tunniste1 (Article")
	}
}