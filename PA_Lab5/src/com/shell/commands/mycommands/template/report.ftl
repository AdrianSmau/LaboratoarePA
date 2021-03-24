<html>
<head>
    <title>HTML Report</title>
</head>
<body>
<h1>HTML Report</h1>

<p>${reportedCatalog.name}, at: ${reportedCatalog.path}</p>

<h2> Movies </h2>
<#list movies as movie>
    <h3>${movie_index + 1}. Name: ${movie.name}, ID: ${movie.id}, Path: ${movie.location}</h3>
    <ul>
        <li>Director: ${movie.director}</li>
        <li>Lead Actor/Actress: ${movie.leadRole}</li>
        <li>IMDB Score: ${movie.imdbScore}</li>
        <li>Release Year: ${movie.releaseYear}</li>
        <li>IMDB Link: ${movie.imdbLink}</li>
    </ul>
</#list>
<h2> Songs </h2>
<#list songs as song>
    <h3>${song_index + 1}. Name: ${song.name}, ID: ${song.id}, Path: ${song.location}</h3>
    <ul>
        <li>Main Artist(/s):
            <#list song.artist as artist>
                ${artist} -
            </#list>
            [END]
        </li>
        <li>Release Year: ${song.releaseYear}</li>
        <!-- <li>Is it on Spotify?: $ { song.isOnSpotify } </li> - Not able to report boolean values!... -->
        <li>YouTube Link: ${song.youtubeLink}</li>
    </ul>
</#list>
</body>
</html>