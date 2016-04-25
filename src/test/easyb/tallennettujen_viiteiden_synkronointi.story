import viitteet.*
import ohtumini.*
import ohtumini.UI.*
import ohtumini.io.*
import java.io.File


description 'Viitelistat voidaan tallentaa ja tallennus ladata (synkronointi)'

scenario "Kayttaja tallentaa viitelistan ilman nimea",{
    given 'uusi kysely avataan', {
         io = new StubIO("tallenna" ,"")
         kysely = new Kysely(io)
    }
    when 'viitte listan tallennus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {
        io.getPrints().shouldHave("Tallennus onnistui!")
    }
}

scenario "Kayttaja tallentaa viitelistan nimella",{
    given 'uusi kysely avataan', {
         io = new StubIO("tallenna", "testi")
         kysely = new Kysely(io)
    }
    when 'viitte listan tallennus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {
        io.getPrints().shouldHave("Tallennus onnistui!")
    }
}

scenario "Kayttaja tallentaa viitelistan ylikirjoittaen",{
    given 'uusi kysely avataan', {
         io = new StubIO("tallenna", "testi", "y")
         kysely = new Kysely(io)
    }
    when 'viitte listan tallennus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {
        io.getPrints().shouldHave("Tallennus onnistui!")
    }
}

scenario "Kayttaja tallentaa viitelistan ylikirjoittamatta",{
    given 'uusi kysely avataan', {
         io = new StubIO("tallenna", "testi", "n", "testi2")
         kysely = new Kysely(io)
         file = new File("testi.rflist")
         file2 = new File("testi2.rflist")
    }
    when 'viitte listan tallennus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {
        file.delete()
        file2.delete()
        io.getPrints().shouldHave("Tallennus onnistui!")
    }
}

scenario "Kayttaja lataa viitelistan",{
    given 'uusi kysely avataan', {
         io = new StubIO("lataa", "viitelista")
         kysely = new Kysely(io)
    }
    when 'viitte listan lataus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {        
        io.getPrints().shouldHave("Tallennuksen lataus onnistui!")
    }
}

scenario "Kayttaja lataa epakelvollanimella viitelistan",{
    given 'uusi kysely avataan', {
         io = new StubIO("lataa", "epakelpo")
         kysely = new Kysely(io)
    }
    when 'viitte listan lataus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {        
        io.getPrints().shouldHave("Viitteitten lataaminen ei onnistunut")
    }
}

scenario "Kayttaja yrittaa ladata tyhjasta kansiosta",{
    given 'uusi kysely avataan', {
         io = new StubIO("lataa", "epakelpo", "", "")
         kysely = new Kysely(io)
         file = new File("viitelista.rflist")
         file.delete()
    }
    when 'viitte listan lataus valittu ja hyvaksytty', {
         kysely.run()
    }
    then 'ilmoitetaan tallennuksen onnistuneen', {        
        io.getPrints().shouldHave("Viitteitten lataaminen ei onnistunut")
    }
}