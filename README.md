# Compose PokeDex

Compose PokeDexは、Jetpack Composeを使用して実装したポケモン図鑑です。

Home | PokemonDetail
:--: | :--:
<img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/474683/61b285c4-cb3e-b752-8a66-091d6242102e.png" width="300" /> | <img src="https://qiita-image-store.s3.ap-northeast-1.amazonaws.com/0/474683/85ad73f8-ef0d-fbe9-45dd-fc58ad9ffbb8.png" width="300" />

## 使用技術

- Jetpack Compose
- MVVM
- Hilt
- マルチモジュール
- Retrofit2
- Coil

## Requirements

- Android Studio：Arctic Fox beta 4
- Gradle：7.0.2
- Android Gradle Plugin：7.1.0-alpha02
- Kotlin：1.5.10

## アーキテクチャ

### データフロー図
<img src="dataflow.png" width="500"/>

- - -
### モジュール図

<img src="project.dot.png" width="500"/>

### 構成

```sh
$ tree -D -L 2
```

```
.
├── app
│   └── src
├── data
│   ├── local
│   ├── remote
│   └── repository
├── domain
├── model
├── presentation
│   ├── common
│   ├── home
│   └── pokemon_detail
└── shared
```