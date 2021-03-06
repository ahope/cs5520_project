CREATE TABLE pokemon (
  id INTEGER PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  name_de TEXT NOT NULL,
  name_fr TEXT NOT NULL,
  atk INTEGER NOT NULL,
  def INTEGER NOT NULL,
  stam INTEGER NOT NULL,
  candy TEXT NOT NULL,
  candy_de TEXT NOT NULL,
  candy_fr TEXT NOT NULL,
  evolveCandy INTEGER NOT NULL
);
.mode csv pokemon
.separator ;
.import list-pokemon.csv pokemon
.dump
.quit