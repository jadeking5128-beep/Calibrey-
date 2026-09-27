# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |
| < 1.0   | :x:                |

## Reporting a Vulnerability

The Calibrey project team takes security and student data privacy seriously.

If you believe you have discovered a security vulnerability in Calibrey, please follow these steps:

1. **Do not disclose the issue publicly** or open a public issue on GitHub.
2. Email your findings with a detailed description, reproduction steps, and potential impact to the project maintainers.
3. You will receive an acknowledgment within 48 hours.
4. We will coordinate a fix and release an updated build as soon as possible.

### Sensitive Data & Secret Management
- **Zero API Key Hardcoding**: Gemini API keys must never be committed to source control. They should be configured via AI Studio Secrets, environment variables (`.env`), or entered dynamically at runtime by users.
- **Local Storage Security**: Calibrey uses sandboxed app-private SQLite databases via Android Room. Telemetry and user notes are never transmitted to unauthorized third-party endpoints.
