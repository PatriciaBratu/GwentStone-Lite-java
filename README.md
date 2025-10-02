

        Pentru implementarea jocului GwentStone Lite, am folosit clasele de baza, care sunt asemanatoare cu cele
din fileio, precum:
    -Cards-> care are campurile asemanatoare cu cele din CardInput
    -Deck->carae reprezinta pachetul de carti si caracteristicile sale(nr de pachete, respectiv nr decarti in pachet)
    -Hero->care este asemanator cu Cards, numai ca ii lipseste campul attackDamage
    -Player->care contine campurile mana si ne de carti in mana, dar si metodele corespunzatoare modificarii manei
    si metodele de adaugare si scoatere carti din mana
    -Action->este clasa care implementeaza comportamentul actctiunii din setul de jocuri pe care il primesc la input
    -Masa->este clasa care pastreaza sub forma unei liste de liste toate cartile plasate pe masa si gestioneaza,
    de asemenea, toate modificarile aduse acesteia prin stergerea sau adaugarea de noi carti; am ales implementarea
    cu liste pentru avatajele pe care le aduc listele la stergerea si adaugarea de noi elemente
    -StartGame->aceasta clasa are rolul de a accesa campurile necesare inceperii unui nou joc
    -PregatireMeci->este o clasa care initializeaza datele de inceput ale jocului; aceasta este instantiata de fiecare data
     cand se incepe un joc
    -interfata Afisez este alcatuita doar ddin metodele care au rolul de a afisa in JSON fie mesajele de eroare, fie
    rezultatele asteptate
    -DesfasurareMeci->este clasa in care se intampla practic testarea tuturor comenzilor, dar si executarea lor; acesta
    clasa mosteneste clasa StartGame deoarece are nevioe de toate datele de inceput ale jocului si implementeza interfata
    Afisez pentru a putea vedea acces la toate metodele necesare afisarii corecte
        Pentru implementarea jocului GwentStone Lite, am folosit clasele de baza, care sunt asemanatoare cu cele
din fileio, precum: -Cards-> care are campurile asemanatoare cu cele din CardInput -Deck->carae reprezinta pachetul de carti si 
caracteristicile sale(nr de pachete, respectiv nr decarti in pachet) -Hero->care este asemanator cu Cards, numai ca ii lipseste 
campul attackDamage -Player->care contine campurile mana si ne de carti in mana, dar si metodele corespunzatoare modificarii manei
 si metodele de adaugare si scoatere carti din mana -Action->este clasa care implementeaza comportamentul actctiunii din setul de 
 jocuri pe care il primesc la input -Masa->este clasa care pastreaza sub forma unei liste de liste toate cartile plasate pe masa si
  gestioneaza, de asemenea, toate modificarile aduse acesteia prin stergerea sau adaugarea de noi carti; am ales implementarea cu liste
   pentru avatajele pe care le aduc listele la stergerea si adaugarea de noi elemente -StartGame->aceasta clasa are rolul de a accesa campurile
    necesare inceperii unui nou joc -PregatireMeci->este o clasa care initializeaza datele de inceput ale jocului; aceasta este instantiata de
     fiecare data cand se incepe un joc -interfata Afisez este alcatuita doar ddin metodele care au rolul de a afisa in JSON fie mesajele de eroare, 
     fie rezultatele asteptate -DesfasurareMeci->este clasa in care se intampla practic testarea tuturor comenzilor, dar si executarea lor; acesta 
     clasa mosteneste clasa StartGame deoarece are nevioe de toate datele de inceput ale jocului si implementeza interfata Afisez pentru a putea avea 
     acces la toate metodele necesare afisarii corecte


    In functaia main, in primul rand preiau datele din input pe care le convertesc in date apartinand claselor mele
    prin constructorii special ganditi pentru acesta aciune, iar apoi pentru ficare joc initializez situatia de inceput a
    meciului si apoi parcrug fiecare comanda si o execut.