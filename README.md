# Obligatorio-ProgII
Obligatorio Programación II

Juan Martin Muñoz
Joaquin Martirena

Descripción procesos de carga

Para cargar los archivos del CSV usamos el BufferedReader. Cada línea del CSV representa una canción distinta. Ignoramos la primera línea del archivo ya que esta son los títulos de los datos sobre las canciones. Reemplazamos todas las barras dobles por el símbolo ∆ y el punto y coma por vacío, para facilitar la separación entre los datos de cada canción. Hicimos un arreglo llamado columnas, en el cual cada posición del arreglo representa un dato de la canción, que fueron separados de una línea en el CSV con el split(∆). Luego creamos la lista artistas y el arreglo nombreArtistas. Este arreglo contiene todos los artistas de cada canción (muchas canciones son de varios artistas y no uno) y los separamos por la coma a cada artista individual que participa en una misma canción y los agregamos a la lista de artistas. Luego creamos el objeto canción con los datos obtenidos del CSV que fueron separados anteriormente. Finalmente, se agrega la canción a nuestros hash. 

Descripción realización de reportes
Descripción de nuestras funciones:

Top10: Le pedimos al usuario la fecha y el país del cual quiere el top 10. Concatenamos la fecha y el país para usar ese String como la key y con esta key buscamos las canciones en el HashDateCountry, hecho especialmente para esta función, y las ponemos en una lista “songsList”. Si la lista de canciones está vacía se devuelve la excepción ElementNotFound. De lo contrario se crea una nueva lista “lista”. Con un for se recorre “songsList” y se agregan las primeras 10 canciones de “songslist” a la lista “lista”, y se imprime de cada posición de la lista el nombre de la canción, el Rank y los artistas. Nótese que dentro de este for tenemos otro for para imprimir los artistas de las canciones ya que una canción puede tener varios artistas. 

Top5inTop50: Se le pide la fecha al usuario y esta se usa para buscar las canciones de esta fecha en el hash “hashDate” y se guardan en la lista “lista”. Si la lista es nula se lanza la excepción ElementNotFoundException. Luego se crea el Hash “hashSong”. Se recorre la lista “lista” y se crea la canción “song” por cada posición de la “lista”. Si el “hashSong” no contiene esta canción, se agrega, pero si ya lo contiene se busca esta cancion por su nombre en “hashSong” y se le agrega 1 al counter de la canción. Luego se crea un heap en donde se insertan todas las canciones del hash “hashSong”. Esto hace que las canciones queden ordenadas en el heap en base al counter, siendo la canción con el counter mayor la primera. Finalmente se imprimen el nombre y el counter de las primeras 5 canciones en el heap.

Top7inTop50: Se le pide al usuario la fecha de inicio y la fecha de fin. La fecha inicio se almacena en “current” y se crea el hash “artists”. Usamos un while para ir recorriendo día por día desde current hasta llegar a la fecha fin. Dentro de este while se busca en el hashDate las canciones de la fecha actual, usando “current” como key, las cuales se almacenan en la lista “listaHash”. Si la lista es null se suma un día a current y por ende comienza de nuevo el while. De lo contrario se recorre la “listaHash” para obtener la lista de artistas de cada canción (“listaArtista”) y luego se recorre la “listaArtista” para obtener cada artista individualmente. Si el hash “artists” no contiene al artista, se agrega al artista a dicho hash. Si lo contiene, se le suma uno al Rank del artista. Luego recorrido todos los días, se crea el heap “heap”. Se agrega al heap todos los aristas del hash “artists”, ordenandose los artistas en el heap por Rank, siendo el que tiene mayor Rank el primero. Luego se crea la lista “lista”, en donde se agregan los primeros 7 aristas ya ordenados por Rank del heap “heap” y se imprime el nombre del artista y el Rank (cantidad de ocurrencias) de dicho artista. 

OcurrenciesArtistinTop50: Le pedimos al usuario la fecha y el artista del que se quiere ver cuantas veces aparece en los Top 50 de todos los países. Concatenamos la fecha y el nombre del artista para usar ese String como la key y con esta key buscamos en nuestro hashArtistDate las canciones de este artista que estén en el top 50, que se guardan en la lista “songs”. Si la lista está vacía se devuelve ElementNotFoundException. De lo contrario se imprime el tamaño de la lista “songs”.

SongsBetweenTempoAndDate: Le pedimos al usuario el tempo máximo, el mínimo, la fecha de inicio y la fecha de fin. Si el tempo minimo es mas grande que el tempo máximo o la fecha de inicio es después que la fecha de fin lanzamos la excepción WrongOrder. De lo contrario guardamos la fecha de inicio en “current” y creamos la lista “lista”, en donde se guardaran todas las canciones entre la fecha buscada y con el tempo buscado. Usamos un while para ir recorriendo día por día desde current hasta llegar a la fecha fin. Dentro del while se crea la lista “listaSongs” en la cual se guardan las canciones de un tal dia, que se buscan en el HashDate, usando como key la fecha current. Se recorre la lista “listaSongs” y si la canción de esta lista tiene un tempo dentro del tempo que se está buscando, la cancion se agrega a la lista “lista”. Luego se le suma a “current” un día y se repite el proceso. Luego recorridas todas las canciones por todos los días, se imprime el tamaño de la lista “lista”.


Eficiencia de la aplicación

Cantidad de memoria RAM consumida:
Carga CSV: Entre 400 y 1300 megabytes
Top10: Entre 23000 y 30000 bytes
Top5inTop50: entre 22000 y 26000 bytes
Top7inTop50: Entre 400 y 800 bytes
OcurrenciesArtistinTop50: Entre 15000 y 18000 bytes
SongsBetweenTempoAndDate: Entre 65000 y 70000 bytes

Tiempo de ejecución promedio:
Carga CSV: Entre 3200 y 3500 milisegundos
Top10: Entre 4 y 6 milisegundos
Top5inTop50: Entre 30 y 150 milisegundos
Top7inTop50: Entre 7000 y 9000 milisegundos
OcurrenciesArtistinTop50: Entre 2 y 7 milisegundos
SongsBetweenTempoAndDate: Entre 8500 y 11500 milisegundos

Decisiones tomadas

Para tomar las decisiones, primero observamos detalladamente cómo estaba armado el input. El archivo .csv tenía canciones ordenadas según la fecha y el país. Además las canciones que estaban en el archivo csv todas tenían un ranking dentro del top 50. Está información fue muy importante a la hora de tomar decisiones.

Una vez que entendimos como leer el archivo, cada vez que leíamos una línea del archivo .csv, creamos una canción con esa información y la guardabamos en distintas estructuras. Estas estructuras las elegimos teniendo en cuenta cada función que íbamos a realizar. Las estructuras que utilizamos fueron estos Hashes.

hashDateCountry:
	Este hash lo elegimos especialmente para la primera función que pedía el top50 de un país en una fecha dada. Al tener está estructura, el usuario ingresa una fecha y un país, el programa concatena estos string, formando una key. De está manera obtenemos el top 50 con un bigO de O(1).

hashDate:
	Este hash lo utilizamos en varias funciones, en todas las que precisamos las canciones de una fecha. Cuando era un rango, lo recorríamos con las funciones de LocalDate.

hashArtistDate:
	Está estructura la utilizamos en la tercera función para obtener todas las canciones de un artista en una fecha, y solo devolvemos el tamaño de esa lista por lo que fue bastante útil.

Otra decisión que tomamos fue de implementar un heap en la hora de ordenar por ejemplo las ocurrencias de un artista. Consideramos que era la mejor opción, con menor costo.

