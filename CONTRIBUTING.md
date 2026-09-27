# Contributing to Calibrey

Thank you for your interest in contributing to Calibrey! We are building an Apple-grade, privacy-first academic operating system for NCERT students and educators worldwide.

---

## 🧭 Code of Conduct
All contributors and maintainers are expected to adhere to our [Code of Conduct](CODE_OF_CONDUCT.md). Please treat all members of the community with respect and empathy.

---

## 🛠️ Development Workflow

### 1. Fork and Clone
```bash
git clone https://github.com/your-username/calibrey-android.git
cd calibrey-android
```

### 2. Branch Naming Conventions
Create a descriptive branch for your work:
- `feat/feature-name` for new features
- `fix/bug-description` for bug fixes
- `docs/documentation-update` for documentation changes
- `refactor/clean-up-name` for architectural or code improvements

### 3. Code Standards & Style Guide
- **Kotlin First**: Use idiomatic Kotlin with concise, functional expressions.
- **Jetpack Compose Only**: All UI must be written in Jetpack Compose following Material Design 3 guidelines. XML layouts are prohibited.
- **Design Consistency**: Follow the [Liquid-Glass Obsidian Design System](docs/DESIGN_SYSTEM.md). Do not introduce casual emojis or bright cartoon palettes into production screens.
- **Unidirectional Data Flow**: State flows down from `CalibreyViewModel` as `StateFlow`; events flow up via lambda callbacks.
- **Room Database Integrity**: Any change to entities in `data/local/Entities.kt` must be accompanied by a corresponding database version increment in `CalibreyDatabase.kt`.
- **Touch Targets & Accessibility**: Ensure interactive elements meet the minimum 48dp touch target standard and provide non-null `contentDescription` for screen readers.

### 4. Testing Requirements
Before submitting a pull request, verify that your changes compile cleanly and pass local tests:
```bash
# Verify local JVM unit tests
gradle :app:testDebugUnitTest

# Verify app compilation
gradle assembleDebug
```

---

## 📝 Commit Message Guidelines
We follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

- `feat(curriculum): add NCERT Class 12 Physics semiconductor chapter`
- `fix(room): resolve schema identity verification issue`
- `docs(readme): add APK installation guide and badge metrics`
- `refactor(ui): extract liquid glass segmented control component`

---

## 🚀 Submitting a Pull Request
1. Push your branch to your fork on GitHub.
2. Open a Pull Request targeting the `main` branch.
3. Fill out the provided [Pull Request Template](.github/pull_request_template.md).
4. Maintainers will review your submission and provide feedback.
