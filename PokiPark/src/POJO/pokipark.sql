-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 21. Jun 2021 um 18:52
-- Server-Version: 10.4.19-MariaDB
-- PHP-Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `pokipark`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pokedex`
--

CREATE TABLE `pokedex` (
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `typ` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `img_path` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `firstEvo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `secondEvo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `thirdEvo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pokedexEntry` text COLLATE utf8_unicode_ci NOT NULL,
  `id` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `pokedex`
--

INSERT INTO `pokedex` (`name`, `typ`, `img_path`, `firstEvo`, `secondEvo`, `thirdEvo`, `pokedexEntry`, `id`) VALUES
('Bisasam', 'Pflanze', '', 'Bisasam', 'Bisaknosp', 'Bisaflor', 'Ist es eine Pflanze die zum Tier wurde oder ein Tier welches zur Pflanze wurde. Man weiß es nicht.', 1),
('Bisaknosp', 'Pflanze', '', 'Bisasam', 'Bisaknosp', 'Bisaflor', 'Ob Pflanze oder Tier ist jetzt egal; Es fängt an zu wachsen und es hört nicht auf.', 2),
('Bisaflor', 'Pflanze', '', 'Bisasam', 'Bisaknosp', 'Bisaflor', 'Sein Wachstumsschub scheint sich ausgepowert zu haben. Es ist nun einfach gewaltig und mächtig.', 3),
('Glumanda', 'Feuer', '', 'Glumanda', 'Glutexo', 'Glurak', 'Ein kleiner Drache. Manche können ihr Glück kaum fassen mal einen echten Feuerdrachen zu sehen. Und dann ist er auch noch zu süß.', 4),
('Glutexo', 'Feuer', '', 'Glumanda', 'Glutexo', 'Glurak', 'Aus dem kleinen süßen aber auch anmutigen Feuerdrachen, ist nun seine wahre feurige Drachenkraft hervorgebrochen. Nun kämpft es um die Kontrolle über seine neuen Kräfte.', 5),
('Glurak', 'Feuer', '', 'Glumanda', 'Glutexo', 'Glurak', 'Ob der wachsende Feuerdrache die Kontrolle über seine freigesetzten Feuerkräfte erlangt hat, wird sich nun zeigen. Es ist sehr beeindruckend seine Kräfte zu erleben und es füllt einen mit Ehrfurcht vor dem Drachen.', 6),
('Schiggy', 'Wasser', '', 'Schiggy', 'Schillok', 'Turtok', 'Die freundliche Schildkröte aus der Nachbarschaft. Jeder kennt sie, jeder liebt sie.', 7),
('Schillok', 'Wasser', '', 'Schiggy', 'Schillok', 'Turtok', 'Aus der kleinen feinen Schildkröte ist ein richtiger Wettkämpfer geworden. Sein Kampfgeist erweist sich während der Wachstumsphase als besonders stark.', 8),
('Turtok', 'Wasser', '', 'Schiggy', 'Schillok', 'Turtok', 'Aus der Wasserschildkröte ist ein im Kampf erfahrender Riese geworden. Ein respektabler Gegner in jedem Kampf.', 9),
('Pottrott', 'Käfer & Gestein', '', 'Pottrott', 'NULL', 'NULL', 'Dieser gelbe Käfer überlebt in seiner normalerweise roten Hülle einfach alles. Sogar einen direkten Atomschlag würde er verkraften.', 10),
('Mampfaxo', 'Normal', '', 'Mampfaxo', 'Relaxo', 'NULL', 'Dieses Pokemon folgt immer seiner Nase. Es ist sehr gutmütig.\r\nBitte achten Sie auf Ihr Essen!', 11),
('Knofensa', 'Pflanze', '', 'Knofensa', 'Ultrigaria', 'Sarzenia', 'Eine kleine Pflanze.', 12),
('Ultrigaria', 'Pflanze', '', 'Knofensa', 'Ultrigaria', 'Sarzenia', 'Ultrigaria ist eine Pflanzen-Pokemon, welches etwas größer ist als seine Vorentwicklung.', 13),
('Sarzenia', 'Pflanze', '', 'Knofensa', 'Ultrigaria', 'Sarzenia', 'Diese gewaltig gewordene Pflanze frisst scheinbar alles außer Fliegen. Gelegentlich verschwindet der Trainer dieses Pokemons halb in dessen Maul; So zeigt Sarzenia seine Zuneigung.', 14),
('Pichu', 'Elektro', '', 'Pichu', 'Pikachu', 'Raichu', 'Dieser kleine Elektroheld spielt mit seinen Elektrokräften, welche jedoch noch sehr schwach und für Menschen ungefährlich ist.', 15),
('Pikachu', 'Elektro', '', 'Pichu', 'Pikachu', 'Raichu', 'Die meisten Leute kennen diese Elektromaus sogar schon bevor sie überhaupt wissen was Pokémon sind. ', 16),
('Raichu', 'Elektro', '', 'Pichu', 'Pikachu', 'Raichu', 'Aus der kleinen, süßen Elektromaus ist ein mächtiges Geschöpf des Donners geworden. Wenn es sehr wütend ist können seine Blitze sehr gefährlich für alles um es herum werden.', 17),
('Ho-Oh', 'Feuer & Flug', '', 'Ho-Oh', 'NULL', 'NULL', 'Ein von Mythen umgebender Vogel. Nur sehr wenige Menschen haben ihn gesehen. Es ist nicht viel über ihn bekannt. Nur das sein Anblick atemberaubend ist.', 18),
('Lugia', 'Flug & Psycho', '', 'Lugia', 'NULL', 'NULL', 'Die Legende des Meeres. Sein Lied gäbe jedem gefallenden Helden wieder Kraft. Viele Sagen und Mythen existieren um dieses legendäre Pokémon.', 19),
('Dialga', 'Stahl & Dracbe', '', 'Dialga', 'NULL', 'NULL', 'Ein Pokémon von welchem behauptet wird, es könnte die Zeit beherrschen. Nur aus Legenden ist dieses Pokémon bekannt. Kaum ein Mensch hat je eines gesehen. ', 20),
('Palkia', 'Wasser & Drache', '', 'Palkia', 'NULL', 'NULL', 'Ebenso wie Dialga ist dieses Pokémon sehr mächtig und von Sagen umwoben. Es soll den Raum beherrschen und aus einer anderen Dimension stammen.', 21),
('Elekid', 'Elektro', '', 'Elekid', 'Elektek', 'Elevoltek', 'Dieses kleine Elektro-Pokémon ist ein richtiger Draufgänger. Es ist sehr begeistert von seiner eigenen Stärke.', 22),
('Elektek', 'Elektro', '', 'Elekid', 'Elektek', 'Elevoltek', 'Das Elektro-Pokémon ist immer noch der volle Draufgänger und stellt gerne seine außergewöhnlichen Elektro-Kräfte zur Schau.', 23),
('Elevoltek', 'Elektro', '', 'Elekid', 'Elektek', 'Elevoltek', 'Der Elektroriese hat seine Draufgängerseite abgelegt und ist ruhiger geworden. Seine Kräfte hingegen sind nun ausgeprägt und sehr mächtig.', 24),
('Ditto', 'Normal', '', 'Ditto', 'NULL', 'NULL', 'Ein Pokémon welches die Form wechseln kann. Es liebt es sich zu verstecken, indem es die Form eines anderen Pokémons annimmt. ', 25),
('Vulpix', 'Feuer', '', 'Vulpix', 'Vulnona', 'NULL', 'Das Fell dieses Pokémons fühlt sich leicht warm an, wenn man es streichelt. Es besitzt an starkes Potenzial an Feuer-Kräften.', 26),
('Vulnona', 'Feuer', '', 'Vulpix', 'Vulnona', 'NULL', 'Das elegant gewordene Feuer-Pokémon hat nun einen neungeteilten Schweife. Außerdem hat es psychokinetische Kräfte entwickelt, welche seine Feuer-Kräfte enorm verstärken. Man sollte es nicht unterschätzen.', 27),
('Togepi', 'Fee', '', 'Togepi', 'Togetic', 'Togekiss', 'Ein kleines fröhliches Ei-Pokémon. Manchmal geschehen eigenartige Sachen um es herum.', 28),
('Togetik', 'Fee', '', 'Togepi', 'Togetik', 'Togekiss', 'Mit seinen kleinen Flügeln kann es sich leicht über dem Boden erheben. Es besitzt leichte Psychokinetische-Kräfte.', 29),
('Togekiss', 'Flug & Fee', '', 'Togepi', 'Togetic', 'Togekiss', 'Das Pokémon ist zu einem wahren Flugkünstler geworden. Seine elegante Art ist sehr verzaubernd.', 30);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pokibank`
--

CREATE TABLE `pokibank` (
  `name` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `typ` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `img_path` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `anzahl` int(20) NOT NULL,
  `id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `userbank`
--

CREATE TABLE `userbank` (
  `username` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `id` int(10) UNSIGNED NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `userbank`
--

INSERT INTO `userbank` (`username`, `password`, `email`, `id`, `admin`) VALUES
('Tobias Wulff', 'Tester1!', 'tobias.wulff@live.de', 1, 1),
('Test', 'Tester1!', 'Test@test.tesT', 2, 0);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `pokedex`
--
ALTER TABLE `pokedex`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `pokibank`
--
ALTER TABLE `pokibank`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `userbank`
--
ALTER TABLE `userbank`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `pokedex`
--
ALTER TABLE `pokedex`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT für Tabelle `pokibank`
--
ALTER TABLE `pokibank`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `userbank`
--
ALTER TABLE `userbank`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
