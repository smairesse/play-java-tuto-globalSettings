# play-java-tuto-globalSettings

Ce tutoriel permet de découvrir les méthodes de la classe GlobalSettings du framework Play!.

## Synopsis

La classe `GlobalSettings` est instanciée par le framework lorsque l'application démarre, pour permettre d'effectuer des tâches spécifiques.

### Démarrage et arrêt

Vous pouvez redéfinir les méthodes `onLoadConfig`, `beforeStart`, `onStart` et `onStop` pour être informé des événements du cycle de vie.
* La méthode `onLoadConfig` est appelée juste après le chargement de la configuration. Elle permet de la modifier.
* La méthode `beforeStart` est exécutée avant les plugins. Par exemple, elle peut être utilisée pour configurer le schéma de la base de données.
* La méthode `onStart` est exécutée après les plugins et la mise en place de la base de données.
* La méthode `onStop` est exécutée lorsque l'application s'arrête.

### Interception des demandes

Un aspect important de la classe `GlobalSettings` est qu'elle fournit un moyen pour intercepter les demandes et exécuter la logique d'entreprise avant qu'elles ne soient envoyées à une action.
* La méthode `onRequest` est exécutée quand une demande d'action est réalisée.

### Redéfinir une page d'erreur

Vous pouvez redéfinir les méthodes `onHandlerNotFound`, `onBadRequest` et `onError` pour rediriger la sortie vers une page d'erreur de votre choix.

* La méthode `onHandlerNotFound` est appelée quand le framework ne trouve pas une action.
* La méthode `onBadRequest` est appelée quand un itinéraire a été trouvé, mais qu'il n'est pas possible de lier les paramètres de la requête.
* La méthode `onError` est appelée quand une exception se produit.

## Mise en oeuvre

* Créer une classe `Global` dans le package racine de votre application.

  Créer le fichier `Global.java` dans le dossier `app` :

  ```java
import play.GlobalSettings;

public class Global extends GlobalSettings {
 ...
}
```

* Puis redéfinir les méthodes que vous souhaitez.
 
  ```java
  - public Configuration onLoadConfig(Configuration config, File path, ClassLoader classloader)
  - public void beforeStart(Application app)
  - public void onStart(Application app)
  - public void onStop(Application app) 
  - public Action onRequest(Request request, Method actionMethod)
  - public Promise<SimpleResult> onError(RequestHeader request, Throwable t)
  - public Promise<SimpleResult> onHandlerNotFound(RequestHeader request)
  - public Promise<SimpleResult> onBadRequest(RequestHeader request, String error)
  ```
  Pour plus de détails, voir [Global.java ] (https://github.com/smairesse/play-java-tuto-globalSettings/blob/master/app/Global.java)

## Download

[Télécharger le code source](https://github.com/smairesse/play-java-tuto-globalSettings/archive/master.zip)

or 

```bash
git clone --recursive git://github.com/smairesse/play-java-tuto-globalSettings.git
```

## Compatibility

### APIs

| Scala              | Java               |
|:------------------:|:------------------:|
| :x:                | :heavy_check_mark: |

### Versions

#### Play! 1

| 1.0.3.2            | 1.1.1              | 1.2.x              | 1.3.x              | 1.4.x              |
|:------------------:|:------------------:|:------------------:|:------------------:|:------------------:|
| :x:                | :x:                | :x:                | :x:                | :x:                |

> Voir la classe Job

#### Play! 2

| 2.0.x              | 2.1.x              | 2.2.x              | 2.3.x              | 2.4.x              | 2.5.x              |
|:------------------:|:------------------:|:------------------:|:------------------:|:------------------:|:------------------:|
| :x:                | :x:                | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: | :heavy_check_mark: |

## License

Copyright (c) 2015-... Stéphane Mairesse (https://github.com/smairesse)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
