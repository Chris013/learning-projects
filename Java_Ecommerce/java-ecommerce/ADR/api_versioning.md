# API Versioning

## Choice: URI Versioning -> #1

-> easy to understand
-> can be tested directly in the browser
-> easy routing
-> configured api requests won't break with a new version

## Declined: Query Parameter Versioning -> #3

-> old query params could be cached
-> could be forgotten
-> needs docs and you have to know the existing versions

## Declined: Custom Header Versioning -> #4

-> browsers don’t easily send custom headers
-> devs may not notice versioning exists
-> needs specific docs

## Declined: Media Type Versioning (Content Negotiation) -> #2

-> too Complex
-> verbose headers
-> harder Debugging bcause version is hidden in headers
