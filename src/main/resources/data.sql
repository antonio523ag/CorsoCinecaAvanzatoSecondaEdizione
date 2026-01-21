insert into utente(nome,cognome,username,password,ruolo) values('mario','rossi','m.rossi@email.com','1234',1);
insert into casa_automobilistica(nome) values('fiat');
insert into automobile(casa_automobilistica_id,anno_immatricolazione,modello,colore) values(1,2010,'uno','blu');
insert into utente_automobile(utente_id,automobile_id) values(1,1);
