import viitteet.*
import ohtumini.*
import ohtumini.UI.*
import ohtumini.io.*

description 'Käyttöliittymä kyselee viitteen tiedot'


scenario "kysely kysyy viitteen tyypin",{
    given 'uusi kysely avataan', {
         io = new StubIO("luo-viite")
         kysely = new Kysely(io)
    }
    when 'viitteen luomis-käsky valittu', {
         kysely.run()
    }
    then 'kysytään viitteen tyyppi', {
        io.getPrints().shouldHave("Anna viitteen tyyppi")
    }
}
scenario "kysely kysyy ensimmäistä kenttää",{
    given 'uusi kysely avataan', {
         io = new StubIO("luo-viite","book")
         kysely = new Kysely(io)
    }
    when 'viitetyyppi valittu', {
         kysely.run()
    }
    then 'kysytään ensimmäinen parametri', {
        io.getPrints().shouldHave("Anna kentta Author/Editor*:")
    }
}
scenario "kysely kysyy toista kenttää",{
    given 'uusi kysely avataan', {
         io = new StubIO("luo-viite","article","kirjoittaja")
         kysely = new Kysely(io)
    }
    when 'viitetyyppi valittu ja ensimmäinen parametri annettu', {
         kysely.run()
    }
    then 'kysytään toinen parametri', {
        io.getPrints().shouldHave("Title*:")
    }
}