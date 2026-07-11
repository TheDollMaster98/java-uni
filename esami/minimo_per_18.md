# Minimo per 18 — linee guida generali

Questa sintesi generalizza la griglia d'esame mostrata in `esami/ScalaValutazione.txt`. Le indicazioni valgono per esami simili dove la valutazione premia: rappresentazione, specifica/controlli, costruttori, metodi principali, AF/RI, eccezioni e `Main`.

- Obiettivo pratico: raggiungere la soglia minima (es. 18/30 o 18/32) concentrandosi sulle classi e sulle parti che danno più punti.

Esempio (usando la griglia fornita): combinazioni rapide per ≥ 18

- `Contenitore` (7.5) + `Vetreria` (8.5) + `Eccezioni` (2) = 18 punti.
- `Contenitore` (7.5) + `Vetreria` (8.5) + `Main` (2) = 18 punti.
- `Vetreria` (8.5) + una forma completa (`Cuboide`/`Sfera`/`Cilindro`, ~4 ciascuna) + qualche punto da `Main`/`Eccezioni` = >18.

Linee guida pratiche (applicabili a più esami):

- Priorità: implementa correttamente le classi di dominio principali, con rappresentazione adeguata e visibilità corretta.
- Specifiche e controlli: ogni costruttore e metodo chiave deve avere precondizioni/chk e fallimenti gestiti (eccezioni o return controllati).
- AF/RI: documenta l'Abstract Function e l'Representation Invariant; risolvono molti dubbi dei valutatori.
- Metodi principali: implementa e specifica metodi di modifica e confronto (es. `aggiungi`/`estrai`, `versa`, `compare`) e fornisci implementazioni semplici ma corrette.
- Eccezioni: fornisci le custom exception richieste (checked/unchecked) con messaggi utili.
- `Main`/IO: un `Main` che legga input, splitti e faccia dispatching minimo può dare punti rapidi.
- Test: non obbligatori, ma utili per mostrare che i controlli funzionano; se non li scrivi, rendi visibili le precondizioni e i controlli nel codice.

Conclusione pratica: non serve completare ogni parte dell'esercizio per superare; puntando sulle componenti che valgono di più (rappresentazione + metodi critici + AF/RI + eccezioni o `Main`) si supera spesso la soglia minima anche senza test estensivi. Se vuoi, trasformo queste linee guida in una checklist di sviluppo o in task concreti per iniziare l'implementazione.

Riferimento: griglia esemplificativa in [esami/ScalaValutazione.txt](esami/ScalaValutazione.txt).
