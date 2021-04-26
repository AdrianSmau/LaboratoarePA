Partile obligatorie, optionala si bonus au fost implementate!<br>

Pentru partile optionala si bonus, tot ce am facut este explicat in output-ul din rularea Main-ului, la fel si la generarea Playlist-ului la partea de bonus!<br>

Pachetele au fost numite reprezentativ, astfel incat sa fie clar ce am facut pentru fiecare punct in parte!<br>

Pentru partea de bonus, am implementat singur un algoritm, in care mapez Directorii la Filme (de fapt, generez un numar predefinit de Directori in baza de date, precum si un numar variabil de Movies, ale fiecarui Director)!<br>

Doua filme sunt related daca au acelasi director. Astfel, un Director are mai multe entry-uri in tabela din baza de date (de exemplu, 'Bonus Sample Director 1(0)', 'Bonus Sample Director 1(1)', 'Bonus Sample Director 1(2)', etc...), fiecare cu cheia straina referitoare la un film unic.<br>

Generarea maparii se face per Director (de exemplu, 'Bonus Sample Director 1', 'Bonus Sample Director 2', etc...), cu o lista de filme.<br>

La final, este afisat Playlist-ul pentru fiecare zi in care este posibila vizionarea a exact doua filme!...
