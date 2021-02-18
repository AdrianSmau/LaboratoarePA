# PA - Laborator 1

Pentru partea obligatorie, am urmarit cerintele si am afisat la fiecare pas ce se cerea.

Pentru partea optionala, am inceput prin a valida input-ul (a verifica daca este de tip int), apoi am pornit cronometrul pentru a gasi timpul de executie. Acesta urmeaza sa fie inchis cand terminam de generat arborele partial gasit mai tarziu.
Am creat, apoi, o matrice random de adiacenta a unui graf, urmand a o afisa. Am implementat o clasa Graph, ce contine doua metode : DFS(pentru a gasi componentele conexe) si TreeDFS (pentru a gasi arborele partial si pentru a crea un vector cu nodurile vizitate, in ordine).
Metoda ConnectedComponents din aceasta clasa genereaza, intai, numarul de componente conexe si le afiseaza. Daca avem o singura componenta, metoda creeaza apoi arborele partial. Memoreaza intr-un vector nodurile vizitate, apoi genereaza matricea arborelui prin a-i marca muchiile intr-o matrice blank(intre fiecare 2 noduri consecutive parcurse in arborele partial, marcheaza muchiile dintre acestea in matricea noua), apoi afiseaza matricea arborelui.

Pentru partea de bonus, am implementat o noua clasa RandTree, cu metoda generateTree. Aceasta primeste ca parametru un numar, ce reprezinta numarul maxim de fii pe care ii poate avea un nod oarecare (intre 0 si parametrul respectiv). Avem doi indici ai clasei, level si index. Level memoreaza nivelul actual(cu cat ne afundam mai tare in fii, cu atat creste nivelul, apoi cand ne intoarcem nivelul scade), si value memoreaza valoarea nodului actual(care se incrementeaza la fiecare nod nou adaugat).
Cu ajutorul functiei nextInt din java.util.Random, generam un numar random de fii intre 0 si nr pornind de la nodul-radacina si, petntru fiecare fiu implementat, repetam procesul.
