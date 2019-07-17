# Webプログラミング基礎実験ドキュメント

1732087  
菅原直輝


# 目次
<!-- TOC -->

- [Webプログラミング基礎実験ドキュメント](#webプログラミング基礎実験ドキュメント)
- [目次](#目次)
- [概要](#概要)
    - [要件](#要件)
    - [操作](#操作)
        - [URLの登録](#urlの登録)
        - [削除](#削除)
- [機能](#機能)
    - [URLの登録](#urlの登録-1)
    - [URLの削除](#urlの削除)
    - [タイトルの自動取得](#タイトルの自動取得)
    - [アイコンの自動取得](#アイコンの自動取得)
- [デザイン](#デザイン)
- [実装](#実装)
    - [データベースの設計](#データベースの設計)
- [ソース](#ソース)
    - [ファイル構成](#ファイル構成)
    - [Bookmark.java](#bookmarkjava)
    - [Bookmarks.java](#bookmarksjava)
    - [BookmarksDAO.java](#bookmarksdaojava)
    - [bookmarks.jsp](#bookmarksjsp)
        - [概要](#概要-1)
- [問題点](#問題点)

<!-- /TOC -->


# 概要
できる限りの操作を無くしたシンプルなブックマークアプリ。  
操作をURLの登録、ブックマークの削除のみに限定した。  
タイトルや画像などは、自動で取得する。  

## 要件
すべての機能はインターンにつながっている前提で動作する。

## 操作
### URLの登録
上部の入力欄にURLを入力し、登録を押す。
### 削除
ブックマークのリストの右下にある削除ボタンを押す。

# 機能
## URLの登録
ブックマークに登録したいURLを登録する。  
URLは`http://`もしくは`https://`からはじまる。  
一応、`ftp://`や`file:///`なども可能だが、動作は保証されない。  
登録されたものは、一番下に追加される。

## URLの削除
削除されたものはデータベースから削除される。  
戻すことはできない。

## タイトルの自動取得
URLをDBへ登録する時に自動で取得される。  
登録されたURLへのアクセスを行い`title`要素を登録する。

## アイコンの自動取得
URLをDBへ登録する時に自動で取得される。  
アクセスし、取得したHTMLファイルを解析し、`.ico`ファイルを取得する。

# デザイン
Bootstrap 4を利用。
    
# 実装

## データベースの設計

| FIELD | TYPE | NULL |	KEY  | DEFAULT 
|:-|:-|:-|:-|:-
|ID	| INTEGER(10)	| NO | 	PRI	| (NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_9AA39920_169A_4BBD_AFC8_2F887C9A734E")
| URI| 	VARCHAR_IGNORECASE(255)	|YES | | NULL |
| FAVICONPATH | VARCHAR_IGNORECASE(255)| YES	| | NULL |
| TITLE | VARCHAR_IGNORECASE(255)| YES	| | NULL |
| DESCRIPTION| VARCHAR_IGNORECASE(255) | YES	| | 	NULL|

`ID`に`AUTOINCREMENT`を設定することで、IDの操作を意識させなくした。  
`DESCRIPTION`は未使用。  
`URL`の最大文字数は255文字。それ以上の場合は切り捨てられる。  


# ソース
## ファイル構成
- `Bookmark.java`
    Bookmarkのデータクラス。
- `Bookmarks.java`
    Bookmarkの制御を行うサーブレットクラス。
- `BookmarksDAO.java`
    Bookmarkのデータベースにアクセスするクラス。
- `bookmarks.jsp`
    Bookmarkページに利用されるjspファイル。
- `style.css`
    Bookmarkページにのデザインを行うcssファイル。

## Bookmark.java

フィールド変数部分。
```java
public class Bookmark {
	private Long id;
	private String uri;
	private String faviconPath;
	private String title;
	private String description;
}
```
他部分は、getter, setter, constructor。

- `description`は未使用。

## Bookmarks.java

重要部分。
```java
if (action.equals("create")) {
    final String uri = request.getParameter("url");
    if (uri != null && uri != "") {
        HttpClient client = new HttpClient(uri);
        client.GetData();
        String title = client.getTitle();
        title = title == "" ? "No Title": title;
        String favi = client.getFavicon();
        System.out.println(favi);
        dao.create(uri, title, favi);
    }
} else if (action.equals("delete")) {
    int num = 0;
    try {
        num = Integer.parseInt(request.getParameter("number"));
    } catch (Exception e) {
        num = -1;
        e.printStackTrace();
    }finally {
    }
    if (num != -1) {
        System.out.println(num);
        dao.delete(num);
    }
}

```

## BookmarksDAO.java

- `public List<Bookmark> findAll()`  
- `public Boolean create(String uri, String title, String favi)`    
- `public Boolean delete(int num)`  

## bookmarks.jsp
### 概要
- faviconの設定。
- 外部cssの読み込み。
- 内部cssの読み込み。
- リンクは、別タブで開くようになる。  

すべての処理をこのページのみで行う為、別ページにリダイレクトなどはしない。

# 問題点
- SQLインジェクションやXSS対策などのセキュリティ対策は無し。
- `ico`が取得されない場合は、画像がなしになる。
- アクセスしてほしくないサイトにも自動的にアクセスしてしまう(登録しないのがベスト)。
