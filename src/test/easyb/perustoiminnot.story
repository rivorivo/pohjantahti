import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'ohjelma sulkeutuu, menee päälle jne.'


scenario "ohjelma sulkeutuu", {

    given 'kysely käynnistetään', {		
     	io = new StubIO("lopeta","n")
            kysely = new Kysely(io)
    	}
    when 'lopetuskäskyt annettu', {
            kysely.run()
	}

    then 'ohjelma ei ole käynnissä' ,{
            kysely.getRunning().shouldHave("false")
	}
}


scenario "ohjelma menee päälle", {

    given 'ohjelma avataan', {		
     	io = new StubIO("")
    	}
    when 'kysely käynnistetty', {
            kysely = new Kysely(io)
	}

    then 'ohjelma on käynnissä' ,{
            kysely.getRunning().shouldHave("true")
	}
}

scenario "ohjelma tulostaa alkutiedot", {
    given 'tulosteet alustetaan',{
        io = new StubIO("")
        kysely = new Kysely(io)
    }
    when 'ohjelma avataan' ,{
        kysely.run()
    }
    then 'alkutulosteet tulostuvat',{
        io.getPrints().shouldHave("Käytettävissä olevat komennot:")
    }
}

