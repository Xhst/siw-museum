SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- --------------------------------------------------------

--
-- Struttura della tabella artists
--

CREATE TABLE artists (
  id bigint(20) NOT NULL,
  birth_place varchar(255) DEFAULT NULL,
  date_of_birth date DEFAULT NULL,
  date_of_death date DEFAULT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  nationality varchar(255) DEFAULT NULL,
  place_of_death varchar(255) DEFAULT NULL,
  biography varchar(5000) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella artists
--

INSERT INTO artists (id, birth_place, date_of_birth, date_of_death, first_name, last_name, nationality, place_of_death, biography) VALUES
(1, 'Napoli', '1598-12-07', '1680-11-28', 'Gian Lorenzo', 'Bernini', 'IT', 'Roma', 'Giovan Lorenzo Bernini, meglio conosciuto come Gian Lorenzo Bernini (Napoli, 7 dicembre 1598 – Roma, 28 novembre 1680), è stato uno scultore, urbanista, architetto, pittore, scenografo e commediografo italiano.\r\n\r\nArtista poliedrico e multiforme, Bernini è considerato il massimo protagonista della cultura figurativa barocca. La sua opera conobbe un clamoroso successo e dominò la scena europea per più di un secolo dopo la morte; analogamente, l`influenza di Bernini sui contemporanei e sui posteri fu di enorme portata.'),
(2, 'Urbino', '1483-03-28', '1520-04-06', 'Raffaello', 'Sanzio', 'IT', 'Roma', 'Raffaello Sanzio (Urbino, 28 marzo o 6 aprile 1483 – Roma, 6 aprile 1520) è stato un pittore e architetto italiano, fra i più celebri del Rinascimento.\r\n\r\nConsiderato uno dei più grandi artisti di ogni tempo, la sua esperienza è considerata profondamente innovativa per le numerose opere iconiche e per il modo in cui queste sono state prodotte, avvalendosi di una bottega altamente strutturata e composta da numerosi professionisti di altissimo livello. La maniera di Raffaello fu di vitale importanza per lo sviluppo del linguaggio artistico dei secoli a venire, sia per emulazione da parte dei suoi collaboratori che ne portarono avanti il linguaggio per decenni in tutta Europa raccolti nella scuola del manierismo, sia per contrasto attraverso il rifiuto dell`opera raffaellesca iniziato da Caravaggio.\r\n\r\nLa sua influenza sulla storia dell`arte occidentale è straordinariamente estesa. Impostosi come modello fondamentale per tutte le accademie di belle arti fino alla prima metà dell`Ottocento, il mito di Raffaello raggiunse le avanguardie del XX secolo e l`arte contemporanea del XXI secolo, fino a lambire altre arti come il cinema e il fumetto.\r\n\r\nRaffaello è stato un artista a 360 gradi, perché nella sua breve vita fu pittore, architetto e poeta. Inoltre rivoluzionò completamente il concetto di bottega: diede molta importanza ai suoi allievi e, mentre gli altri artisti lasciavano agli aiuti piccoli dettagli da dipingere, egli fece realizzare alcune opere totalmente o in buona parte dagli allievi. Per questo, però, non è facile individuare e distinguere in un`opera la mano di Raffaello e quella degli aiuti.'),
(3, 'Anversa', '1554-01-01', '1626-10-07', 'Paul', 'Bril', 'BE', 'Roma', NULL);

-- --------------------------------------------------------

--`
-- Struttura della tabella credentials
--

CREATE TABLE credentials (
  id bigint(20) NOT NULL,
  password varchar(255) DEFAULT NULL,
  role varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  user_id bigint(20) DEFAULT NULL,
  provider varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella credentials
--

INSERT INTO credentials (id, password, role, username, user_id, provider) VALUES
(1, '$2a$10$WB1eX2F4FVxVZkCOYZD1quiZz1hNgYMXask94V8VZiIXaUWIzUtfa', 'ADMIN', 'lorem', 1, 'LOCAL'),
(2, '$2a$10$RSHvWVOXn7Pq6vDKgv7NiekgfFduawXCLgJSYMsdFO2C1Qx8.Ost6', 'DEFAULT', 'Ipsum', 2, 'LOCAL'),
(3, '$2a$10$VCYJi3CduVr/8Tzl6Kvcy.x/Vuj3sW.TAiVvoqKVLcYPez/bJNWRm', 'DEFAULT', 'Dolor', 3, 'LOCAL'),
(10, '$2a$10$NYQAGuRkuEUPmo/ivjz7s.4koXUvSBNOW4xbMWXjBjwXD8WVeJX2G', 'DEFAULT', 'Prova', 8, 'LOCAL');

-- --------------------------------------------------------

--
-- Struttura della tabella hibernate_sequence
--

CREATE TABLE hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella hibernate_sequence
--

INSERT INTO hibernate_sequence (next_val) VALUES
(11);

-- --------------------------------------------------------

--
-- Struttura della tabella users
--

CREATE TABLE users (
  id bigint(20) NOT NULL,
  birth_place varchar(255) DEFAULT NULL,
  date_of_birth date DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  phone_number varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella users
--

INSERT INTO users (id, birth_place, date_of_birth, email, first_name, last_name, phone_number) VALUES
(1, NULL, NULL, 'lorem@ipsum.prova', 'Lorem', 'Ipsum', NULL),
(2, NULL, NULL, 'lorem@ipsum.prova', 'Lorem', 'Ipsum', NULL),
(3, NULL, NULL, 'lorem@ipsum.prova', 'Lorem', 'Ipsum', NULL),
(8, NULL, NULL, 'mario.rossi@example.com', 'Mario', 'Rossi', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella works
--

CREATE TABLE works (
  id bigint(20) NOT NULL,
  date_of_realization date DEFAULT NULL,
  description varchar(5000) DEFAULT NULL,
  title varchar(255) NOT NULL,
  artist_id bigint(20) DEFAULT NULL,
  collection_id bigint(20) DEFAULT NULL,
  image_url varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella works
--

INSERT INTO works (id, date_of_realization, description, title, artist_id, collection_id, image_url) VALUES(1, NULL, 'Si tratta di un autoritratto dell’artista all’età di circa 25 anni. Le fattezze del volto sono infatti assai simili a quelle del suo celebre autoritratto in marmo: il volto del David, commissionatogli da Scipione Borghese e ultimato nel 1623. La composizione del dipinto, nonostante l’eleganza dell’immagine, tradisce la preminente attività scultorea dell’artista. Il dipinto, insieme agli atri due della Galleria Borghese, l’Autoritratto in età matura (inv.545) e il Ritratto di fanciullo (inv.555), costituisce un’importante testimonianza dell’attività pittorica dell’artista.', 'Autoritratto in età giovanile', 1, NULL, '/img/works/autoritratto_in_eta_giovanile__gian_lorenzo_bernini.jpg'),
(2, NULL, 'La Verità fu realizzata da Gian Lorenzo Bernini per sé stesso in un periodo difficile della sua carriera, culminato con l’abbattimento di uno dei campanili da lui progettati per la Basilica di S. Pietro e con l’elezione al soglio pontificio nel 1644 di Innocenzo X Pamphilj, che gli preferì come architetto Francesco Borromini. Raffigurata come una fanciulla sorridente e nuda, la Verità è seduta su un masso roccioso, tiene nella mano destra il sole e poggia la gamba sinistra sul globo terrestre, secondo un’iconografia già canonizzata nella celebre Iconologia di Cesare Ripa (1600).\r\n\r\nL’opera doveva far parte di un gruppo scultoreo rappresentante l’allegoria della Verità svelata dal Tempo, mai portato a termine. Alla morte dell’artista il grande blocco di marmo destinato alla realizzazione del Tempo in volo, rivelatore della Verità, fu venduto dagli eredi.\r\n\r\nDel gruppo scultoreo sono noti numerosi disegni autografi; nella figura della Verità si possono riconoscere dei legami con l’incompiuta Allegoria della Virtù di Correggio (Antonio Allegri), conservata presso la Galleria Doria Pamphilj di Roma.', 'La verità', 1, 1, '/img/works/la_verita__gian_lorenzo_bernini.jpg'),
(3, NULL, 'L’opera raffigura il rapimento di Proserpina per mano di Plutone, dio degli Inferi. Il mito, presente sia in Claudiano sia in Ovidio, narra del rapimento della fanciulla sulle rive del lago di Pergusa, nelle vicinanze di Enna. La madre Cerere, dea delle messi, folle di dolore, ridusse alla siccità la terra, costringendo Giove a intercedere presso Plutone per consentire alla giovane di tornare da lei per sei mesi l’anno. Bernini rappresenta il momento culminante dell’azione: il dio fiero e insensibile sta trascinando Proserpina nell’Ade, i muscoli sono tesi nello sforzo di sostenere il corpo che si sta divincolando, tanto che le mani di Plutone affondano nella sua carne.\r\n\r\nL’impianto della scultura è spinto fino ai limiti della stabilità dalle due figure che si ritraggono l’una dall’altra pur rimanendo frontali rispetto allo spettatore. L’avvitamento della fanciulla richiama il virtuosismo di gusto manierista, ma la potenza della plastica, la tensione dei muscoli, la tenerezza sensuale delle carni, l’intensità del sentimento esprimono un nuovo linguaggio espressivo, fondato su un naturalismo evidente nella straordinaria resa materica delle superfici. Attraverso lo studio costante della statuaria classica e il recupero degli strumenti antichi Bernini traduce nel marmo la poetica del racconto mitologico, confrontandosi con le potenzialità della stessa pittura.', 'Ratto di Proserpina', 1, 1, '/img/works/ratto_di_proserpina__gian_lorenzo_bernini.jpg'),
(4, NULL, 'La tavola, firmata e datata in basso a sinistra “Raphael Urbinas MDVII.”, fu commissionata da Atalanta Baglioni in memoria del figlio Grifonetto, ucciso durante una lotta fratricida per il possesso della signoria di Perugia.\r\nL’opera, utilizzata come pala d’altare nella chiesa di San Francesco, rimase nella città umbra per cento anni, finché una notte, con la complicità dei frati, fu prelevata di nascosto e inviata a Roma a papa Paolo V, che ne fece dono al nipote Scipione Borghese (1608).\r\nOriginariamente era sormontata da una cimasa con l’immagine di Dio Padre benedicente (Perugia, Galleria Nazionale dell’Umbria) e accompagnata da una predella con la raffigurazione delle Virtù teologali, oggi ai Musei Vaticani.\r\n\r\nNel mettere in scena il dramma della rappresentazione, Raffaello prese a modello il Compianto su Cristo morto di Perugino a Palazzo Pitti, eseguito nel 1495, in cui Cristo è raffigurato disteso a terra secondo un’iconografia allora tradizionale.\r\n\r\nL’ingente numero di disegni preparatori documenta lo studio dall’antico e il lungo e laborioso evolversi del progetto compositivo, reso progressivamente più drammatico e dinamico nella nuova iconografia del “trasporto”. La novità compositiva della Deposizione, segnò il superamento della tradizione umbro-toscana e aprí a un nuovo linguaggio espressivo, sintesi di un perfetto equilibrio tra idealizzazione formale ed espressione del sentimento, secondo uno stile a lungo ricercato nei modelli dell’antichità classica e caratteristico della successiva fase romana dell’artista.', 'Deposizione di Cristo', 2, NULL, '/img/works/deposizione_di_cristo__sanzio_raffaello.jpg'),
(5, NULL, 'Questa tavoletta viene ritenuta di Paul Bril e datata verso la fine del Cinquecento. La scena di storia religiosa, con i due frati inseguiti dai soldati armati di spade, è ambientata in un paesaggio tipicamente fiammingo, molto scorciato, nel quale la profondità è scandita per piani colorati. Bril si dimostra molto vicino alle opere di Jan Bruegel, altro pittore fiammingo di grande successo fra i mecenati e i collezionisti italiani di questo periodo.', 'Uccisione di San Pietro martire', 3, NULL, '/img/works/uccisione_di_san_pietro_martire__brill_paul.jpg'),
(6, NULL, 'Nel dipinto sono raffigurate due scene: in primo piano, nell’ampio paesaggio con scorci fluviali, il battesimo di Cristo nel Giordano, cui assistono due angeli e, nei recessi boscosi sulla destra, la predica del Battista.\r\n\r\nPer il modo di raffigurare le fronde degli alberi, colpite da improvvisi bagliori luminosi, e per l’ambientazione delle scene narrative, il quadro sembra riconducibile all’ultimo lustro del secolo.', 'Paesaggio con Battesimo di Cristo e predica del Battista', 3, NULL, '/img/works/paesaggio_con_battesimo_di_cristo_e_predica_del_battista__brill_paul.jpg'),


-- --------------------------------------------------------

--
-- Struttura della tabella works_collections
--

CREATE TABLE works_collections (
  id bigint(20) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  curator_id bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella works_collections
--

INSERT INTO works_collections (id, description, name, curator_id) VALUES
(1, 'Raccolta di sculture di Bernini', 'Le sculture di Bernini', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle artists
--
ALTER TABLE artists
  ADD PRIMARY KEY (id);

--
-- Indici per le tabelle credentials
--
ALTER TABLE credentials
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_l7xhygibdj6cgkpj2ih1jgx14 (username) USING HASH,
  ADD KEY FKcbcgksvnqvqxrrc4dwv3qys65 (user_id);

--
-- Indici per le tabelle users
--
ALTER TABLE users
  ADD PRIMARY KEY (id);

--
-- Indici per le tabelle works
--
ALTER TABLE works
  ADD PRIMARY KEY (id),
  ADD KEY FK6kip484gqcclkjvcvbml48wsf (artist_id),
  ADD KEY FKhsy4rtwtxlk39hesgvoapyx6w (collection_id);

--
-- Indici per le tabelle works_collections
--
ALTER TABLE works_collections
  ADD PRIMARY KEY (id),
  ADD KEY FKod5604jf6y29pc9c16rhnersd (curator_id);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella artists
--
ALTER TABLE artists
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella users
--
ALTER TABLE users
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella works
--
ALTER TABLE works
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella works_collections
--
ALTER TABLE works_collections
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
