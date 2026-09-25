# Bookshelf

JavaとPostgreSQLを使用したCLI型の蔵書管理アプリです。

ユーザー登録・ログインを行い、自分の本を一覧表示・検索・登録・編集・削除できます。

## 概要

Bookshelfは、自分が所持している本を管理するためのCLIアプリケーションです。

JavaのMVC（Model・View・Controller）構成を使用し、PostgreSQLにユーザー情報と本の情報を保存します。

## 主な機能

### ユーザー機能

- ユーザー登録
- ログイン
- ログアウト
- ユーザー削除
- パスワードのハッシュ化
- 入力値のバリデーション（入力内容のチェック）

### 本の管理機能

- 本の一覧表示
- 詳細表示
- 本の登録
- 本の検索
- 本の編集
- 本の削除
- 入力値のバリデーション（入力内容のチェック）

### 詳細表示内容

- ID
- タイトル
- 巻数
- 出版社
- 著者

### 検索機能

以下の項目から本を検索できます。

- タイトル
- 出版社
- 著者

### 編集機能

以下の項目から本を編集できます。

- タイトル
- 巻数
- 出版社
- 著者

### 本のID管理

`book_id` はユーザーごとに1から採番されます。

また、本を削除した後も過去に使用した `book_id` を再利用しないようにしています。

## 使用技術

- Java
- PostgreSQL
- JDBC（Javaからデータベースへ接続するための仕組み）
- Git / GitHub

## 開発環境

- Java 25
- PostgreSQL 18
- Eclipse
- macOS

## プロジェクト構成

```text
Bookshelf
├── src
│   ├── controller
│   ├── entity
│   ├── repository
│   ├── service
│   ├── view
│   ├── db
│   └── util
├── db
│   └── migration
├── lib
│   └── postgresql-42.7.13.jar
├── .gitignore
└── README.md
```

## 実行方法

PostgreSQLを起動し、Bookshelf用のデータベースを作成します。

その後、データベース接続情報を設定します。

プロジェクトをコンパイルした後、以下のコマンドで実行できます。

`java -cp "bin:lib/postgresql-42.7.13.jar" Main`

## 最終更新日

2026-09-25
