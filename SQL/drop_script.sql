-- script di pulizia

drop table utente cascade constraint;
drop table ordine cascade constraint;
drop table articolo cascade constraint;
drop table ordine_articolo cascade constraint;
drop table immagine cascade constraint;
drop table amministratore cascade constraint;

drop view report;

drop sequence ordine_seq;
drop sequence articolo_seq;
drop sequence admin_Seq;






