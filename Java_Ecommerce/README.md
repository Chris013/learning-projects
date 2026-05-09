# Topics

## 1. Datenmodell

Ziel-Themen:

- N+1
- Connection Pooling
- Query Analyse

## 2. REST API

Ziel-Themen:

- CRUD API bauen
- Alle HTTP-Methoden korrekt verwenden
- Fehlerformate standardisieren (eigenes Error-Response-Format)
- Pagination implementieren (erst Offset, dann Cursor)
- API Versioning: Eine Strategie wählen und begründen

## 3. Indexierung & Query-Optimierung

Ziel-Themen:

- EXPLAIN ANALYZE auf allen wichtigen Queries ausführen
- Index-Strategie dokumentieren: Welche Indizes, warum?
- Bewusst N+1 einbauen -> Queries zählen -> beheben -> Queries zählen
- Connection Pool konfigurieren und Pool-Größe begründen

## 4. DB-Erweiterung

Ziel-Themen:

- Soft Delete einbauen
- Audit Trail (createdat, updatedat, created_by)
- Eine Hierarchie modellieren (z.Bsp. Produktkategorien)

## 5. API Security Basics

Ziel-Themen:

- JWT-Authentifizierung hinzufügen

## Checkpoint

Was kann ich:

[] Du kannst Query-Logs lesen und Probleme benennen
[] Du kannst ein normalisiertes Datenbankschema entwerfen
[] Du verstehst Transaktionen und ihre Grenzen
[] Projekt ist fertig und dokumentiert

Was habe ich gelernt:

[x] ...

## 6. Test-Typen & TDD

Ziel-Themen:

- Unit Tests für Domain-Logik (kein Framework, kein DB-Zugriff)
- Test Doubles einbauen: bewusst unterscheiden was Stub vs Mock ist
- AAA-Pattern konsequent verwenden

## 7. Integration Tests & Testbare Architektur

Ziel-Themen:

- Integration Tests für Repository-Layer (echte Test-Datenbank)
- Integration Tests für API-Layer (echter HTTP-Stack, aber mock DB)
- Test Coverage Report generieren und analysieren

## 8. CI-Pipeline

Ziel-Themen:

- GitHub Actions: Build -> Lint -> Unit Tests bei jedem Push (was ist die beste reihenfolge: fail fast)
- Integration-Tests bei Push auf main
- Test-Coverage Report als Artifact speichern

## 9. Deployment Strategien & Feature Flags

Ziel-Themen:

- Ein Feature Flag implementieren (einfachste Form: Config-basiert)
- Demployment in eine Staging-Umgebung (kann lokal simuliert sein)
- Rollback-Prozess dokumentieren

## 10. DB Internals & Skalierung

Ziel-Themen:

- Dokumentiere wie du ein System mit 10 Mio. Usern skalieren würdest
- Welche DB-Strategie? (Read Replicas, Sharding?)
- Wann denormalisieren?
- Welche Daten in welchen Datenbanken? (Polyglot Persistence)

## 11. HTTP vertiefung & Load Balancing

Ziel-Themen:

- Rate Limiting implementieren
- Token Bucket Algorithmus (oder Sliding Window)
- Bewusst: welche Endpunkte brauchen Rate Limiting? Warum?
