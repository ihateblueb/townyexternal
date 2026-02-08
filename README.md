# TownyExternal

A simple plugin that exposes information from Towny in a simple, easy to use, HTTP API.

Caching can also be configured to reduce unnecessary calls to the Towny API and unnecessary server load.

By default, the server listens to `127.0.0.1:8064`, but that can be configured.

## Why?

So you can make cool things like this: https://orchidmc.org/#towns

## Endpoints

- `/api/towns` fetches a list of all towns
- `/api/town/{id}` fetches a specific town
- `/api/nations` fetches a list of all nations
- `/api/nation/{id}` fetches a specific nation

## Caching

There are three types of caches for TownyExternal: Redis, H2, and Postgres.

### H2 (default)

Everything will be stored in memory. This is the easiest option and default, but required more calls to the Towny API than the other two options since the cache will be cleared when your server restarts.

If you have nothing already set, you don't have to add this line to your config.yml since it's default, but you can.

```yaml
cache: "h2"
```

### Redis (recommended)

Requires installation of Redis or a similar program like Valkey.

Add the following to your config.yml.

```yaml
cache: "redis"

redis-host: "127.0.0.1" # or whatever your host is
redis-port: "6379" # or whatever your port is
redis-prefix: "" # can be left blank, set if you have other programs using the same database
```

### Postgres

Requires installation of PostgreSQL.

Add the following to your config.yml.

```yaml
cache: "postgres"

postgres-host: "127.0.0.1" # or whatever your host is
postgres-port: "5432" # or whatever your port is
postgres-db: "townyexternal" # or whatever your database name is
postgres-user: "youruser" # change this
postgres-pass: "yourpass" # change this
```