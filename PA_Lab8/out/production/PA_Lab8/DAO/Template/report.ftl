<html>
<head>
    <title>HTML Report</title>
</head>
<body>
<h1>HTML Report</h1>

<p>Metadata extracted from the CSV files at: ${CSV1Path} and ${CSV2Path}, <b><i>importing based on the first ${n} entries!</i></b></p>

<h2> Movies </h2>
<#list movies as movie>
    <h3>Movie ID: ${movie.id}, Movie Name: ${movie.title}</h3>
    <ul>
        <li>Release Date: ${movie.release_date!"null"}</li>
        <li>Duration: ${movie.duration} minutes</li>
        <li>IMDB Score: ${movie.score}</li>
    </ul>
</#list>

<h2> Actors </h2>
<#list actors as actor>
    <h3>Actor ID: ${actor.id}, Actor Name: ${actor.name}</h3>
    <ul>
        <li>Height: ${actor.height} cm</li>
        <li>Birth Date: ${actor.birth_date!"null"}</li>
        <li>Death Date: ${actor.death_date!"null"}</li>
    </ul>
</#list>

<h2> Directors </h2>
<#list directors as director>
    <ul>
        <li>Director ID: ${director.id}</li>
        <li>Director Name: ${director.name}</li>
    </ul>
</#list>

<h2> Genres </h2>
<#list genres as genre>
    <ul>
        <li>Genre ID: ${genre.id}</li>
        <li>Genre Name: ${genre.name}</li>
    </ul>
</#list>

</body>
</html>