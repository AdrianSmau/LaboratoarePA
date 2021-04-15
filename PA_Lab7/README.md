Partile Obligatorie, Optionala si Bonus au fost implementate!<br>

Din clasa Main se pot schimba toate "variabilele" jocului : numarul de jucatori (dimensiunea vectorului players), numarul de "noduri" dupa care se creeaza Token-urile (index-ul dat obiectului gameBoard in constructor), <br>
Statusul fiecarui jucator (in constructorul fiecarui player, isBot reprezinta o valoare booleana care decide daca userul este bot sau player manual, iar parametrul boolean isSmart se aplica botilor si decide daca acesta implementeaza o maniera "smart" de a alege jetoanele!). Metoda de a alege smart este aceea de a lua jetoanele in ordine descrescatoare in concordanta cu valoarea lor, astfel blocand circuitele "valoroase" ale celorlalti playeri si castigand, la randul lui, valoare. Metode simpla de a alege jetoane este aceea de a lua urmatorul jeton available in ordine crescatoare.<br>

Deci, pentru a testa rularea pe input de dimensiuni mari, pur si simplu trebuie sa schimbam numarul de jetoane din constructorul gameBoard (se vor genera x * (x-1) jetoane, unde x este acest numar de noduri). <br>

In paralel cu mesajele din consola care arata statusul jocului la fiecare pas, o componenta grafica de tip JFrame se va deschide la inceputul jocului si va arata, la fiecare miscare a jucatorilor, statusul grafului cu jetoane (pe fiecare muchie a grafului, va afisa numele playerului care a luat jetonul precum si valoarea jetonului), alaturi de un JLabel care afiseaza Thread-ul curent!<br>
Cu ajutorul unui Thread de tip Daemon, se tine contorizarea timpului de rulare al jocului. Acesta poate fi setat tot din clasa Main, in variabila roundDuration din constructorul obiectului myStopwatch! <br>

Scorul se calculeaza astfel : la finalizarea jocului, se calculeaza pt fiecare player dimensiunea celui mai lung circuit inchis. Daca acesta are valoare peste 2, scorul va fi compus din suma valorilor jetoanelor alese + (numarul total de jetoane) ^ 2 * (dimensiunea celui mai lung circuit).<br>
Aditional (pentru diversificarea metodelor de scoring), daca acest circuit este hamiltonian (deci dimensiunea sa este egala cu numarul de jetoane extrase de catre player), scorul obtinut in etapa anterioara se inmulteste inca o data la dimensiunea acestui circuit maxim.

La final, se creeaza un leaderboard, se sorteaza dupa scor si se afiseaza in consola.
