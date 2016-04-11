import ohtumini.*


description 'Käyttäjä voi lisätä viitten'

scenario "käyttäjä voi lisätä artikkeli viitteen", {
    given 'käsky lisää uusi viite valittu'
    when 'pakolliset kentät täytetty validisti ja lisätty mahdolliset valinnaiset kentät'
    then 'viite lisätään tulostettaviin'
}
