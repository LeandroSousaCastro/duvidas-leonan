# swapi-planets

Api com informações dos planetas da franquia de filmes Star Wars 

Lista de todos os planetas
GET: /planets

Lista de todos os planetas paginada
GET: /planets/page
Opicional: ?pageNo=1&limit=5&sortBy=id
Valor padrão: pageNo=0, limit=5 e sortBy=id


Busca palneta por ID
GET: /planets/{id}

Busca planeta por nome
GET: /planets/name/{name}

Remove planeta
DELETE: /planets/{id}

Cadastra planeta
POST: /planets
PayLoad:
{
    "name": "Geonosis",
    "climate": "temperate, arid",
    "terrain": "rock, desert, mountain, barren",
    "qtyAppearMovies": 1,
    "movies": [
      {
        "link": "http://swapi.dev/api/films/5/"
      }
    ]
}

Atualiza planeta
PUT: /planets
PayLoad:
{
    "id": "5f2b50ed9cd77d13f801f22c",
    "name": "Geonosis",
    "climate": "temperate, arid",
    "terrain": "rock, desert, mountain, barren",
    "qtyAppearMovies": 1,
    "movies": [
      {
        "link": "http://swapi.dev/api/films/5/"
      }
    ]
}
# duvidas-leonan
