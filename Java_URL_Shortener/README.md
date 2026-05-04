# 1. Creational & Structural Patterns

Ziel-Themen:

- System Design zuerst dokumentieren (Architekturdiagramm)
- Welche Komponenten? Welche Verantwortlichkeiten?
- Repository Pattern einbauen
- Builder für komplexe Objekte verwenden

# 2. Behavioral Patterns & Architekturmuster

Ziel-Themen:

- Strategy Pattern für URL-Generierung (verschiedene Algorithmen)
- Observer/Event für Click-Tracking (entkoppelt)
- Code Review des bisherigen Codes: Code Smells identifizieren

# 3. Hexagonal Architektur & Clean Architecture

Ziel-Themen:

- Projekt umstrukturieren nach Ports & Adapters:
- Domain-Layer: kein Fremwork, kein HTTP-Abhängigkeit
- Primäre Ports: HTTP Adapter
- Sekundäre Ports: DB Adapter (Repository Interface)
- Dokumentiere: Was hat sich verändert? Was ist testbar?

# 4. Code Smells & Refactoring

Ziel-Themen:

- vollständiger Code Review - alle Code Smells dokumentieren
- einen identifizierten Code Smell refacotrn (dokumentiere vorher/nacher)

# 5. Skalierungsgrundlagen & CAP-Theorem

Ziel-Themen:

- Architektur-Dokumentation:
	- Was würde passieren bei 10x Last?
	- Wo ist der Bottleneck?
	- Wie würdest du skalieren? (Schriftlich, nicht implementieren)
	
# 6. Review & Projekt abschluss

Ziel-Themen:

- Projekt vollständig dokumentieren:
	- Architekturdiagramm (C4 Level 1 & 2)
	- Entscheidungen begründen (ADR für die wichtigsten Entscheidungen)
	- Trade-offs dokumentieren
- Review des gelernten

# Checkpoint

- [] Du kannst alle GoF-Patterns benennen und ihre Anwendungsfälle erklären
- [] Du kannst ein System in Ports & Adapters strukturieren
- [] Projekt ist fertig mit Architektur-Dokumentation
- [] Du kannst Trade-offs zwischen Monolith und Microservices benennen

# 7. Projekt erweitern

Ziel-Themen:

- Cache-Aside für häufige URLS implementieren
- TTL-Strategie definieren und begründen
- Cache Miss Handling implementieren

# 8. Performance-Analyse

Ziel-Themen:

- Load Test mit einem einfachen Tool (z.Bsp. k6, hey, oder Apache Bench)
- Bottleneck identifizieren (wo ist der Engpass?)
- Mindestens einen Bottleneck beheben und wieder messen

# 9. Message Queues & Pub/Sub

Ziel-Themen:

- Click-Tracking asynchorn machen
- In-Memory Queue als einfachste Form (dann konzeptionell auf echte Queue übertragen)
- Outbox Pattern konzeptionell dokumentieren: "So würde ich das mit einer echten Queue bauen"
- Dead Letter Queue: Was würde passieren wenn der Consumer ausfällt?

# 10. Observability & Security

Ziel-Themen:

- Observability einbauen
	- Structured Logging mit Correlation IDs
	- Metrics: Latenz, Fehlerrate, Durchsatz (mindestens als Konzept implementieren)
	- Alerting-Strategie dokumentieren: Worauf würdest du aletieren?
	
# 11. Security in depth

Ziel-Themen:

- Security Review
	- Checklist aller OWASP Top 10 für das eigene Projekt
	- Was ist schon abgedeckt? Was fehlt?
	- Mindestens: Input Validation, Secrets aus dem Code entfernen, HTTPS-only
	
# 12. Großes System Design

Ziel-Themen:

- Projekt abschließen
- Alle Komponenten dokumentieren
- ADRs für alle wichtigen Entscheidungen
- Performance-Test-Ergebnisse dokumentieren
- Review des gelernten

