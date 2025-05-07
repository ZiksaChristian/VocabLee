package com.example.vocablee.BaseSQLITE

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.vocablee.BaseSQLITE.HelperDatos.Companion.COLUMN_ID
import com.example.vocablee.BaseSQLITE.HelperDatos.Companion.COLUMN_RECORD
import com.example.vocablee.BaseSQLITE.HelperDatos.Companion.LIBROS

data class Book(
    val id: Long,
    val title: String,
    val lectura: String,
    val rating: Int // Añadir el campo de calificación
)

class Books(context: Context) {

    private val dbHelper: HelperDatos = HelperDatos(context)

    fun agregarLibro(titulo: String, descripcion: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(HelperDatos.COLUMN_TITLE, titulo)
            put(HelperDatos.COLUMN_LECTURA, descripcion)
        }
        val newRowId = db.insert(HelperDatos.LIBROS, null, values)
        db.close()
        return newRowId
    }

    fun obtenerTodosLosLibros(): List<Book> {
        agregarLibrosIniciales() // Supongo que esta función agrega libros iniciales si no existen
        val libros = mutableListOf<Book>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            HelperDatos.LIBROS, null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(HelperDatos.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(HelperDatos.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(HelperDatos.COLUMN_LECTURA))
                val rating = getInt(getColumnIndexOrThrow(HelperDatos.COLUMN_RECORD))
                libros.add(Book(id, title, description, rating)) // Añadir el parámetro 'rating'
            }
        }
        cursor.close()
        db.close()

        Log.d("Books", "Libros obtenidos: $libros") // Registro de depuración para verificar los libros obtenidos

        return libros
    }

    fun agregarLibrosIniciales() {
        if (librosInicialesYaAgregados()) {
            return  // Si los libros iniciales ya están agregados, no hacer nada
        }

        val librosIniciales = listOf(
            Book(0, "Venus",
                "En la tranquila noche, mis nostalgias amargas sufría.\n" +
                        "En busca de quietud bajé al fresco y callado jardín.\n" +
                        "En el obscuro cielo Venus bella temblando lucía,\n" +
                        "como incrustado en ébano un dorado y divino jazmín.\n" +
                        "\n" +
                        "A mi alma enamorada, una reina oriental parecía,\n" +
                        "que esperaba a su amante bajo el techo de su camarín,\n" +
                        "o que, llevada en hombros, la profunda extensión recorría,\n" +
                        "triunfante y luminosa, recostada sobre un palanquín.\n" +
                        "\n" +
                        "\"¡Oh, reina rubia! -díjele, mi alma quiere dejar su crisálida\n" +
                        "y volar hacia a ti, y tus labios de fuego besar;\n" +
                        "y flotar en el nimbo que derrama en tu frente luz pálida,\n" +
                        "\n" +
                        "y en siderales éxtasis no dejarte un momento de amar\".\n" +
                        "El aire de la noche refrescaba la atmósfera cálida.\n" +
                        "Venus, desde el abismo, me miraba con triste mirar.",5),
            Book(0, "Que el amor no admite cuerdas reflexiones",
                "Señora, Amor es violento,\n" +
                        "y cuando nos transfigura\n" +
                        "nos enciende el pensamiento\n" +
                        "la locura.\n" +
                        "\n" +
                        "No pidas paz a mis brazos\n" +
                        "que a los tuyos tienen presos:\n" +
                        "son de guerra mis abrazos\n" +
                        "y son de incendio mis besos;\n" +
                        "y sería vano intento\n" +
                        "el tornar mi mente obscura\n" +
                        "si me enciende el pensamiento\n" +
                        "la locura.\n" +
                        "\n" +
                        "Clara está la mente mía\n" +
                        "de llamas de amor, señora,\n" +
                        "como la tienda del día\n" +
                        "o el palacio de la aurora.\n" +
                        "Y el perfume de tu ungüento\n" +
                        "te persigue mi ventura,\n" +
                        "y me enciende el pensamiento\n" +
                        "la locura.\n" +
                        "\n" +
                        "Mi gozo tu paladar\n" +
                        "rico panal conceptúa,\n" +
                        "como en el santo Cantar:\n" +
                        "Mel et lac sub lingua tua*.\n" +
                        "La delicia de tu aliento\n" +
                        "en tan fino vaso apura,\n" +
                        "y me enciende el pensamiento\n" +
                        "la locura.", 5),
            Book(0, "Sonatina", "La princesa está triste… ¿qué tendrá la princesa?\n" +
                    "Los suspiros se escapan de su boca de fresa,\n" +
                    "que ha perdido la risa, que ha perdido el color.\n" +
                    "La princesa está pálida en su silla de oro,\n" +
                    "está mudo el teclado de su clave de oro;\n" +
                    "y en un vaso olvidado se desmaya una flor.\n" +
                    "\n" +
                    "El jardín puebla el triunfo de los pavos-reales.\n" +
                    "Parlanchina, la dueña dice cosas banales,\n" +
                    "y, vestido de rojo, piruetea el bufón.\n" +
                    "La princesa no ríe, la princesa no siente;\n" +
                    "la princesa persigue por el cielo de Oriente\n" +
                    "la libélula vaga de una vaga ilusión.\n" +
                    "\n" +
                    "¿Piensa acaso en el príncipe del Golconsa o de China,\n" +
                    "o en el que ha detenido su carroza argentina\n" +
                    "para ver de sus ojos la dulzura de luz?\n" +
                    "¿O en el rey de las Islas de las Rosas fragantes,\n" +
                    "o en el que es soberano de los claros diamantes,\n" +
                    "o en el dueño orgulloso de las perlas de Ormuz?\n" +
                    "\n" +
                    "¡Ay! La pobre princesa de la boca de rosa\n" +
                    "quiere ser golondrina, quiere ser mariposa,\n" +
                    "tener alas ligeras, bajo el cielo volar,\n" +
                    "ir al sol por la escala luminosa de un rayo,\n" +
                    "saludar a los lirios con los versos de mayo,\n" +
                    "o perderse en el viento sobre el trueno del mar.\n" +
                    "\n" +
                    "Ya no quiere el palacio, ni la rueca de plata,\n" +
                    "ni el halcón encantado, ni el bufón escarlata,\n" +
                    "ni los cisnes unánimes en el lago de azur.\n" +
                    "Y están tristes las flores por la flor de la corte;\n" +
                    "los jazmines de Oriente, los nulumbos del Norte,\n" +
                    "de Occidente las dalias y las rosas del Sur.\n" +
                    "\n" +
                    "¡Pobrecita princesa de los ojos azules!\n" +
                    "Está presa en sus oros, está presa en sus tules,\n" +
                    "en la jaula de mármol del palacio real,\n" +
                    "el palacio soberbio que vigilan los guardas,\n" +
                    "que custodian cien negros con sus cien alabardas,\n" +
                    "un lebrel que no duerme y un dragón colosal.\n" +
                    "\n" +
                    "¡Oh quién fuera hipsipila que dejó la crisálida!\n" +
                    "La princesa está triste. La princesa está pálida…\n" +
                    "¡Oh visión adorada de oro, rosa y marfil!\n" +
                    "¡Quién volara a la tierra donde un príncipe existe\n" +
                    "La princesa está pálida. La princesa está triste…\n" +
                    "más brillante que el alba, más hermoso que abril!\n" +
                    "\n" +
                    "¡Calla, calla, princesa dice el hada madrina,\n" +
                    "en caballo con alas, hacia acá se encamina,\n" +
                    "en el cinto la espada y en la mano el azor,\n" +
                    "el feliz caballero que te adora sin verte,\n" +
                    "y que llega de lejos, vencedor de la Muerte ,\n" +
                    "a encenderte los labios con su beso de amor!",5),
            Book(0, "A Margarita Debayle",
                "Margarita está linda la mar,\n" +
                        "y el viento,\n" +
                        "lleva esencia sutil de azahar;\n" +
                        "yo siento\n" +
                        "en el alma una alondra cantar;\n" +
                        "tu acento:\n" +
                        "Margarita, te voy a contar\n" +
                        "un cuento:\n" +
                        "\n" +
                        "Esto era un rey que tenía\n" +
                        "un palacio de diamantes,\n" +
                        "una tienda hecha de día\n" +
                        "y un rebaño de elefantes,\n" +
                        "un kiosko de malaquita,\n" +
                        "un gran manto de tisú,\n" +
                        "y una gentil princesita,\n" +
                        "tan bonita,\n" +
                        "Margarita,\n" +
                        "tan bonita, como tú.\n" +
                        "\n" +
                        "Una tarde, la princesa\n" +
                        "vio una estrella aparecer;\n" +
                        "la princesa era traviesa\n" +
                        "y la quiso ir a coger.\n" +
                        "\n" +
                        "La quería para hacerla\n" +
                        "decorar un prendedor,\n" +
                        "con un verso y una perla\n" +
                        "y una pluma y una flor.\n" +
                        "\n" +
                        "Las princesas primorosas\n" +
                        "se parecen mucho a ti:\n" +
                        "cortan lirios, cortan rosas,\n" +
                        "cortan astros. Son así.\n" +
                        "\n" +
                        "Pues se fue la niña bella,\n" +
                        "bajo el cielo y sobre el mar,\n" +
                        "a cortar la blanca estrella\n" +
                        "que la hacía suspirar.\n" +
                        "\n" +
                        "Y siguió camino arriba,\n" +
                        "por la luna y más allá;\n" +
                        "más lo malo es que ella iba\n" +
                        "sin permiso de papá.\n" +
                        "\n" +
                        "Cuando estuvo ya de vuelta\n" +
                        "de los parques del Señor,\n" +
                        "se miraba toda envuelta\n" +
                        "en un dulce resplandor.\n" +
                        "\n" +
                        "Y el rey dijo: «¿Qué te has hecho?\n" +
                        "\n" +
                        "te he buscado y no te hallé;\n" +
                        "y ¿qué tienes en el pecho\n" +
                        "que encendido se te ve?».\n" +
                        "\n" +
                        "La princesa no mentía.\n" +
                        "Y así, dijo la verdad:\n" +
                        "«Fui a cortar la estrella mía\n" +
                        "a la azul inmensidad».\n" +
                        "\n" +
                        "Y el rey clama: «¿No te he dicho\n" +
                        "que el azul no hay que cortar?\n" +
                        "¡Qué locura!, ¡Qué capricho!...\n" +
                        "El Señor se va a enojar».\n" +
                        "\n" +
                        "Y ella dice: «No hubo intento;\n" +
                        "yo me fui no sé por qué.\n" +
                        "Por las olas por el viento\n" +
                        "fui a la estrella y la corté».\n" +
                        "\n" +
                        "Y el papá dice enojado:\n" +
                        "«Un castigo has de tener:\n" +
                        "vuelve al cielo y lo robado\n" +
                        "vas ahora a devolver».\n" +
                        "\n" +
                        "La princesa se entristece\n" +
                        "por su dulce flor de luz,\n" +
                        "cuando entonces aparece\n" +
                        "sonriendo el Buen Jesús.\n" +
                        "\n" +
                        "Y así dice: «En mis campiñas\n" +
                        "esa rosa le ofrecí;\n" +
                        "son mis flores de las niñas\n" +
                        "que al soñar piensan en mí».\n" +
                        "\n" +
                        "Viste el rey pompas brillantes,\n" +
                        "y luego hace desfilar\n" +
                        "cuatrocientos elefantes\n" +
                        "a la orilla de la mar.\n" +
                        "\n" +
                        "La princesita está bella,\n" +
                        "pues ya tiene el prendedor\n" +
                        "en que lucen, con la estrella,\n" +
                        "verso, perla, pluma y flor.\n" +
                        "\n" +
                        "Margarita, está linda la mar,\n" +
                        "y el viento\n" +
                        "lleva esencia sutil de azahar:\n" +
                        "tu aliento.\n" +
                        "\n" +
                        "Ya que lejos de mí vas a estar,\n" +
                        "guarda, niña, un gentil pensamiento\n" +
                        "al que un día te quiso contar\n" +
                        "un cuento.", 5),
            Book(0, "Yo persigo una forma",
                "Yo persigo una forma que no encuentra mi estilo,\n" +
                        "botón de pensamiento que busca ser la rosa;\n" +
                        "se anuncia con un beso que en mis labios se posa\n" +
                        "el abrazo imposible de la Venus de Milo.\n" +
                        "\n" +
                        "Adornan verdes palmas el blanco peristilo;\n" +
                        "los astros me han predicho la visión de la Diosa;\n" +
                        "y en mi alma reposa la luz como reposa\n" +
                        "el ave de la luna sobre un lago tranquilo.\n" +
                        "\n" +
                        "Y no hallo sino la palabra que huye,\n" +
                        "la iniciación melódica que de la flauta fluye\n" +
                        "y la barca del sueño que en el espacio boga;\n" +
                        "\n" +
                        "y bajo la ventana de mi Bella-Durmiente,\n" +
                        "el sollozo continuo del chorro de la fuente\n" +
                        "y el cuello del gran cisne blanco que me interroga.", 5),
            Book(0, "A Colón",
                "¡Desgraciado Almirante! Tu pobre América,\n" +
                        "tu india virgen y hermosa de sangre cálida,\n" +
                        "la perla de tus sueños, es una histérica\n" +
                        "de convulsivos nervios y frente pálida.\n" +
                        "\n" +
                        "Un desastroso espíritu posee tu tierra:\n" +
                        "donde la tribu unida blandió sus mazas,\n" +
                        "hoy se enciende entre hermanos perpetua guerra,\n" +
                        "se hieren y destrozan las mismas razas.\n" +
                        "\n" +
                        "Al ídolo de piedra reemplaza ahora\n" +
                        "el ídolo de carne que se entroniza,\n" +
                        "y cada día alumbra la blanca aurora\n" +
                        "en los campos fraternos sangre y ceniza.\n" +
                        "\n" +
                        "Desdeñando a los reyes nos dimos leyes\n" +
                        "al son de los cañones y los clarines,\n" +
                        "y hoy al favor siniestro de negros reyes\n" +
                        "fraternizan los Judas con los Caínes.\n" +
                        "\n" +
                        "Bebiendo la esparcida savia francesa\n" +
                        "con nuestra boca indígena semiespañola,\n" +
                        "día a día cantamos la Marsellesa\n" +
                        "para acabar danzando la Carmañola.\n" +
                        "\n" +
                        "Las ambiciones pérfidas no tienen diques,\n" +
                        "soñadas libertades yacen deshechas.\n" +
                        "¡Eso no hicieron nunca nuestros caciques,\n" +
                        "a quienes las montañas daban las flechas! .\n" +
                        "\n" +
                        "Ellos eran soberbios, leales y francos,\n" +
                        "ceñidas las cabezas de raras plumas;\n" +
                        "¡ojalá hubieran sido los hombres blancos\n" +
                        "como los Atahualpas y Moctezumas!\n" +
                        "\n" +
                        "Cuando en vientres de América cayó semilla\n" +
                        "de la raza de hierro que fue de España,\n" +
                        "mezcló su fuerza heroica la gran Castilla\n" +
                        "con la fuerza del indio de la montaña.\n" +
                        "\n" +
                        "¡Pluguiera a Dios las aguas antes intactas\n" +
                        "no reflejaran nunca las blancas velas;\n" +
                        "ni vieran las estrellas estupefactas\n" +
                        "arribar a la orilla tus carabelas!\n" +
                        "\n" +
                        "Libre como las águilas, vieran los montes\n" +
                        "pasar los aborígenes por los boscajes,\n" +
                        "persiguiendo los pumas y los bisontes\n" +
                        "con el dardo certero de sus carcajes.\n" +
                        "\n" +
                        "Que más valiera el jefe rudo y bizarro\n" +
                        "que el soldado que en fango sus glorias finca,\n" +
                        "que ha hecho gemir al zipa bajo su carro\n" +
                        "o temblar las heladas momias del Inca.\n" +
                        "\n" +
                        "La cruz que nos llevaste padece mengua;\n" +
                        "y tras encanalladas revoluciones,\n" +
                        "la canalla escritora mancha la lengua\n" +
                        "que escribieron Cervantes y Calderones.\n" +
                        "\n" +
                        "Cristo va por las calles flaco y enclenque,\n" +
                        "Barrabás tiene esclavos y charreteras,\n" +
                        "y en las tierras de Chibcha, Cuzco y Palenque\n" +
                        "han visto engalonadas a las panteras.\n" +
                        "\n" +
                        "Duelos, espantos, guerras, fiebre constante\n" +
                        "en nuestra senda ha puesto la suerte triste:\n" +
                        "¡Cristóforo Colombo, pobre Almirante,\n" +
                        "ruega a Dios por el mundo que descubriste!", 5),
            Book(0, "Marcha triunfal", "¡Ya viene el cortejo!\n" +
                    "¡Ya viene el cortejo! Ya se oyen los claros clarines,\n" +
                    "la espada se anuncia con vivo reflejo;\n" +
                    "ya viene, oro y hierro, el cortejo de los paladines.\n" +
                    "\n" +
                    "Ya pasa debajo los arcos ornados de blancas Minervas y Martes,\n" +
                    "los arcos triunfales en donde las Famas erigen sus largas trompetas\n" +
                    "la gloria solemne de los estandartes,\n" +
                    "llevados por manos robustas de heroicos atletas.\n" +
                    "Se escucha el ruido que forman las armas de los caballeros,\n" +
                    "los frenos que mascan los fuertes caballos de guerra,\n" +
                    "los cascos que hieren la tierra\n" +
                    "y los timbaleros,\n" +
                    "que el paso acompasan con ritmos marciales.\n" +
                    "¡Tal pasan los fieros guerreros\n" +
                    "debajo los arcos triunfales!\n" +
                    "\n" +
                    "Los claros clarines de pronto levantan sus sones,\n" +
                    "su canto sonoro,\n" +
                    "su cálido coro,\n" +
                    "que envuelve en su trueno de oro\n" +
                    "la augusta soberbia de los pabellones.\n" +
                    "Él dice la lucha, la herida venganza,\n" +
                    "las ásperas crines,\n" +
                    "los rudos penachos, la pica, la lanza,\n" +
                    "la sangre que riega de heroicos carmines\n" +
                    "la tierra;\n" +
                    "de negros mastines\n" +
                    "que azuza la muerte, que rige la guerra.\n" +
                    "\n" +
                    "Los áureos sonidos\n" +
                    "anuncian el advenimiento\n" +
                    "triunfal de la Gloria;\n" +
                    "dejando el picacho que guarda sus nidos,\n" +
                    "tendiendo sus alas enormes al viento,\n" +
                    "los cóndores llegan. ¡Llegó la victoria!\n" +
                    "\n" +
                    "Ya pasa el cortejo.\n" +
                    "Señala el abuelo los héroes al niño.\n" +
                    "Ved cómo la barba del viejo\n" +
                    "los bucles de oro circunda de armiño.\n" +
                    "Las bellas mujeres aprestan coronas de flores,\n" +
                    "y bajo los pórticos vense sus rostros de rosa;\n" +
                    "y la más hermosa\n" +
                    "sonríe al más fiero de los vencedores.\n" +
                    "¡Honor al que trae cautiva la extraña bandera\n" +
                    "honor al herido y honor a los fieles\n" +
                    "soldados que muerte encontraron por mano extranjera!\n" +
                    "\n" +
                    "¡Clarines! ¡Laureles!\n" +
                    "\n" +
                    "Los nobles espadas de tiempos gloriosos,\n" +
                    "desde sus panoplias saludan las nuevas coronas y lauros\n" +
                    "las viejas espadas de los granaderos, más fuertes que osos,\n" +
                    "hermanos de aquellos lanceros que fueron centauros?\n" +
                    "Las trompas guerreras resuenan:\n" +
                    "de voces los aires se llenan...\n" +
                    "\n" +
                    "A aquellas antiguas espadas,\n" +
                    "a aquellos ilustres aceros,\n" +
                    "que encaman las glorias pasadas...\n" +
                    "Y al sol que hoy alumbra las nuevas victorias ganadas,\n" +
                    "y al héroe que guía su grupo de jóvenes fieros,\n" +
                    "al que ama la insignia del suelo materno,\n" +
                    "al que ha desafiado, ceñido el acero y el arma en la mano,\n" +
                    "los soles del rojo verano,\n" +
                    "las nieves y vientos del gélido invierno,\n" +
                    "la noche, la escarcha\n" +
                    "y el odio y la muerte, por ser por la patria inmortal,\n" +
                    "¡saludan con voces de bronce las trompas de guerra que tocan la marcha triunfal!...", 5),
            Book(0, "Agencia",
                "¿Qué hay de nuevo?… Tiembla la Tierra.\n" +
                    "En La Haya incuba la guerra.\n" +
                    "Los reyes han terror profundo.\n" +
                    "Huele a podrido en todo el mundo.\n" +
                    "No hay aromas en Galaad.\n" +
                    "Desembarcó el marqués de Sade\n" +
                    "procedente de Seboim.\n" +
                    "Cambia de curso el gulf–stream.\n" +
                    "París se flagela de placer.\n" +
                    "Un cometa va a aparecer.\n" +
                    "Se cumplen ya las profecías\n" +
                    "del viejo monje Malaquías.\n" +
                    "En la iglesia el diablo se esconde.\n" +
                    "Ha parido una monja… (¿En dónde?…)\n" +
                    "Barcelona ya no está bona\n" +
                    "sino cuando la bomba sona…\n" +
                    "China se corta la coleta.\n" +
                    "Henry de Rothschild es poeta.\n" +
                    "Madrid abomina la capa.\n" +
                    "Ya no tiene eunucos el papa.\n" +
                    "Se organizará por un bill\n" +
                    "la prostitución infantil.\n" +
                    "La fe blanca se desvirtúa\n" +
                    "y todo negro continúa.\n" +
                    "En alguna parte está listo\n" +
                    "el palacio del Anticristo.\n" +
                    "Se cambian comunicaciones\n" +
                    "entre lesbianas y gitones.\n" +
                    "Se anuncia que viene el Judío\n" +
                    "errante… ¿Hay algo más, Dios mío?…", 5),
            Book(0, "Yo soy aquel",
                "Yo soy aquel que ayer no más decía\n" +
                        "el verso azul y la canción profana,\n" +
                        "en cuya noche un ruiseñor había\n" +
                        "que era alondra de luz por la mañana.\n" +
                        "\n" +
                        "El dueño fui de mi jardín de sueño,\n" +
                        "lleno de rosas y de cisnes vagos;\n" +
                        "el dueño de las tórtolas, el dueño\n" +
                        "de góndolas y liras en los lagos;\n" +
                        "\n" +
                        "y muy siglo diez y ocho y muy antiguo\n" +
                        "y muy moderno; audaz, cosmopolita;\n" +
                        "con Hugo fuerte y con Verlaine ambiguo,\n" +
                        "y una sed de ilusiones infinita.\n" +
                        "\n" +
                        "Yo supe de dolor desde mi infancia,\n" +
                        "mi juventud... ¿fue juventud la mía?\n" +
                        "Sus rosas aún me dejan su fragancia...\n" +
                        "una fragancia de melancolía...\n" +
                        "\n" +
                        "Potro sin freno se lanzó mi instinto,\n" +
                        "mi juventud montó potro sin freno;\n" +
                        "iba embriagada y con puñal al cinto;\n" +
                        "si no cayó, fue porque Dios es bueno.\n" +
                        "\n" +
                        "En mi jardín se vio una estatua bella;\n" +
                        "se juzgó mármol y era carne viva;\n" +
                        "una alma joven habitaba en ella,\n" +
                        "sentimental, sensible, sensitiva.\n" +
                        "\n" +
                        "Y tímida ante el mundo, de manera\n" +
                        "que encerrada en silencio no salía,\n" +
                        "sino cuando en la dulce primavera\n" +
                        "era la hora de la melodía...\n" +
                        "\n" +
                        "Hora de ocaso y de discreto beso;\n" +
                        "hora crepuscular y de retiro;\n" +
                        "hora de madrigal y de embeleso,\n" +
                        "de «te adoro», y de «¡ay!» y de suspiro.\n" +
                        "\n" +
                        "Y entonces era la dulzaina un juego\n" +
                        "de misteriosas gamas cristalinas,\n" +
                        "un renovar de gotas del Pan griego\n" +
                        "y un desgranar de músicas latinas.\n" +
                        "\n" +
                        "Con aire tal y con ardor tan vivo,\n" +
                        "que a la estatua nacían de repente\n" +
                        "en el muslo viril patas de chivo\n" +
                        "y dos cuernos de sátiro en la frente.\n" +
                        "\n" +
                        "Como la Galatea gongorina\n" +
                        "me encantó la marquesa verleniana,\n" +
                        "y así juntaba a la pasión divina\n" +
                        "una sensual hiperestesia humana;\n" +
                        "\n" +
                        "todo ansia, todo ardor, sensación pura\n" +
                        "y vigor natural; y sin falsía,\n" +
                        "y sin comedia y sin literatura...:\n" +
                        "si hay un alma sincera, esa es la mía.\n" +
                        "\n" +
                        "La torre de marfil tentó mi anhelo;\n" +
                        "quise encerrarme dentro de mí mismo,\n" +
                        "y tuve hambre de espacio y sed de cielo\n" +
                        "desde las sombras de mi propio abismo.\n" +
                        "\n" +
                        "Como la esponja que la sal satura\n" +
                        "en el jugo del mar, fue el dulce y tierno\n" +
                        "corazón mío, henchido de amargura\n" +
                        "por el mundo, la carne y el infierno.\n" +
                        "\n" +
                        "Mas, por gracia de Dios, en mi conciencia\n" +
                        "el Bien supo elegir la mejor parte;\n" +
                        "y si hubo áspera hiel en mi existencia,\n" +
                        "melificó toda acritud el Arte.\n" +
                        "\n" +
                        "Mi intelecto libré de pensar bajo,\n" +
                        "bañó el agua castalia el alma mía,\n" +
                        "peregrinó mi corazón y trajo\n" +
                        "de la sagrada selva la armonía.\n" +
                        "\n" +
                        "¡Oh, la selva sagrada! ¡Oh, la profunda\n" +
                        "emanación del corazón divino\n" +
                        "de la sagrada selva! ¡Oh, la fecunda\n" +
                        "fuente cuya virtud vence al destino!\n" +
                        "\n" +
                        "Bosque ideal que lo real complica,\n" +
                        "allí el cuerpo arde y vive y Psiquis vuela;\n" +
                        "mientras abajo el sátiro fornica,\n" +
                        "ebria de azul deslíe Filomela.\n" +
                        "\n" +
                        "Perla de ensueño y música amorosa\n" +
                        "en la cúpula en flor del laurel verde,\n" +
                        "Hipsipila sutil liba en la rosa,\n" +
                        "y la boca del fauno el pezón muerde.\n" +
                        "\n" +
                        "Allí va el dios en celo tras la hembra,\n" +
                        "y la caña de Pan se alza del lodo;\n" +
                        "la eterna vida sus semillas siembra,\n" +
                        "y brota la armonía del gran Todo.\n" +
                        "\n" +
                        "El alma que entra allí debe ir desnuda,\n" +
                        "temblando de deseo y fiebre santa,\n" +
                        "sobre cardo heridor y espina aguda:\n" +
                        "así sueña, así vibra y así canta.\n" +
                        "\n" +
                        "Vida, luz y verdad, tal triple llama\n" +
                        "produce la interior llama infinita.\n" +
                        "El Arte puro como Cristo exclama:\n" +
                        "Ego sum lux et veritas et vita!\n" +
                        "\n" +
                        "Y la vida es misterio, la luz ciega\n" +
                        "y la verdad inaccesible asombra;\n" +
                        "la adusta perfección jamás se entrega,\n" +
                        "y el secreto ideal duerme en la sombra.\n" +
                        "\n" +
                        "Por eso ser sincero es ser potente;\n" +
                        "de desnuda que está, brilla la estrella;\n" +
                        "el agua dice el alma de la fuente\n" +
                        "en la voz de cristal que fluye de ella.\n" +
                        "\n" +
                        "Tal fue mi intento, hacer del alma pura\n" +
                        "mía, una estrella, una fuente sonora,\n" +
                        "con el horror de la literatura\n" +
                        "y loco de crepúsculo y de aurora.\n" +
                        "\n" +
                        "Del crepúsculo azul que da la pauta\n" +
                        "que los celestes éxtasis inspira,\n" +
                        "bruma y tono menor —¡toda la flauta!,\n" +
                        "y Aurora, hija del Sol— ¡toda la lira!\n" +
                        "\n" +
                        "Pasó una piedra que lanzó una honda;\n" +
                        "pasó una flecha que aguzó un violento.\n" +
                        "La piedra de la honda fue a la onda,\n" +
                        "y la flecha del odio fuese al viento.\n" +
                        "\n" +
                        "La virtud está en ser tranquilo y fuerte;\n" +
                        "con el fuego interior todo se abrasa;\n" +
                        "se triunfa del rencor y de la muerte,\n" +
                        "y hacia Belén... ¡la caravana pasa!", 5),

            Book(0, "Canto de esperanza",
                "Un gran vuelo de cuervos mancha el azul celeste.\n" +
                        "Un soplo milenario trae amagos de peste.\n" +
                        "Se asesinan los hombres en el extremo Este.\n" +
                        "\n" +
                        "¿Ha nacido el apocalíptico Anticristo?\n" +
                        "Se han sabido presagios y prodigios se han visto\n" +
                        "y parece inminente el retorno de Cristo.\n" +
                        "\n" +
                        "La tierra está preñada de dolor tan profundo\n" +
                        "que el soñador, imperial meditabundo,\n" +
                        "sufre con las angustias del corazón del mundo.\n" +
                        "\n" +
                        "Verdugos de ideales afligieron la tierra,\n" +
                        "en un pozo de sombra la humanidad se encierra\n" +
                        "con los rudos molosos del odio y de la guerra.\n" +
                        "\n" +
                        "¡Oh, Señor Jesucristo! ¡Por qué tardas, qué esperas\n" +
                        "para tender tu mano de luz sobre las fieras\n" +
                        "y hacer brillar al sol tus divinas banderas!\n" +
                        "\n" +
                        "Surge de pronto y vierte la esencia de la vida\n" +
                        "sobre tanta alma loca, triste o empedernida,\n" +
                        "que amante de tinieblas tu dulce aurora olvida.\n" +
                        "\n" +
                        "Ven, Señor, para hacer la gloria de Ti mismo;\n" +
                        "ven con temblor de estrellas y horror de cataclismo,\n" +
                        "ven a traer amor y paz sobre el abismo.\n" +
                        "\n" +
                        "Y tu caballo blanco, que miró el visionario,\n" +
                        "pase. Y suene el divino clarín extraordinario.\n" +
                        "Mi corazón será brasa de tu incensario.", 5)
        )

        librosIniciales.forEach {
            agregarLibro(it.title, it.lectura)
        }
    }

    private fun librosInicialesYaAgregados(): Boolean {
        val db = dbHelper.readableDatabase
        val query = "SELECT COUNT(*) FROM ${HelperDatos.LIBROS}"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count > 0
    }

    fun actualizarRating(bookId: Long, rating: Int) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(HelperDatos.COLUMN_RECORD, rating)
        }
        db.update(
            HelperDatos.LIBROS,
            contentValues,
            "${HelperDatos.COLUMN_ID} = ?",
            arrayOf(bookId.toString())
        )
        db.close()
    }

    fun obtenerLibroPorId(id: Long): Book? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            HelperDatos.LIBROS,
            arrayOf(HelperDatos.COLUMN_ID, HelperDatos.COLUMN_TITLE, HelperDatos.COLUMN_LECTURA, HelperDatos.COLUMN_RECORD),
            "${HelperDatos.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null,

        )

        var book: Book? = null

        cursor.use { // Utilizamos use para asegurarnos de cerrar el cursor automáticamente
            if (it.moveToFirst()) {
                val id = it.getLong(it.getColumnIndexOrThrow(HelperDatos.COLUMN_ID))
                val title = it.getString(it.getColumnIndexOrThrow(HelperDatos.COLUMN_TITLE))
                val lectura = it.getString(it.getColumnIndexOrThrow(HelperDatos.COLUMN_LECTURA))
                val rating = it.getInt(it.getColumnIndexOrThrow(HelperDatos.COLUMN_RECORD))

                book = Book(id, title, lectura, rating)
            }
        }

        cursor.close()
        db.close()

        return book
    }
}

