# Correzione Esame Dicembre - Calendario dell'Avvento (revisione finale)

## Stato dei file

- **Sorpresa.java** - OK
- **Giocattolo.java** - OK
- **Calendario.java** - OK
- **Test.java** - OK
- **GiornoException.java** - OK
- **SorpresaException.java** - OK

---

## Unico problema rimasto

### Cioccolatino.java - `getCacao()` ritorna il valore sbagliato

- Riga 10: `return cacao * 0.1` ritorna il **costo**, non la percentuale di cacao.
- `getCacao()` e' il getter del campo `cacao`. Deve semplicemente fare `return cacao`.
- Conseguenza nel `toString()`: per Lindt (25% cacao), stampa "con 2.50% di cacao" invece di "con 25.00% di cacao".
- **Fix**: cambia riga 10 da `return cacao * 0.1` a `return cacao`.

---

## Dettagli minori (non bloccanti)

- **Giocattolo.java**: `final String descrizione` e' package-private, idealmente `private final`.
- **Calendario.java**: mancano AF e RI come commenti nella classe.

---
