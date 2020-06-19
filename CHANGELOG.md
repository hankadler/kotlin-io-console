# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.2.1] - 2020-06-19
### Changed
 - Minor editing

## [0.2.0] - 2020-06-18
### Fixed
Fixed select() such that:
- If no value is given and defaultValue was set, return defaultValue
- If no value is given and defaultValue was not set, continue asking
### Changed
 - Thread.sleep() argument from 0.5 second to 1.0 second

## [0.1.0] - 2020-06-17
### Added
 - .gitignore
 - .gradle/
 - CHANGELOG.md
 - LICENSE
 - README.md
 - build.gradle
 - gradle.properties
 - gradle/
 - gradlew
 - gradlew.bat
 - settings.gradle
 - src/

[Unreleased]: https://github.com/hankadler/kotlin-io-console/compare/v0.2.1...HEAD
[0.2.1]: https://github.com/hankadler/kotlin-io-console/compare/v0.2.0...0.2.1
[0.2.0]: https://github.com/hankadler/kotlin-io-console/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/hankadler/kotlin-io-console/releases/tag/v0.1.0
