grammar Java8;

INTEGER : ('0'..'9') | ('1'..'9') ('0'..'9')+;
COLOR : 'black' | 'random';
SHAPE : 'polygon' | 'circle' | 'square' | 'rectangle';

parse : expr;

expr : drawcmd | deletecmd | freehandcmd | resetcmd | exitcmd;

drawcmd : 'draw ' SHAPE ' ' INTEGER ',' INTEGER ',' INTEGER ',' COLOR;

deletecmd : 'delete ' INTEGER ',' INTEGER;

freehandcmd : 'freehand';

resetcmd : 'reset';

exitcmd : 'exit';

