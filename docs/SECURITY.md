# Política de Seguridad y Criptografía — VERBUM LEX CORE

## 1. Integridad de Datos e Inmutabilidad Criptográfica
- Cada norma, artículo y fragmento relevante de evidencia procesado por VERBUM es firmado digitalmente mediante un hash SHA-256 de 64 caracteres hexadecimales.
- El hashing garantiza que cualquier alteración de una sola coma, plazo o sanción resulte en un hash discrepante, alertando de inmediato sobre una posible corrupción o manipulación de la fuente jurídica.

## 2. Privacidad y Modelo Offline-First
- La base de datos jurídica reside localmente en el dispositivo del operador mediante SQLite / Room (`verbum_lex_database.db`).
- Las consultas al corpus normativo, análisis Lex-to-Lex, cálculos Chronos y grafo de conocimiento operan al 100% en modo offline sin requerir conectividad a servidores externos.
- La integración opcional con la API Gemini se canaliza mediante `GeminiLegalService` con transporte cifrado TLS 1.3, requiriendo credenciales inyectadas de forma segura y jamás expuestas en repositorios públicos.

## 3. Manejo de Secretos y Llaves
- Conforme a los lineamientos de AI Studio, las API Keys residen en variables de entorno (`.env` / `BuildConfig.GEMINI_API_KEY`) y no se incluyen en el control de versiones.
- Las firmas de depuración de Android utilizan un almacén seguro estándar preconfigurado.
