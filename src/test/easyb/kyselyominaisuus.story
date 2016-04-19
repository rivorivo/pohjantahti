import ohtumini.io.*
import viitteet.*
import ohtumini.*
import ohtumini.UI.*


description 'Käyttöliittymä kyselee viitteen tiedot'


scenario "kysely kysyy viitteen tyypin",{
    given 'uusi kysely avataan', {
         io = new StubIO("luo-viite")
         kysely = new Kysely(io)
    }
    when 'käsky valittu', {
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
    when 'käsky valittu', {
         kysely.run()
    }
    then 'kysytään viitteen tyyppi', {
        io.getPrints().shouldHave("Anna kenttä Author/Editor*:")
    }
}
scenario "kysely kysyy toista kenttää",{
    given 'uusi kysely avataan', {
         io = new StubIO("luo-viite","article","kirjoittaja")
         kysely = new Kysely(io)
    }
    when 'käsky valittu', {
         kysely.run()
    }
    then 'kysytään viitteen tyyppi', {
        io.getPrints().shouldHave("Anna kenttä Title*:")
    }
}